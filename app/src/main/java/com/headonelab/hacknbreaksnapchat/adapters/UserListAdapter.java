package com.headonelab.hacknbreaksnapchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mUserList;
    private ClickListener mClickListener;

    public UserListAdapter(Context context, ClickListener clickListener, List<String> userList) {
        mContext = context;
        mClickListener = clickListener;
        mUserList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_list, parent, false);
        return new UserListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String user = mUserList.get(position);
        holder.mTvName.setText(user);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tvUser);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.itemClickListener(getAdapterPosition());
                }
            });
        }

    }

}