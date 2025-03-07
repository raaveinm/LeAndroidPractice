package com.raaceinm.androidpracticals;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

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

        Log.i(TAG, "onStart activity initialized");
        super.onStart();
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

        Intent intent = new Intent(this, ExperimentalSheesh.class);
        startActivity(intent);
    }

    protected void onDestroy(){
        Log.i(TAG, "onDestroy activity initialized");
        super.onDestroy();
    }

    public void PerformLinearLayout(View view){
        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        FragmentContainerView frameLayout = findViewById(R.id.ShowFrameLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);

        scrollView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void PerformFrameLayout(View view){
        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        FragmentContainerView frameLayout = findViewById(R.id.ShowFrameLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);

        scrollView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    public void home (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backToScroll(View view){
        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);

        scrollView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }
}