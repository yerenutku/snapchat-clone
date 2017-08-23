package com.headonelab.hacknbreaksnapchat.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

public class MessageActivity extends BaseActivity {

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    private ImageView mIvMessageContainer;
    private TextView mTvSender, mTvTimer;

    private CountDownTimer mCountDownTimer;
    private int millisInFuture = 7000, countDownInterval = 1000;

    private DatabaseReference recordRef;
    private StorageReference messageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        String username = mSharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_MESSAGES).child(username);
        initCountDown();

        mIvMessageContainer = (ImageView) findViewById(R.id.iv_message_container);
        mIvMessageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCountDownTimer.cancel();
                finish();
            }
        });
        mTvSender = (TextView) findViewById(R.id.tv_sender);
        mTvTimer = (TextView) findViewById(R.id.tv_timer);

        if (getIntent() != null) {
            String messageUrl = getIntent().getStringExtra(Constants.param_message_url);
            String messageKey = getIntent().getStringExtra(Constants.param_message_key);
            String messageSender = getIntent().getStringExtra(Constants.param_message_sender);
            mTvSender.setText(messageSender);

            messageRef = mStorageReference.child("messages").child(messageKey);
            recordRef = mDatabaseReference.child(messageKey);

            getMessage(messageUrl);
            deleteMessageOnDatabase();
            mCountDownTimer.start();
        }

    }

    private void getMessage(String messageUrl) {
        Glide.with(MessageActivity.this)
                .load(messageUrl)
                .centerCrop()
                //.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(mIvMessageContainer);

    }

    private void initCountDown() {
        mCountDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTimer.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                finish();
                deleteMessageOnStorage();
            }
        };
    }

    private void deleteMessageOnDatabase() {
        recordRef.removeValue();
    }

    private void deleteMessageOnStorage(){
        messageRef.delete();
    }

}