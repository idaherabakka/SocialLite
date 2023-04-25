package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.service.ChallengeOverviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Challenge> challenges = new ArrayList<>();
        challenges.add(new Challenge("Walk 30km", "Lisa"));
        challenges.add(new Challenge("Save 50k", "Thomas"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChallengeOverviewAdapter adapter = new ChallengeOverviewAdapter(getApplicationContext(), challenges, new ChallengeOverviewAdapter.MyAdapterListener() {
            @Override
            public void buttonOnClick(View v, int position, TextView challengeID) {
                // TODO: Implement this to go to challenge
            }
        });
        recyclerView.setAdapter(adapter);

        Button profileButton = findViewById(R.id.MyProfile);
        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(OverviewActivity.this,MyProfileActivity.class));
        });

        Button joinButton = findViewById(R.id.Join);
        joinButton.setOnClickListener(view -> {
            startActivity(new Intent(OverviewActivity.this,JoinChallengeActivity.class));
        });


    }

}
