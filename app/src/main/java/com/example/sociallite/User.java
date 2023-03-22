package com.example.sociallite;


import com.google.firebase.auth.FirebaseAuth;

public class User {

    String username;
    public String firstname;
    public String lastname;
    public int age;
    public String ID;

    private String password;

    private String email;

    FirebaseAuth mAuth;

    public User() {
        mAuth = FirebaseAuth.getInstance();
        username = mAuth.getCurrentUser().getDisplayName();
    }

    public User(String firstname, String lastname, int Age, String ID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }
}
