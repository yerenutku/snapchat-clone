package com.headonelab.hacknbreaksnapchat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.activities.MessageActivity;
import com.headonelab.hacknbreaksnapchat.adapters.InboxAdapter;
import com.headonelab.hacknbreaksnapchat.models.MessageModel;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;
import com.headonelab.hacknbreaksnapchat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements ClickListener {

    // TODO : create databaseRef and sharedPref variables

    private List<MessageModel> mMessageModels;
    private RecyclerView mRvInbox;
    private InboxAdapter mInboxAdapter;

    public InboxFragment() {
        // Required empty public constructor
    }

    public static InboxFragment newInstance() {
        return new InboxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        initViews(view);
        initFirebase();
        return view;
    }

    private void initViews(View view) {
        mRvInbox = view.findViewById(R.id.rv_inbox);
        mRvInbox.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvInbox.setHasFixedSize(true);
        mMessageModels = new ArrayList<>();
        mInboxAdapter = new InboxAdapter(getContext(), this, mMessageModels);
        mRvInbox.setAdapter(mInboxAdapter);
    }

    private void initFirebase() {
        // TODO : init sp, get username

        // TODO : init databaseRef from getRef = messages and child = username
        // TODO : add childEventListener for listen changes

        // TODO : getVal and add to list, notify adapter
        // TODO : getVal, iterate, get it on temp value, remove from list and notify adapter

        // TODO : to add dummy data, push and getKey from databaseRef
        // TODO : ref.getparent, child who, child key, setval with messageModel

    }

    @Override
    public void itemClickListener(int position) {
        MessageModel messageModel = mMessageModels.get(position);

        Intent intent = new Intent(getContext(), MessageActivity.class);
        intent.putExtra(Constants.param_message_url, messageModel.getUrl());
        intent.putExtra(Constants.param_message_sender, messageModel.getFromWho());
        intent.putExtra(Constants.param_message_key, messageModel.getKey());
        startActivity(intent);
    }

}