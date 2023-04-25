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

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userEmail = mAuth.getCurrentUser().getEmail();

        TextView nameText = findViewById(R.id.name);
        nameText.setText("Firstname Lastname");
        String currentUser = mAuth.getCurrentUser().getEmail();
        User = db.getUser(mAuth.getCurrentUser().getEmail());

        TextView nameText = findViewById(R.id.name);
        nameText.setText("Firstname Lastname");

        TextView emailText = findViewById(R.id.email);
        emailText.setText(userEmail);

        TextView emailText = findViewById(R.id.email);
        emailText.setText(userEmail);

        TextView email = findViewById(R.id.email);
        email.setText(currentUser);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        if(User.getFirstname() != null && User.getLastname()!= null){
            firstName.setText(User.getFirstname());
            lastName.setText(User.getLastname());
        }
        Button updateProfile = findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(view -> {
            if (TextUtils.isEmpty(firstName.getText().toString())) {
                this.firstName.setError("First Name cannot be empty");
                this.firstName.requestFocus();
            }
            if (TextUtils.isEmpty(lastName.getText().toString())){
                this.lastName.setError("Last Name cannot be empty");
                this.lastName.requestFocus();
            }
            else {
                User.firstname = firstName.getText().toString();
                User.lastname = lastName.getText().toString();
                db.updateUser(User);
                startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));
            }
        });
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
