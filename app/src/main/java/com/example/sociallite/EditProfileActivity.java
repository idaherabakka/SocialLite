package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        Button profileButton = findViewById(R.id.cancel);
        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(EditProfileActivity.this,MyProfileActivity.class));;
        });



    }
}
