package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.google.firebase.auth.FirebaseAuth;

public class MyProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    FirebaseDBService db = new FirebaseDBService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);


        String email = mAuth.getCurrentUser().getEmail();
        User currentUser = db.getUser(mAuth.getCurrentUser().getEmail());
        String firstname = currentUser.getFirstname();
        String lastname = currentUser.getLastname();


        TextView nameText = findViewById(R.id.name);
        nameText.setText(firstname + " " + lastname);

        TextView emailText = findViewById(R.id.email);
        emailText.setText(email);


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
