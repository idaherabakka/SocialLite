package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Challenge {
    private String id;
    private String title;
    private String creator;
    private Date deadline;
    private Date date_created;

    private List<String> participants;

    public Challenge(String title, String creator) {
        this.title = title;
        this.creator = creator;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public void addParticipant(String participantId) {
        participants.add(participantId);
    }
    public void removeParticipant(String participantId) {
        participants.remove(participantId);
    }
}
