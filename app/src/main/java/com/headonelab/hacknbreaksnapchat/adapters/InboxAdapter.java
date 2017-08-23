package com.headonelab.hacknbreaksnapchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.models.MessageModel;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> {

    private Context mContext;
    private List<MessageModel> mMessageModelList;
    private ClickListener mClickListener;

    public InboxAdapter(Context context, ClickListener clickListener, List<MessageModel> messageModelList) {
        mContext = context;
        mClickListener = clickListener;
        mMessageModelList = messageModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_inbox, parent, false);
        return new InboxAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MessageModel messageModel = mMessageModelList.get(position);

        holder.mTvSender.setText(messageModel.getFromWho());
        holder.mTvUrl.setText(messageModel.getUrl());
    }

    @Override
    public int getItemCount() {
        return mMessageModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvSender, mTvUrl;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTvSender = itemView.findViewById(R.id.tv_message_sender);
            mTvUrl = itemView.findViewById(R.id.tv_message_url);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.itemClickListener(getAdapterPosition());
                }
            });
        }

    }

}