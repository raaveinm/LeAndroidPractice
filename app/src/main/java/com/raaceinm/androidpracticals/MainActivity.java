package com.raaceinm.androidpracticals;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView2);

        Vid vid = new Vid(videoView, this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button myButton = findViewById(R.id.button);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        myButton.setOnClickListener(v -> {
            String TextFromAutocomplete = autoCompleteTextView.getText().toString();
            Toast.makeText(this, "Redirecting to: " + TextFromAutocomplete, Toast.LENGTH_SHORT).show();

            String url = "https://" + TextFromAutocomplete;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}