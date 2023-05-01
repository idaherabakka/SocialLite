package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.service.ChallengeOverviewAdapter;
import com.example.service.FirebaseDBService;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private List<Challenge> challenges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        FirebaseDBService dbService = new FirebaseDBService();
        challenges = dbService.getAllChallenges();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChallengeOverviewAdapter adapter = new ChallengeOverviewAdapter(getApplicationContext(), challenges, new ChallengeOverviewAdapter.MyAdapterListener() {
            @Override
            public void buttonOnClick(View v, int position, TextView id) {
                String challengeID = (String) id.getText();
                Intent i = new Intent(OverviewActivity.this, ProgressionActivity.class);
                i.putExtra("challengeID", challengeID);
                startActivity(i);
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

        Button createButton = findViewById(R.id.Create);
        createButton.setOnClickListener(view -> {
            startActivity(new Intent(OverviewActivity.this, CreateChallengeActivity.class));
        });


    }

}
