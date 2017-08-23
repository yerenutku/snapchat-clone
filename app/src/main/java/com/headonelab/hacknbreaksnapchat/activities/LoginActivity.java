package com.headonelab.hacknbreaksnapchat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private DatabaseReference mDatabaseReference;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    private EditText mEtUsername;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUtils();

        initViews();
    }

    private void initUtils(){
        mDatabaseReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_USERS);

        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
    }

    private void initViews(){
        mEtUsername = (EditText) findViewById(R.id.et_username);
        String lastUsername = mSharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");
        if (!lastUsername.equals(""))
            mEtUsername.setText(lastUsername);

        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String username = mEtUsername.getText().toString();

        if (!username.isEmpty()) {
            mDatabaseReference.child(username).setValue(username);
            mSharedPreferencesHelper.setPreferences(Constants.SP_USERNAME, username);

            startActivity(new Intent(this, MainActivity.class));
        }
    }

}