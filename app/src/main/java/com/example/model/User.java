package com.example.model;


import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    String username;
    public String firstname;
    public String lastname;
    public String ID;

    private String password;

    private String email;

    FirebaseAuth mAuth;

    public User(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
        username = mAuth.getCurrentUser().getDisplayName();
        email = mAuth.getCurrentUser().getEmail();
    }


    private ArrayList<String> createdChallenges;
    private ArrayList<String> joinedChallenges;

    public User(String firstname, String lastname, String ID, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getCreatedChallenges() {
        return createdChallenges;
    }

    public void setCreatedChallenges(ArrayList<String> createdChallenges) {
        this.createdChallenges = createdChallenges;
    }

    public ArrayList<String> getJoinedChallenges() {
        return joinedChallenges;
    }

    public void setJoinedChallenges(ArrayList<String> joinedChallenges) {
        this.joinedChallenges = joinedChallenges;
    }

    public void addJoined(String challengeId){
        joinedChallenges.add(challengeId);
    }

    public void removeJoined(String challengeId) {
        joinedChallenges.remove(challengeId);
    }

    public void addCreated(String challengeId){
        joinedChallenges.add(challengeId);
    }

    public void removeCreated(String challengeId) {
        joinedChallenges.remove(challengeId);
    }


}
