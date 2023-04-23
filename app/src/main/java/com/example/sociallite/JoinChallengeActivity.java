package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.service.ChallengeOverviewAdapter;
import com.example.service.ClickListener;
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
        JoinChallengeAdapter adapter = new JoinChallengeAdapter(getApplicationContext(), challenges, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                // TODO: when clicked challenge should be joined.
            }
        });
        recyclerView.setAdapter(adapter);

        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(JoinChallengeActivity.this,OverviewActivity.class));
        });

    }
}
