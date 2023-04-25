package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Challenge {

    private static final AtomicInteger count = new AtomicInteger(0);
    private String id;
    private String title;
    private String creator;
    private String deadline;
    private String date_created;

    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    private List<String> participants;

    public Challenge(String title, String creator, String deadline, String date_created, String challengeType, String description) {
        this.id = String.valueOf(count.incrementAndGet());
        this.title = title;
        this.creator = creator;
        this.deadline = deadline;
        this.date_created = date_created;
        this.type = challengeType;
        this.description = description;
    }

    public Challenge() {}
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void addParticipant(String participantId) {
        participants.add(participantId);
    }
    public void removeParticipant(String participantId) {
        participants.remove(participantId);
    }
}
