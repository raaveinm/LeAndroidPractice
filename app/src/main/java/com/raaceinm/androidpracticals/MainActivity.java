package com.raaceinm.androidpracticals;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
        });

    }

    @Override
    protected void onStart() {

        Button first = findViewById(R.id.frost);
        Button second = findViewById(R.id.smoke);
        Button third = findViewById(R.id.thermite);
        View firstFragment = findViewById(R.id.firstFragment);
        View secondFragment = findViewById(R.id.SecondFragment);
        View thirdFragment = findViewById(R.id.ThirdFragment);

        getSupportFragmentManager().beginTransaction().add
                (R.id.SecondFragment, new SecondFragment()).commit();

        first.setOnClickListener(v -> {
            firstFragment.setVisibility(View.VISIBLE);
            secondFragment.setVisibility(View.GONE);
            thirdFragment.setVisibility(View.GONE);
        });

        second.setOnClickListener(v -> {
            firstFragment.setVisibility(View.GONE);
            secondFragment.setVisibility(View.VISIBLE);
            thirdFragment.setVisibility(View.GONE);

        });
        third.setOnClickListener(v -> {
            firstFragment.setVisibility(View.GONE);
            secondFragment.setVisibility(View.GONE);
            thirdFragment.setVisibility(View.VISIBLE);
        });

        super.onStart();
    }
}