package com.raaceinm.androidpracticals.activities;

import static android.view.View.GONE;
import static android.view.View.inflate;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.fragments.AnotherPrivate;

import com.raaceinm.androidpracticals.fragments.ExtraContent;
import com.raaceinm.androidpracticals.fragments.LinearLayout;

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
        FrameLayout hiddenContent = findViewById(R.id.hiddenFrameLayout);
        View buttons = findViewById(R.id.buttons);


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

                    intent.putExtra(EXTRA_DATA, "invalid");
                    setResult(Activity.RESULT_CANCELED, intent);
                    finish();
                }
            });
        }else{
            username.setVisibility(GONE);
            password.setVisibility(GONE);
            login.setVisibility(GONE);
            hiddenContent.setVisibility(VISIBLE);
            buttons.setVisibility(VISIBLE);

            getSupportFragmentManager().beginTransaction().add(R.id.hiddenFrameLayout,
                    AnotherPrivate.class, null).commit();

        }
        super.onStart();
    }

    public void FragmentManager(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExtraContent fragment1 = new ExtraContent();
        AnotherPrivate fragment2 = new AnotherPrivate();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (view.getId() == R.id.key) {
            fragmentTransaction.replace(R.id.hiddenFrameLayout, fragment1, "fragment1");
        } if (view.getId() == R.id.shield) {
            fragmentTransaction.replace(R.id.hiddenFrameLayout, fragment2, "fragment2");
        }
        fragmentTransaction.commitNow();
    }

    @Override
    protected void onRestart() {
        startActivity(new Intent(this, ExperimentalSheesh.class));
        super.onRestart();
    }
}