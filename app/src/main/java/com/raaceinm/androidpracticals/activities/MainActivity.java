package com.raaceinm.androidpracticals.activities;

import static android.view.View.GONE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.Tools.Sterilization;
import com.raaceinm.androidpracticals.Tools.Vid;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "asd";
    private static final String VideoFileNameDefault = "background.mp4";
    private static final String VideoFileNameExtra = "IP.mp4";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate activity initialized");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView2);
        Vid vid = new Vid(videoView, this,VideoFileNameDefault);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart activity initialized");

        Button dropMeButton = findViewById(R.id.button);

        dropMeButton.setOnClickListener(v -> {

            Toast.makeText(this,"achtung",LENGTH_SHORT).show();

            AutoCompleteTextView autoCompleteTextView = findViewById(R.id.completed_sheesh);
            String inputtedURL = autoCompleteTextView.getText().toString();

            Log.i(TAG, "Inputted URL: " + inputtedURL);

            Intent intent = new Intent(this, FilesTestActivity.class);
            intent.putExtra("autoCompleteTextView", inputtedURL);
            startActivity(intent);

            Intent rt = new Intent(this, FilesTestActivity.class);
            rt.putExtra("autoCompleteTextView", inputtedURL);
            startActivity(rt);
        });

        Bundle arguments = getIntent().getExtras();
        new Thread(() -> {

            if (arguments != null) {
                Float Phone = Sterilization.getPhone();
                String Email = Sterilization.getEmail();
                String Password = Sterilization.getPassword();
                String name = Sterilization.getName();
                Integer Age = Sterilization.getAge();

                if (name != null) {
                    videoView = findViewById(R.id.videoView2);
                    Vid vid = new Vid(videoView, this, VideoFileNameDefault);
                    vid.clearVideoCache(VideoFileNameDefault);

                    Vid vidExtra = new Vid(videoView, this, VideoFileNameExtra);

                    AutoCompleteTextView autoCompleteTextView = findViewById(R.id.completed_sheesh);
                    autoCompleteTextView.setVisibility(GONE);

                    Toast.makeText(this, "data has been stolen successful, dear " +
                            name, LENGTH_LONG).show();

                    Log.v("STOLEN DATA", "NAME " + name);
                    Log.v("STOLEN DATA", "PHONE " + Phone);
                    Log.v("STOLEN DATA", "EMAIL " + Email);
                    Log.v("STOLEN DATA", "PASSWORD " + Password);
                    Log.v("STOLEN DATA", "AGE " + Age);
                } else {
                    videoView = findViewById(R.id.videoView2);
                    Vid vid = new Vid(videoView, this, VideoFileNameDefault);
                }
            } else {
                videoView = findViewById(R.id.videoView2);
                Vid vid = new Vid(videoView, this, VideoFileNameDefault);
            }
        }).start();

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume activity initialized");



    }

    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause activity initialized");
    }

    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop activity initialized");

        Vid vid = new Vid(videoView, this,VideoFileNameDefault);
        vid.clearVideoCache(VideoFileNameDefault);
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart activity initialized");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy activity initialized");

        Vid vidDefault = new Vid(videoView, this,VideoFileNameDefault);
        vidDefault.clearVideoCache(VideoFileNameDefault);
        Vid vidExtra = new Vid(videoView, this,VideoFileNameExtra);
        vidExtra.clearVideoCache(VideoFileNameExtra);
    }
}