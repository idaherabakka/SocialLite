package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public String firstname;
    public String lastname;
    private String email;
    public ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<String> challengesCreated;
    private ArrayList<String> challengesJoined;


    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.challengesCreated = new ArrayList<>();
        this.challengesJoined = new ArrayList<>();
        allUsers.add(this);
    }
    public User(){}
    public User getUser(String email){
        for (User x : allUsers){
            if(email.equals(x.getEmail())){
                return x;
            }
        }
        return null;
    }
    public HashMap<String,User> getAllUsers(){return getAllUsers();}
    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
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

    public ArrayList<String> getChallengesCreated() {
        return challengesCreated;
    }

    public void setChallengesCreated(ArrayList<String> challengesCreated) {
        this.challengesCreated = challengesCreated;
    }

    public ArrayList<String> getChallengesJoined() {
        return challengesJoined;
    }

    public void setChallengesJoined(ArrayList<String> challengesJoined) {
        this.challengesJoined = challengesJoined;
    }

    public User addChallenge(Challenge challenge) {
        this.challengesJoined.add(challenge.getID());
        return this;
    }

    public void addJoined(String challengeId){
        challengesJoined.add(challengeId);
    }

    public void removeJoined(String challengeId) {
        challengesJoined.remove(challengeId);
    }

    public void addCreated(String challengeId){
        challengesJoined.add(challengeId);
    }

    public void removeCreated(String challengeId) {
        challengesJoined.remove(challengeId);
    }


}
