package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Challenge;
import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.example.service.JoinChallengeAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class JoinChallengeActivity extends AppCompatActivity {

    private List<Challenge> challenges;
    private JoinChallengeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_challenge);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        FirebaseDBService dbService = new FirebaseDBService();
        challenges = dbService.getAllChallenges();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String currentUser = mAuth.getCurrentUser().getEmail();
        User user = dbService.getUser(currentUser);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JoinChallengeAdapter(getApplicationContext(), challenges, new JoinChallengeAdapter.MyAdapterListener(){
            @Override
            public void buttonOnClick(View v, int position, TextView id) {
                String challengeID = (String) id.getText();
                Challenge challenge = dbService.getChallenge(challengeID);
                System.out.println(challenge.getTitle());
                User updatedUser = user.addChallenge(challenge);
                dbService.updateUser(updatedUser);
            }
        }, user);
        recyclerView.setAdapter(adapter);

        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(JoinChallengeActivity.this,OverviewActivity.class));
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });

    }


    private void filter(String text) {
        List<Challenge> filteredlist = new ArrayList<Challenge>();

        if (text != null) {
            for (Challenge challenge : challenges) {
                if (challenge.getID().contains(text)) {
                    filteredlist.add(challenge);
                }
            }
            if (filteredlist.isEmpty()) {
                System.out.println("No such challenges.");
            } else {
                adapter.filterList(filteredlist);
            }
        }



    }
}
