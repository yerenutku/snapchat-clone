package com.headonelab.hacknbreaksnapchat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.headonelab.hacknbreaksnapchat.R;

public class TakenImagePreviewActivity extends AppCompatActivity {
    public static final String KEY_PREVIEW_BYTE_ARRAY = "byte_array";

    private ImageView ivPreview, ivSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_image_preview);
        ivPreview = (ImageView) findViewById(R.id.ivPreview);
        ivSend = (ImageView) findViewById(R.id.ivSend);

        if (getIntent() != null && getIntent().hasExtra(KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(KEY_PREVIEW_BYTE_ARRAY);
            Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//            ivPreview.setImageBitmap(result);
            Glide.with(this)
                    .load(picture)
                    .into(ivPreview);
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
}
