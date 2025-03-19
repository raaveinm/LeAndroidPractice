package com.raaceinm.androidpracticals.activities;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.raaceinm.androidpracticals.Tools.ComponentsKt.getGPUData;
import static com.raaceinm.androidpracticals.Tools.ComponentsKt.setGPUDataSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.fragments.AddNewCard;
import com.raaceinm.androidpracticals.fragments.AnotherPrivate;
import com.raaceinm.androidpracticals.fragments.ContentResolver;
import com.raaceinm.androidpracticals.fragments.ListViewFragment;

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
        super.onStart();

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
        } else {
            username.setVisibility(GONE);
            password.setVisibility(GONE);
            login.setVisibility(GONE);
            hiddenContent.setVisibility(VISIBLE);
            buttons.setVisibility(VISIBLE);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.hiddenFrameLayout, AnotherPrivate.class, null)
                    .commit();
        }
    }

    public void FragmentManager(View view) {
        TextView textView = findViewById(R.id.newGpuName);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (view.getId() == R.id.key) {
            fragmentTransaction.replace(R.id.hiddenFrameLayout, new ContentResolver(), "fragment1");
            Log.d("EEE", "pressed key");
        } else if (view.getId() == R.id.shield) {
            fragmentTransaction.replace(R.id.hiddenFrameLayout, new AnotherPrivate(), "fragment2");
            Log.d("EEE", "pressed shield");
        } else if (view.getId() == R.id.addGPU) {
            fragmentTransaction.replace(R.id.hiddenFrameLayout, new AddNewCard(), "fragment3");
            Log.d("EEE", "pressed addGPU");
        } else if (view.getId() == R.id.confirm) {
            ReNewList(textView.getText().toString());
            fragmentTransaction.replace(R.id.hiddenFrameLayout, new ContentResolver(), "fragment1");
            Log.d("EEE", "pressed confirm");
        } else {
            Log.e("EEE", "Unknown button pressed");
        }
        fragmentTransaction.commit();
    }

    private void ReNewList(String NewItem) {
        if (NewItem.isEmpty()) {
            Snackbar.make(this.findViewById(android.R.id.content), "please enter a name",
                    Snackbar.LENGTH_LONG).show();
        } else {
            setGPUDataSet(NewItem);
            FragmentManager fragmentManager = getSupportFragmentManager();
            ListViewFragment listViewFragment = (ListViewFragment) fragmentManager.findFragmentByTag("fragment1");

            if(listViewFragment != null){
                listViewFragment.updateList();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(this, ExperimentalSheesh.class));
    }
}