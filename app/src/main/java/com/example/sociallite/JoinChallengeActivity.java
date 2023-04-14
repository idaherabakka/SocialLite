package com.example.sociallite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.service.ChallengeOverviewAdapter;
import com.example.service.FirebaseDBService;
import com.example.service.JoinChallengeAdapter;

import java.util.List;

public class JoinChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_challenge);

        List<Challenge> challenges;
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        FirebaseDBService dbService = new FirebaseDBService();
        challenges = dbService.getAllChallenges();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new JoinChallengeAdapter(getApplicationContext(),challenges));

    }
}
