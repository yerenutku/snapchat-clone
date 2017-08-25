package com.headonelab.hacknbreaksnapchat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.adapters.UserListAdapter;
import com.headonelab.hacknbreaksnapchat.utils.ClickListener;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public class SendUserListActivity extends AppCompatActivity implements ClickListener {
    private DatabaseReference mDatabaseRoot;
    private List<String> mUsers = new ArrayList<>();
    private RecyclerView mRvUser;
    private UserListAdapter mAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_user_list);
        mRvUser = (RecyclerView) findViewById(R.id.rvUser);
        mRvUser.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserListAdapter(this,this,mUsers);
        mRvUser.setAdapter(mAdapter);

        // TODO : get databaseReference
        // TODO : add childEventListener to reference's child with FIREBASE_USERS value

        // TODO : in onChildAdded function, get String.class value from dataSnapshot
        // TODO : add this username value to list and notify adapter
    }

    @Override
    public void itemClickListener(int position) {
        if (getIntent() != null && getIntent().hasExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY);
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(this);
            username = sharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");
            uploadMessage(picture, mUsers.get(position));

        }
    }

    private void uploadMessage(byte[] messageBytes, final String messageReceiver){
        // TODO : get storageReference, child messages
        // TODO : get databaseReference with FIREBASE_MESSAGES
        // TODO : get key value with databaseReference push and getKey

        // TODO : define uploadTask value with storageReference, child key, putBytes with message
        // TODO : add addOnFailureListener and use onSuccessListener function to get downloadUrl from taskSnapshot

        // TODO : databaseReference, child messageReceiver, child key, setValue with messageModel
        // TODO : setResult with RESULT_OK and finish activity.

    }

}
