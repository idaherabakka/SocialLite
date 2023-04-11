package com.example.sociallite.EspressoTest;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.example.sociallite.MainActivity;
import com.example.sociallite.OverviewActivity;
import com.example.sociallite.R;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);
@Before
public void setUp(){
    Espresso.closeSoftKeyboard();
    closeSoftKeyboard();
    ViewActions.clearGlobalAssertions();
}

    @Test
    public void loginTest() {
        // email and password for test user
        onView(withId(R.id.email)).perform(replaceText("123@123.no"));
        onView(withId(R.id.password)).perform(replaceText("123456"));

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // Click the login button to log in
        onView(withId(R.id.signIn)).perform(click());

        // Checks that the user is logged in to firebase with the same email
        Assert.assertEquals("123@123.no",mAuth.getCurrentUser().getEmail());
    }

    @Test
    public void testRegisterButton() {
        onView(withId(R.id.register)).perform(click());
    }

    @Test
    public void testForgotPassword() {

    onView(withId(R.id.forgotPassword)).perform(click());
    }
}