package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userEmail = mAuth.getCurrentUser().getEmail();

        TextView nameText = findViewById(R.id.name);
        nameText.setText("Firstname Lastname");

        TextView emailText = findViewById(R.id.email);
        emailText.setText(userEmail);

        Button profileButton = findViewById(R.id.back);
        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));;
        });

        Button editProfileButton = findViewById(R.id.edit);
        editProfileButton.setOnClickListener(view -> {
            startActivity(new Intent(MyProfileActivity.this,EditProfileActivity.class));;
        });

    }
}
