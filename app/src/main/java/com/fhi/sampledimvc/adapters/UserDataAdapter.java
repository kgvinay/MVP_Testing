package com.fhi.sampledimvc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fhi.sampledimvc.R;
import com.fhi.sampledimvc.data.entity.users.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vinay on 11/29/2016.
 */
public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {
    private List<User> mDataList;

    public UserDataAdapter(List<User> dataList) {
        mDataList = dataList;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.mLoginTitleView.setText(mDataList.get(position).getLogin());
        holder.mReposURLTextView.setText(mDataList.get(position).getReposUrl());
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.loginTextView)
        TextView mLoginTitleView;
        @BindView(R.id.reposUrlTextView)
        TextView mReposURLTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
