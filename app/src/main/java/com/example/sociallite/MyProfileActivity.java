package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Adapter;
import com.example.model.Challenge;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        User currentUser = new User(mAuth);

        TextView title = findViewById(R.id.title);
        title.setText("My Profile");

        TextView email = findViewById(R.id.email);
        email.setText(currentUser.getEmail());

        Button profileButton = findViewById(R.id.back);
        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(MyProfileActivity.this,OverviewActivity.class));;
        });

    }
}
