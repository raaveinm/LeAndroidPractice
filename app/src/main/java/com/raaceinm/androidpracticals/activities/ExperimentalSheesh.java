package com.raaceinm.androidpracticals.activities;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.Tools.DataTransfer;
import com.raaceinm.androidpracticals.Tools.Sterilization;

public class ExperimentalSheesh extends AppCompatActivity {
    int amountOfContinues = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experimental_sheesh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    protected void onStart(){
        super.onStart();

        Button hellYeah = findViewById(R.id.hlYeah);
        Button cContinue = findViewById(R.id.Continue);

        ImageView background = findViewById(R.id.imageView4);

        TextView textPhone = findViewById(R.id.editTextPhone);
        TextView textEmail = findViewById(R.id.editTextTextEmailAddress);
        TextView textPassword = findViewById(R.id.editTextTextPassword);
        TextView textName = findViewById(R.id.editTextName);
        TextView textAge = findViewById(R.id.editTextAge);

        hellYeah.setOnClickListener(v->{
            hellYeah.setVisibility(GONE);
            cContinue.setVisibility(VISIBLE);
        });

        cContinue.setOnClickListener(v->{
            switch (amountOfContinues){
                case 0: {
                    background.setImageResource(R.drawable.darkbackground);
                    textPhone.setVisibility(VISIBLE);
                    cContinue.setText("Continue");
                    amountOfContinues++;
                    break;
                }
                case 1: {
                    String phoneNumber = textPhone.getText().toString();
                    if (phoneNumber.equals("")) {
                        Toast.makeText(this, "NONONO", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (phoneNumber.equals("999")) {
                        textEmail.setVisibility(VISIBLE);
                        textPassword.setVisibility(VISIBLE);
                        textName.setVisibility(VISIBLE);
                        textAge.setVisibility(VISIBLE);
                        textEmail.setText("examplemail.gmail.com");
                        textPassword.setText("ADMINSUPERPASSWORD");
                        textName.setText("Admin");
                        textAge.setText("21");
                        cContinue.setText("Done");
                        amountOfContinues = 5;
                        break;
                    } else {
                        textEmail.setVisibility(VISIBLE);
                        amountOfContinues++;
                        break;
                    }
                }
                case 2: {
                    if (textEmail.getText().toString().equals("")){
                        Toast.makeText(this, "NONONO", Toast.LENGTH_SHORT).show();
                        break;
                    }else {
                        textPassword.setVisibility(VISIBLE);
                        amountOfContinues++;
                        break;
                    }
                }
                case 3: {
                    if (textPassword.getText().toString().equals("")){
                        Toast.makeText(this, "NONONO", Toast.LENGTH_SHORT).show();
                        break;
                    }else {
                        textName.setVisibility(VISIBLE);
                        amountOfContinues++;
                        break;
                    }
                }
                case 4: {
                    if (textName.getText().toString().equals("")) {
                        Toast.makeText(this, "NONONO", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        textAge.setVisibility(VISIBLE);
                        cContinue.setText("Done");
                        amountOfContinues++;
                        break;
                    }
                }
                case 5:{
                    if (textAge.getText().toString().equals("")){
                        Toast.makeText(this, "NONONO", Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        /*Using Parcelable

                        DataTransfer personalData = new DataTransfer(
                                Float.parseFloat(textPhone.getText().toString()),
                                textEmail.getText().toString(),
                                textPassword.getText().toString(),
                                textName.getText().toString(),
                                Integer.parseInt(textAge.getText().toString()));
                        */
                        //Using Serialization

                        Sterilization personalData = new Sterilization();
                        personalData.setPersonalData(
                                Float.parseFloat(textPhone.getText().toString()),
                                textEmail.getText().toString(),textPassword.getText().toString(),
                                textName.getText().toString(),
                                Integer.parseInt(textAge.getText().toString()));

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("Sterilization", personalData);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                        /* Using simple Intent

                        intent.putExtra("Phone", Float.parseFloat(textPhone.getText().toString()));
                        intent.putExtra("Email", textEmail.getText().toString());
                        intent.putExtra("Password", textPassword.getText().toString());
                        intent.putExtra("Name", textName.getText().toString());
                        intent.putExtra("Age", Integer.parseInt(textAge.getText().toString()));

                        */
                    }
                }
                default:
                    Snackbar.make(findViewById(R.id.main), "GG", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onResume(){
        super.onResume();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onRestart(){
        super.onRestart();
    }

    protected void onDestroy(){
        super.onDestroy();
    }
}