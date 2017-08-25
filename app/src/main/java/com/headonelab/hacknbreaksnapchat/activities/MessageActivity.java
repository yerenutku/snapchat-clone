package com.headonelab.hacknbreaksnapchat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

public class MessageActivity extends AppCompatActivity {

    // TODO : databaseReference and storageReference definitions

    private SharedPreferencesHelper mSharedPreferencesHelper;

    private ImageView mIvMessageContainer;
    private TextView mTvSender, mTvTimer;

    // TODO : definition of countDownTimer and millisInFuture & countDownInterval values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
        String username = mSharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");

        initCountDown();

        mIvMessageContainer = (ImageView) findViewById(R.id.iv_message_container);
        mIvMessageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : call delete message on storage func, cancel timer and finish activity
            }
        });
        mTvSender = (TextView) findViewById(R.id.tv_sender);
        mTvTimer = (TextView) findViewById(R.id.tv_timer);

        if (getIntent() != null) {
            String messageUrl = getIntent().getStringExtra(Constants.param_message_url);
            String messageKey = getIntent().getStringExtra(Constants.param_message_key);
            String messageSender = getIntent().getStringExtra(Constants.param_message_sender);
            mTvSender.setText(messageSender);

            // TODO : get storageRef, child messages, child messageKey
            // TODO : get databaseRef on FIREBASE_MESSAGES, child username, child messageKey

            // TODO : getMessage from Url, delete message on db and start timer
        }

    }

    private void getMessage(String messageUrl) {
        Glide.with(MessageActivity.this)
                .load(messageUrl)
                .centerCrop()
                .into(mIvMessageContainer);

    }

    private void initCountDown() {
        // TODO : init countDownTimer with millisInFuture and countDownInterval values
        // TODO : set timer text to untilFinished / 1000
        // TODO : in onFinish method, finish activity and deleteMessageOnStorage

    }

    private void deleteMessageOnDatabase() {
        // TODO : remove database value
    }

    private void deleteMessageOnStorage(){
        // TODO : delete storage value
    }

}