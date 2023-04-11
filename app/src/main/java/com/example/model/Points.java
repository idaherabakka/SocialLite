package com.example.model;

public class Points {

    private String id;
    private int challengePoints;
    private String challengeId;
    private String userID;

    public Points(String id, int challengePoints, String challengeId, String userID) {
        this.id = id;
        this.challengePoints = challengePoints;
        this.challengeId = challengeId;
        this.userID = userID;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public int getChallengePoints() {
        return challengePoints;
    }

    public void setChallengePoints(int challengePoints) {
        this.challengePoints = challengePoints;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getUserId() {
        return userID;
    }

    public void setUserId(String userId) {
        this.userID = userId;
    }
}
