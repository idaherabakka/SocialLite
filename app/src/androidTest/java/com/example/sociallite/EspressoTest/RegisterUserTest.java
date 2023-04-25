package com.example.sociallite.EspressoTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.example.sociallite.MyProfileActivity.isValidName;

import android.view.View;

import androidx.test.espresso.FailureHandler;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.sociallite.MainActivity;
import com.example.sociallite.R;
import com.example.sociallite.RegisterUser;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.UUID;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterUserTest {
    @Rule
    public ActivityScenarioRule<RegisterUser> mainActivityRule = new ActivityScenarioRule<>(RegisterUser.class);

    @Test
    public void createUser(){
        UUID id = UUID.randomUUID();
        onView(withId(R.id.firstName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.lastName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.email)).perform(replaceText(id.toString() + "@123.no"));
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.registerUser)).perform(click());
    }
    @Test
    public void invalidEmailError(){
        onView(withId(R.id.firstName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.lastName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.email)).perform(replaceText(""));
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.registerUser)).perform(click());
        onView(withId(R.id.email)).check(matches(hasFocus()));
    }
    @Test
    public void toShortPasswordTest(){
        onView(withId(R.id.firstName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.lastName)).perform(replaceText(generateRandomName(8)));
        onView(withId(R.id.email)).perform(replaceText(  "12@123.no"));
        onView(withId(R.id.password)).perform(replaceText(""));
        onView(withId(R.id.registerUser)).perform(click());
        onView(withId(R.id.password)).check(matches(hasFocus()));

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
