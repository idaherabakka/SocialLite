package com.example.sociallite.EspressoTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.example.sociallite.MyProfileActivity.isValidName;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.example.sociallite.ForgotPassword;
import com.example.sociallite.MyProfileActivity;
import com.example.sociallite.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.UUID;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditUserTest {
    @Rule
    public ActivityScenarioRule<MyProfileActivity> MyProfileActivityRule = new ActivityScenarioRule<>(MyProfileActivity.class);
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDBService db = new FirebaseDBService();

    @Before
    public void setUp(){
        mAuth.signInWithEmailAndPassword("d@d.no","123456");
    }

    @Test
    public void changeFirstName(){
        User currentUser = db.getUser(mAuth.getCurrentUser().getEmail()); //Gets logged in user
        Assert.assertEquals(currentUser.getEmail(),"d@d.no"); //Checks that we have logged in the right user
        String firstname = db.getUser(mAuth.getCurrentUser().getEmail()).getFirstname(); //Gets current firstname of user
        String random = generateRandomName(8); //Generates random name
        onView(withId(R.id.firstName)).perform(replaceText(random)); //Replaces firstname with the random one
        onView(withId(R.id.updateProfile)).perform(click()); //Clicks button to update
        Assert.assertEquals(db.getUser(mAuth.getCurrentUser().getEmail()).firstname,random); //Checks that the new firstname is the random one
        Assert.assertNotEquals(firstname,db.getUser(mAuth.getCurrentUser().getEmail()).firstname);// Checks that the first firstname is not the new
    }

    @Test
    public void changeLastName(){
        User currentUser = db.getUser(mAuth.getCurrentUser().getEmail()); //Gets logged in user
        Assert.assertEquals(currentUser.getEmail(),"d@d.no"); //Checks that we have logged in the right user
        String lastname = db.getUser(mAuth.getCurrentUser().getEmail()).getLastname(); //Gets current lastname of user
        String random = generateRandomName(8); //Generates random name
        onView(withId(R.id.lastName)).perform(replaceText(random)); //Replaces lastname with the random one
        onView(withId(R.id.updateProfile)).perform(click()); //Clicks button to update
        Assert.assertEquals(db.getUser(mAuth.getCurrentUser().getEmail()).lastname,random); //Checks that the new lastname is the random one
        Assert.assertNotEquals(lastname,db.getUser(mAuth.getCurrentUser().getEmail()).lastname);// Checks that the first lastname is not the first lastname
    }
    @Test
    public void errorWhenEmptyFirstName(){
        onView(withId(R.id.firstName)).perform(replaceText(""));
        Espresso.closeSoftKeyboard();
        closeSoftKeyboard();
        onView(withId(R.id.updateProfile)).perform(click());
        onView(withId(R.id.firstName)).check(matches(hasFocus()));
    }
    @Test
    public void errorWhenEmptyLastName(){
        onView(withId(R.id.lastName)).perform(replaceText(""));
        Espresso.closeSoftKeyboard();
        closeSoftKeyboard();
        onView(withId(R.id.updateProfile)).perform(click());
        onView(withId(R.id.lastName)).check(matches(hasFocus()));
    }

    public static String generateRandomName(int length) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = validChars.charAt(random.nextInt(validChars.length()));
            builder.append(c);
        }

        String name = builder.toString().trim(); // remove leading/trailing spaces

        if (isValidName(name)) {
            return name; // return the generated name if it is valid
        } else {
            return generateRandomName(length); // try again recursively
        }
    }
}
