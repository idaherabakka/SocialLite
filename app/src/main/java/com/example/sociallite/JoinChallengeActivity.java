package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.service.ChallengeAdapter;

import java.util.ArrayList;
import java.util.List;

public class JoinChallengeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_challenge);

        List<Challenge> challenges = new ArrayList<>();
        /*
        FirebaseDBService dbService = new FirebaseDBService();
        dbService.read("Challenges", )
        //filtering etc...
        */


    }
}
