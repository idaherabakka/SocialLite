package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.model.Points;
import com.example.service.ChallengeOverviewAdapter;
import com.example.service.ClickListener;
import com.example.service.LeaderboardAdapter;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Points> points = new ArrayList<>();
        //firebaseDBService .getAllPoints
        points.add(new Points(4, "ida.her@live.no"));
        points.add(new Points(5, "olesya"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LeaderboardAdapter adapter = new LeaderboardAdapter(getApplicationContext(), points);
        recyclerView.setAdapter(adapter);


    }

}
