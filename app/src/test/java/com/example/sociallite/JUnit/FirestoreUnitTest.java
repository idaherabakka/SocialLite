package com.example.sociallite.JUnit;

import org.junit.Assert;
import org.junit.Test;

import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import com.example.model.Challenge;
import com.example.model.Points;
import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FirestoreUnitTest{
        public FirebaseDBService db;
        public FirebaseAuth mAuth;
        @Before
        public void setUp(){
            db = new FirebaseDBService();
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword("123@123.no","123456");
        }
        @Test
        public void testAddUser(){
            // create a new user document with a random ID
         /*   User user = new User("Alice","Bob", "alice@example.com");
            db.addUser(user);
            User checkedUser = db.getUser(user.getEmail());
            Assert.assertEquals(user.firstname,checkedUser.firstname);
            Assert.assertEquals(user.lastname,checkedUser.lastname);
            Assert.assertEquals(user.getEmail(),checkedUser.getEmail());*/
        }

        @Test
        public void testAddChallenge(){
            // create a new challenge document with a random ID
            /*
            Challenge challenge = new Challenge("Run a 5k", "alice@example.com");
            */
        }

        @Test
        public void testAddPoints(){
            // create a new points document with a random ID
            /*
            Points points = new Points("alice@example.com", 50,"10","alice@example.com");
            */
        }
}
