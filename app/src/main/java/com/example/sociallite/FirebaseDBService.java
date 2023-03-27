package com.example.sociallite;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Challenge;
import com.example.model.Points;
import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDBService extends AppCompatActivity {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public FirebaseFirestore getDataBase(){
        return db;
    }

    // Add user OR overwrite user completely with a certain ID
    public void addUser(User u ) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", u.getID());
        user.put("first", u.getFirstname());
        user.put("last", u.getLastname());
        //user.put("password", u.getPassword());
        user.put("email", u.getEmail());
        user.put("challengesCreated", u.getCreatedChallenges());
        user.put("challengesJoined", u.getJoinedChallenges());

        db.collection("User").document(u.getID()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        Map<String, Object> user = new HashMap<>();
        user.put("id", challenge.getID());
        user.put("title", challenge.getTitle());
        user.put("creator", challenge.getCreator());
        user.put("deadline", challenge.getDeadline());
        user.put("date_created", challenge.getDate_created());
        user.put("participants", challenge.getParticipants());

        db.collection("Challenge").document(challenge.getID()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        Map<String, Object> user = new HashMap<>();
        user.put("id", points.getID());
        user.put("challengePoints", points.getChallengePoints());
        user.put("challengeID", points.getChallengeId());
        user.put("userID", points.getUserId());

        db.collection("Points").document(points.getID()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    // Read data, based on collection name and chosen ID
    public void read(String collectionName, String chosenID){
        DocumentReference docRef = db.collection(collectionName).document(chosenID);
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
