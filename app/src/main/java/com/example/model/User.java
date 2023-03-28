package com.example.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    public String firstname;
    public String lastname;

    public UUID getID() {
        return ID;
    }


    public UUID ID;
    private String email;

    private ArrayList<String> createdChallenges;
    private ArrayList<String> joinedChallenges;


    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ID = UUID.randomUUID();
        this.email = email;
        this.createdChallenges = new ArrayList<>();
        this.joinedChallenges = new ArrayList<>();
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
