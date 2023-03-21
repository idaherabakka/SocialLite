package com.example.sociallite;

public class User {
    public String firstname;
    public String lastname;
    public int age;
    public String ID;
    private String password;
    private String email;

    public User(String firstname, String lastname, int age, String ID, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.ID = ID;
        this.password = password;
        this.email = email;
    }
}
