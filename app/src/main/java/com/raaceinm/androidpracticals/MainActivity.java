package com.raaceinm.androidpracticals;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.raaceinm.androidpracticals.Tools.Vid;


public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate activity initialized");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView2);
        Vid vid = new Vid(videoView, this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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

            AutoCompleteTextView autoCompleteTextView = findViewById(R.id.completed_sheesh);
            String inputtedURL = autoCompleteTextView.getText().toString();

            Log.i(TAG, "Inputted URL: " + inputtedURL);

            Intent intent = new Intent(this, FilesTestActivity.class);
            intent.putExtra("autoCompleteTextView", inputtedURL);
            startActivity(intent);
        });
    }

    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause activity initialized");
    }


    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume activity initialized");
    }

    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop activity initialized");

        Vid vid = new Vid(videoView, this);
        vid.clearVideoCache();
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart activity initialized");

        videoView = findViewById(R.id.videoView2);
        Vid vid = new Vid(videoView, this);
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy activity initialized");

        Vid vid = new Vid(videoView, this);
        vid.clearVideoCache();
    }
}