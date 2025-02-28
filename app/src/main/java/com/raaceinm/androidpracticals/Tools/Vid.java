package com.raaceinm.androidpracticals.Tools;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Vid {
    private static final String TAG = "VID";
    private final VideoView videoView;
    private final Context context;
    public String videoFileName;

    public Vid(VideoView videoView, Context context, String videoFileName) {
        this.videoView = videoView;
        this.context = context;
        playVideo(videoFileName);
    }

    private void playVideo(String videoFileName) {
        try {
            File outputFile = new File(context.getCacheDir(), videoFileName);

            if (!outputFile.exists()) {
                InputStream inputStream = context.getAssets().open(videoFileName);
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int read;
                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                Log.d(TAG, "Video file copied to cache.");
            } else {
                Log.d(TAG, "Video file already exists in cache.");
            }

            Uri videoUri = Uri.fromFile(outputFile);
            videoView.setVideoURI(videoUri);

            videoView.setOnPreparedListener(mp -> {
                mp.setVideoScalingMode(1);
                Log.d(TAG, "MediaPlayer Prepared");
                mp.setLooping(true);
                mp.setVolume(0.6F, 0.6F);
                videoView.start();
            });

            videoView.setOnErrorListener((mp, what, extra) -> {
                Log.e(TAG, "MediaPlayer Error: what=" + what + ", extra=" + extra);
                return true;
            });

            videoView.setOnInfoListener((mp, what, extra) -> {
                Log.i(TAG, "MediaPlayer Info: what=" + what + ", extra=" + extra);
                return false;
            });

            videoView.requestFocus();

        } catch (IOException e) {
            Log.e(TAG, "Error loading video:", e);
        }
    }

    public void clearVideoCache(String videoFileName) {
        try {
            File outputFile = new File(context.getCacheDir(), videoFileName);

            if (outputFile.exists()) {
                if (outputFile.delete()) {
                    Log.d(TAG, "Video cache cleared");
                } else {
                    Log.e(TAG, "Failed to clear video cache.");
                }
            } else {
                Log.d(TAG, "Video cache is already empty.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error clearing video cache:", e);
        }
    }
}