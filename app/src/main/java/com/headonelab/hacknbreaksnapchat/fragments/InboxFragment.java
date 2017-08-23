package com.headonelab.hacknbreaksnapchat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.activities.MessageActivity;
import com.headonelab.hacknbreaksnapchat.adapters.InboxAdapter;
import com.headonelab.hacknbreaksnapchat.models.MessageModel;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements ClickListener {

    private DatabaseReference mDatabaseReference;
    private SharedPreferencesHelper mSharedPreferencesHelper;

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
        mRvInbox = (RecyclerView) view.findViewById(R.id.rv_inbox);
        mRvInbox.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvInbox.setHasFixedSize(true);
        mMessageModels = new ArrayList<>();
        mInboxAdapter = new InboxAdapter(getContext(), this, mMessageModels);
        mRvInbox.setAdapter(mInboxAdapter);
    }

    private void initFirebase() {
        mSharedPreferencesHelper = new SharedPreferencesHelper(getContext());
        String username = mSharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");

        if (username.equals("")) {
            // error
        }

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_MESSAGES).child(username);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                mMessageModels.add(messageModel);
                mInboxAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                MessageModel tempModel = null;

                for (MessageModel item : mMessageModels) {

                    if (item.getImageName().equals(messageModel.getImageName())) {
                        tempModel = item;
                    }

                }

                if (tempModel != null) {
                    mMessageModels.remove(tempModel);
                    mInboxAdapter.notifyDataSetChanged();
                }
            }

            // region UNNECESSARY
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            // endregion
        });

        // dummy data
        String key = mDatabaseReference.push().getKey();
        mDatabaseReference.getParent().child("yasin").child(key).setValue(new MessageModel(key, "eren", "img"));
    }

    @Override
    public void itemClickListener(int position) {
        MessageModel messageModel = mMessageModels.get(position);

        Intent intent = new Intent(getContext(), MessageActivity.class);
        intent.putExtra(Constants.param_message_name, messageModel.getImageName());
        intent.putExtra(Constants.param_message_sender, messageModel.getFromWho());
        intent.putExtra(Constants.param_message_key, messageModel.getKey());
        startActivity(intent);
    }

}