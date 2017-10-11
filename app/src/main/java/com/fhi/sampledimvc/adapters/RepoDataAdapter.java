package com.fhi.sampledimvc.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.data.entity.repos.GitHubUserRepos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author David Wu (david10608@gmail.com)
 *         Created by pl4gue on 09.10.17.
 */

public class RepoDataAdapter extends RecyclerView.Adapter<RepoDataAdapter.RepoDataHolder> {
    private List<GitHubUserRepos> mRepoList;

    public RepoDataAdapter(List<GitHubUserRepos> reposList) {
        mRepoList = reposList;
    }

    @Override
    public RepoDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepoDataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoDataHolder holder, int position) {
        Log.d("logs", "getItemCount: " + mRepoList.size());
        holder.mRepoTitleView.setText(mRepoList.get(position).getName());
        holder.mRepoLanguageTextView.setText(String.format("Language: %s", String.valueOf(mRepoList.get(position).getLanguage() == null ? "N/A" : mRepoList.get(position).getLanguage())));
        holder.mRepoCreationDateTextView.setText(String.format("Created on: %s at %s", mRepoList.get(position).getCreatedAt().split("T")[0], mRepoList.get(position).getCreatedAt().split("T")[1].substring(0, mRepoList.get(position).getCreatedAt().split("T")[1].length() - 1)));
    }

    @Override
    public int getItemCount() {
        return mRepoList == null ? 0 : mRepoList.size();
    }

    public class RepoDataHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.repoTitleTextView)
        TextView mRepoTitleView;
        @BindView(R.id.repoLanguageTextView)
        TextView mRepoLanguageTextView;
        @BindView(R.id.repoCreationDateTextView)
        TextView mRepoCreationDateTextView;

        public RepoDataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
