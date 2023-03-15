package com.example.sociallite;
import com.example.sociallite.DatabaseActivity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.View;
import android.widget.Button;

import com.example.model.Challenge;
import com.example.model.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        DatabaseActivity db2 = new DatabaseActivity();
        //db2.deleteUser();
        //db2.read();

        Button answersBtn = findViewById(R.id.signIn);
        answersBtn.setOnClickListener(view -> {
            db2.addUser(new User("123123", "added", 1999,"1","123","dn@hotmail.com"));

        });
    }
}