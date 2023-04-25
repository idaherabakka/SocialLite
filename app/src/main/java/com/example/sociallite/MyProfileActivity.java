package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.model.Challenge;
import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    User User;
    FirebaseDBService db = new FirebaseDBService();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        String currentUser = mAuth.getCurrentUser().getEmail();
        User = db.getUser(mAuth.getCurrentUser().getEmail());

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
            startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));
        });

    }
}
