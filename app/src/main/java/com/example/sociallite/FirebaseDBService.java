package com.example.sociallite;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Challenge;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDBService extends AppCompatActivity {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirebaseFirestore getDataBase(){
        return db;
    }

    public void DatabaseActivity(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void addUser(User u ) {
        Map<String, Object> user = new HashMap<>();
        user.put("first", u.getFirstname());
        user.put("last", u.getLastname());
        user.put("email", u.getEmail());

        // Add a new document with a generated ID
        db.collection("User").document(u.ID).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    //Add challenge
    public void addChallenge(Challenge challengeObj) {
        Map<String, Object> challenge = new HashMap<>();
        challenge.put("title", challengeObj.getTitle());
        challenge.put("creator", challengeObj.getCreator());
        challenge.put("deadline", challengeObj.getDeadline());
        challenge.put("created", challengeObj.getDate_created());

        // Add a new document with a generated ID
        db.collection("User").document(us.ID).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
        //Delete data
    public static void deleteUser(){
        db.collection("users").document("E4rrw8ugMPvcQwPciOYR")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }

    //Read data
    public void read(){
        DocumentReference docRef = db.collection("users").document("3JHDbQDMJJVkrTzGgJ77");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
