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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (!isValidName(firstName.getText().toString())) {
                firstName.setError("Not a valid name");
                firstName.requestFocus();
            }
            if (!isValidName(lastName.getText().toString())){
                lastName.setError("Not a valid name");
                lastName.requestFocus();
            }
            if(isValidName(firstName.getText().toString()) && isValidName(lastName.getText().toString())){
                User.setFirstname(firstName.getText().toString());
                User.setLastname(lastName.getText().toString());
                db.updateUser(User);
                startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));
            }
        });
        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));;
        });

    }
    public static boolean isValidName(String name)
    {
        if (name == null || name.isEmpty()) {
            return false; // empty string or null are not valid names
        }

        if (Character.isWhitespace(name.charAt(0))) {
            return false; // name cannot begin with a space
        }

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                return false; // name cannot contain digits
            }
        }

        return true; // all checks passed, name is valid
    }
}
