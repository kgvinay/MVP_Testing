package com.fhi.sampledimvc.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.data.entity.starred.GitHubRepoStarred;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 11/28/2016.
 */
public class StarredDataAdapter extends RecyclerView.Adapter<StarredDataAdapter.StarredViewHolder>{
    private List<GitHubRepoStarred> mDataList;

    public StarredDataAdapter(List<GitHubRepoStarred> dataList) {
        mDataList = dataList;
    }

    @Override
    public StarredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_starred, parent, false);
        return new StarredViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StarredViewHolder holder, int position) {
        holder.mTitleView.setText(mDataList.get(position).getName());
        holder.mDescView.setText(mDataList.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
      return mDataList != null ? mDataList.size() : 0;
    }

    public class StarredViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleTextView)
        TextView mTitleView;
        @BindView(R.id.creationDateTextView)
        TextView mDescView;

        public StarredViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
