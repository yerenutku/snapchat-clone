package com.headonelab.hacknbreaksnapchat.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.headonelab.hacknbreaksnapchat.R;

public class TakenImagePreviewActivity extends AppCompatActivity {
    public static final String KEY_PREVIEW_BYTE_ARRAY = "byte_array";

    private ImageView ivPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_image_preview);
        ivPreview = (ImageView) findViewById(R.id.ivPreview);

        if (getIntent() != null && getIntent().hasExtra(KEY_PREVIEW_BYTE_ARRAY)) {
            byte[] picture = getIntent().getByteArrayExtra(KEY_PREVIEW_BYTE_ARRAY);
            Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//            ivPreview.setImageBitmap(result);
            Glide.with(this)
                    .load(picture)
                    .into(ivPreview);
        }
    }
}
