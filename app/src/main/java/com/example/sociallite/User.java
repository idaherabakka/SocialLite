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

    public User(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
        username = mAuth.getCurrentUser().getDisplayName();
        email = mAuth.getCurrentUser().getEmail();
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

    public String getEmail() {
        return email;
    }
}
