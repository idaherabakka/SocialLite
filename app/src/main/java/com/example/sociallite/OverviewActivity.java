package com.example.sociallite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.model.MyAdapter;

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
        challenges.add(new Challenge("Go to gym", "Lisa"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),challenges));

    }

}
