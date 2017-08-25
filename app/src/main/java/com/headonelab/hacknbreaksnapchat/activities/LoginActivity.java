package com.headonelab.hacknbreaksnapchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO : Create variable from DatabaseReference and sharedPreferences
    private SharedPreferencesHelper mSharedPreferencesHelper;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUtils();

        initViews();
    }

    private void initUtils(){
        // TODO : init databaseReference, ref from firebaseUsers
        // TODO : init sp

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_USERS);
        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
    }

    private void initViews(){
        // TODO: init views, check last username from sp
        mEditText = (EditText) findViewById(R.id.et_username);
        mButton = (Button) findViewById(R.id.bt_login);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO : get username, set value to databaseRef and sp
        String username = mEditText.getText().toString();
        mDatabaseReference.child(username).setValue(username);

        mSharedPreferencesHelper.setPreferences(Constants.SP_USERNAME, username);

        startActivity(new Intent(this, MainActivity.class));
    }

}