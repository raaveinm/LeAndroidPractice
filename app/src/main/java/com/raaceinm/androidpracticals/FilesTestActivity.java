package com.raaceinm.androidpracticals;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FilesTestActivity extends AppCompatActivity {
    boolean webViewReleased = false;

    private static final String TAG = "SET-GALLERY";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_files_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart activity initialized");


        ImageView thirdImage = findViewById(R.id.imageView3);
        Button dropMeButton = findViewById(R.id.StillDropMe);
        WebView webView = findViewById(R.id.webView);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        String autoCompleteTextView = arguments.getString("autoCompleteTextView");

        Log.i(TAG, "Received URL: " + autoCompleteTextView);

        dropMeButton.setOnClickListener(v->{
            if (!webViewReleased){
                webViewReleased = true;
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl("https://"+autoCompleteTextView);
            }else{
                webView.setVisibility(View.GONE);
                webViewReleased = false;
            }
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
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart activity initialized");

        WebView webView = findViewById(R.id.webView);
        webView.setVisibility(View.GONE);
        webViewReleased = false;

        Intent intent = new Intent(this, ExperimentalSheesh.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        Log.i(TAG, "onDestroy activity initialized");
        super.onDestroy();
    }
}