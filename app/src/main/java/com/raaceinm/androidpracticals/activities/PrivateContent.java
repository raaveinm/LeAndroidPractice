package com.raaceinm.androidpracticals.activities;

import static android.service.autofill.Validators.and;
import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.snackbar.Snackbar;
import com.raaceinm.androidpracticals.R;

public class PrivateContent extends AppCompatActivity {
    public static final String EXTRA_DATA = "com.raaceinm.androidpracticals.activities.EXTRA_DATA";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_content);
        FrameLayout mainLayout = findViewById(R.id.main);
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    @Override
    protected void onStart() {

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        FragmentContainerView fragmentContainerView = findViewById(R.id.fragmentContainerView);

        if (!getIntent().getBooleanExtra("isRegistered", false)) {

            login.setOnClickListener(v -> {
                String userName = username.getText().toString();
                String userPassword = password.getText().toString();
                Intent intent = new Intent();

                if (userName.equals("era") && userPassword.equals("s1234")) {
                    intent.putExtra(EXTRA_DATA, "valid");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Snackbar.make(v,
                                    "incorrect username or password",
                                    Snackbar.LENGTH_LONG)
                            .show();
                    intent.putExtra(EXTRA_DATA, "invalid");
                    setResult(Activity.RESULT_CANCELED, intent);
                    finish();
                }
            });
        }else{
            username.setVisibility(GONE);
            password.setVisibility(GONE);
            login.setVisibility(GONE);
            fragmentContainerView.setVisibility(VISIBLE);
        }
        super.onStart();
    }

    @Override
    protected void onRestart() {
        startActivity(new Intent(this, ExperimentalSheesh.class));
        super.onRestart();
    }
}