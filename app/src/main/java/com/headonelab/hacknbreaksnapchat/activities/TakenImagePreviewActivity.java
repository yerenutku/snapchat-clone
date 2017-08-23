package com.headonelab.hacknbreaksnapchat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.headonelab.hacknbreaksnapchat.R;
import com.headonelab.hacknbreaksnapchat.models.MessageModel;
import com.headonelab.hacknbreaksnapchat.utils.Constants;
import com.headonelab.hacknbreaksnapchat.utils.SharedPreferencesHelper;

public class TakenImagePreviewActivity extends AppCompatActivity {
    public static final String KEY_PREVIEW_BYTE_ARRAY = "byte_array";
    private String username;
    private ImageView ivPreview, ivSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_image_preview);
        ivPreview = (ImageView) findViewById(R.id.ivPreview);
        ivSend = (ImageView) findViewById(R.id.ivSend);

        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(this);
        username = sharedPreferencesHelper.getPreferences(Constants.SP_USERNAME, "");

        if (getIntent() != null && getIntent().hasExtra(KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(KEY_PREVIEW_BYTE_ARRAY);
            Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//            ivPreview.setImageBitmap(result);
            Glide.with(this)
                    .load(picture)
                    .into(ivPreview);

            uploadMessage(picture, "yasin");
        }
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent() != null && getIntent().hasExtra(KEY_PREVIEW_BYTE_ARRAY)) {
                    byte[] picture = getIntent().getByteArrayExtra(KEY_PREVIEW_BYTE_ARRAY);
                    Intent intent  = new Intent(TakenImagePreviewActivity.this,SendUserListActivity.class);
                    intent.putExtra(KEY_PREVIEW_BYTE_ARRAY,picture);
                    startActivity(intent);
                }
            }
        });
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
