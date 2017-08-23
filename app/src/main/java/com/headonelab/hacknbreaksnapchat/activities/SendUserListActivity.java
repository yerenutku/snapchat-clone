package com.headonelab.hacknbreaksnapchat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.adapters.UserListAdapter;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;
import com.headonelab.hacknbreaksnapchat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SendUserListActivity extends AppCompatActivity implements ClickListener {
    private DatabaseReference mDatabaseRoot;
    private List<String> mUsers = new ArrayList<>();
    private RecyclerView rvUser;
    private UserListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_user_list);
        rvUser = (RecyclerView) findViewById(R.id.rvUser);
        mAdapter = new UserListAdapter(this,this,mUsers);
        rvUser.setAdapter(mAdapter);
        if (getIntent() != null && getIntent().hasExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY);
        }

        mDatabaseRoot = FirebaseDatabase.getInstance().getReference();
        mDatabaseRoot.child(Constants.FIREBASE_USERS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String user = dataSnapshot.getValue(String.class);
                mUsers.add(user);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void itemClickListener(int position) {

    }
}
