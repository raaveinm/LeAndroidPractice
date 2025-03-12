package com.raaceinm.androidpracticals.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.snackbar.Snackbar;
import com.raaceinm.androidpracticals.R;
import java.util.Objects;

public class FilesTestActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "invalid";
    private static final String TAG = "SET-GALLERY";
    private ActivityResultLauncher<Intent> resultLauncher;
    private Boolean isRegistered = false;

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

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String forcedData = data != null ? data.getStringExtra(FilesTestActivity.EXTRA_DATA) : null;
                        Log.d(TAG, "onActivityResult: " + forcedData);
                        isRegistered = true;
                        Snackbar.make(this.findViewById(android.R.id.content),
                                        "success!",
                                        Snackbar.LENGTH_LONG).show();
                        Button register = findViewById(R.id.Register);
                        register.setText(R.string.see_extra);
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        isRegistered = false;
                        Log.d(TAG, "onActivityResult: canceled");
                        Snackbar.make(this.findViewById(android.R.id.content),
                                        "incorrect username or password",
                                        Snackbar.LENGTH_LONG).show();
                    } else {
                        isRegistered = false;
                        Log.e(TAG, "onActivityResult: failed");
                        Snackbar.make(this.findViewById(android.R.id.content),
                                "there was an error",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
        );
    }

    protected void onStart() {
        Log.i(TAG, "onStart activity initialized");
        super.onStart();
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause activity initialized");
    }


    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume activity initialized");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop activity initialized");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart activity initialized");
    }

    protected void onDestroy() {
        Log.i(TAG, "onDestroy activity initialized");
        super.onDestroy();
    }

    public void PerformLinearLayout(View view) {
        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        FragmentContainerView frameLayout = findViewById(R.id.ShowFrameLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);

        scrollView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void PerformFrameLayout(View view) {

        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        FragmentContainerView frameLayout = findViewById(R.id.ShowFrameLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);

        scrollView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    public void home(View view) {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }

    public void backToScroll(View view) {
        FragmentContainerView linearLayout = findViewById(R.id.ShowLinearLayout);
        ScrollView scrollView = findViewById(R.id.scrollView);
        FragmentContainerView frameLayout = findViewById(R.id.ShowFrameLayout);

        scrollView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        frameLayout.setVisibility(View.GONE);
    }

    public void Register(View view) {
        Intent register = new Intent(this, PrivateContent.class);
        if (isRegistered) {
            register.putExtra("isRegistered", true);
            startActivity(register);
        } else {
            resultLauncher.launch(register);

        }
    }
}