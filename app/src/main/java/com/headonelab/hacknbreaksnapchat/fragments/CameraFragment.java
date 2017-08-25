package com.headonelab.hacknbreaksnapchat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.headonelab.hacknbreaksnapchat.R;

public class CameraFragment extends Fragment {
    // TODO : create cameraView variable

    private ImageButton btCapture;

    public CameraFragment() {
        // Required empty public constructor
    }

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        // TODO : init cameraView, setPermission to CameraKit.Constants.PERMISSIONS_PICTURE
        // TODO : setCameraListener and jpegQuality

        btCapture = rootView.findViewById(R.id.ibCapture);
        btCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : take picture
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        // TODO : notify cameraView
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO : notify cameraView
    }

    // TODO : init cameraListener
    // TODO : add byte array to intent and start takenImagePreview activity

}
