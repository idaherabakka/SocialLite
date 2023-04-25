package com.example.service;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.model.Challenge;
import com.example.model.Points;
import com.example.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FirebaseDBService {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public FirebaseDBService() {


    }
    public FirebaseFirestore getDataBase(){
        return db;
    }

    //get : return User object

    // Add user OR overwrite user completely with a certain ID
    public void addUser(User u ) {
        Map<String, Object> user = new HashMap<>();
        user.put("firstname", u.getFirstname());
        user.put("lastname", u.getLastname());
        //email as id
        user.put("email", u.getEmail());
        user.put("challengesCreated", u.getChallengesCreated());
        user.put("challengesJoined", u.getChallengesJoined());

        db.collection("User").document(u.getEmail()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    // Add challenge OR overwrite challenge completely with a certain ID
    public void addChallenge(Challenge challenge ) {
        Map<String, Object> challenges = new HashMap<>();
        challenges.put("id", challenge.getID());
        challenges.put("title", challenge.getTitle());
        challenges.put("creator", challenge.getCreator());
        challenges.put("deadline", challenge.getDeadline());
        challenges.put("date_created", challenge.getDate_created());
        challenges.put("participants", challenge.getParticipants());

        db.collection("Challenge").document(challenge.getID()).set(challenges).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    // Add points OR overwrite points completely with a certain ID
    public void addPoints(Points points) {
        Map<String, Object> point = new HashMap<>();
        point.put("id", points.getID());
        point.put("challengePoints", points.getChallengePoints());
        point.put("challengeID", points.getChallengeId());
        point.put("userID", points.getUserId());

        db.collection("Points").document(points.getID()).set(point).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    // Update a field in a document that is not an array, needs collection name, id you wish to update, field and its value you wish to update
    public void updateDocument(String collectionName, String idToBeUpdated, String fieldToBeUpdated, String newValueForField) {

        // The document that needs to be updated
        DocumentReference toBeUpdatedDocument = db.collection(collectionName).document(idToBeUpdated);

        toBeUpdatedDocument
                .update(fieldToBeUpdated, newValueForField) // what field to update and the value
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    // Update a field in a document that is an array, needs collection name, id you wish you update, field and its value you wish to update and weather the action is an add or delete
    public void updateDocumentArrayField(String collectionName, String toBeUpdatedID, String fieldToBeUpdated, String valueForField, String addOrDelete) {

        // The document that needs to be updated
        DocumentReference toBeUpdatedDocument = db.collection(collectionName).document(toBeUpdatedID);

        if (addOrDelete.equals("add")) {
            // Atomically add a new value to the array field you wish to update
            toBeUpdatedDocument.update(fieldToBeUpdated, FieldValue.arrayUnion(valueForField));
        }

        if (addOrDelete.equals("delete")) {
            // Atomically remove an existing value from the array field you wish to update
            toBeUpdatedDocument.update(fieldToBeUpdated, FieldValue.arrayRemove(valueForField));
        }

    }

    // Delete data, based on collection name and chosen ID
    public void deleteDocument(String collectionName, String chosenID){
        db.collection(collectionName).document(chosenID)
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

    ArrayList<Challenge> challenges = new ArrayList<>();
    //Read all documents by collectionName
    public ArrayList<Challenge> getAllChallenges() {
        Task<QuerySnapshot> colRef = db.collection("Challenge").get();
        while (!colRef.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        for (QueryDocumentSnapshot document : colRef.getResult()) {
            Challenge currentChallenge = document.toObject(Challenge.class);
            challenges.add(currentChallenge);
        }
        return challenges;
    }
    Challenge challenge = new Challenge();
    // Read data, based on collection name and chosen ID
    public Challenge getChallenge(String chosenID){
        DocumentReference docRef = db.collection("Challenge").document(chosenID);
        Task<DocumentSnapshot> task = docRef.get();
        while (!task.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        DocumentSnapshot snapshot = task.getResult();
        challenge = snapshot.toObject(Challenge.class);
        return challenge;
    }

    ArrayList<Points> points = new ArrayList<>();
    //Read all documents by collectionName
    public ArrayList<Challenge> getAllPoints() {
        Task<QuerySnapshot> colRef = db.collection("Points").get();
        while (!colRef.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        for (QueryDocumentSnapshot document : colRef.getResult()) {
            Points currentPoints = document.toObject(Points.class);
            points.add(currentPoints);
        }
        return challenges;
    }
    Points point = new Points();
    // Read data, based on collection name and chosen ID
    public Points getPoints(String chosenID){
        DocumentReference docRef = db.collection("Points").document(chosenID);
        Task<DocumentSnapshot> task = docRef.get();
        while (!task.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        DocumentSnapshot snapshot = task.getResult();
        point = snapshot.toObject(Points.class);
        return point;
    }

    ArrayList<User> users = new ArrayList<>();
    //Read all documents by collectionName
    public ArrayList<User> getAllUsers() {
        Task<QuerySnapshot> colRef = db.collection("User").get();
        while (!colRef.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        for (QueryDocumentSnapshot document : colRef.getResult()) {
            User currentUser = document.toObject(User.class);
            users.add(currentUser);
        }
        return users;
    }
    User user = new User();
    // Read data, based on collection name and chosen ID
    public User getUser(String chosenID){
        DocumentReference docRef = db.collection("User").document(chosenID);
        Task<DocumentSnapshot> task = docRef.get();
        while (!task.isComplete()) {
            try {
                TimeUnit.SECONDS.sleep(1); //forbedringspotensiale:))
            }
            catch(InterruptedException e){
                break;
            }
        }
        DocumentSnapshot snapshot = task.getResult();
        user = snapshot.toObject(User.class);
        return user;
    }

    public void updateUser(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("firstname", user.getFirstname());
        userMap.put("lastname", user.getLastname());
        userMap.put("challengesJoined", user.getChallengesJoined());

        db.collection("User").document(mAuth.getCurrentUser().getEmail()).update(userMap);
    }
}
