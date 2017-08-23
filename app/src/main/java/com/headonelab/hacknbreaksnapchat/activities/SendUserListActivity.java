package com.headonelab.hacknbreaksnapchat.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.adapters.UserListAdapter;
import com.headonelab.hacknbreaksnapchat.models.MessageModel;
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
        if (getIntent() != null && getIntent().hasExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(TakenImagePreviewActivity.KEY_PREVIEW_BYTE_ARRAY);
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(this);
            username = sharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");
            uploadMessage(picture, mUsers.get(position));

        }
    }

    private void uploadMessage(byte[] messageBytes, final String messageReceiver){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("messages");
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_MESSAGES);
        final String key = databaseReference.push().getKey();

        UploadTask uploadTask = storageReference.child(key).putBytes(messageBytes);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d("onFailure", exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                databaseReference.child(messageReceiver).child(key).setValue(new MessageModel(key, username, downloadUrl.toString()));
            }
        });
    }

}
