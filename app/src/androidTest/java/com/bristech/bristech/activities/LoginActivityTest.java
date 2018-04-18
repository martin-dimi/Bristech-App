package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginActivityTest {
    private static final String userName = "Bristech47";
    private static final String passWord = "123456789";

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testButtonSignIn() {
        Espresso.onView(withId(R.id.bt_sign_in)).check(matches(isClickable()));
    }

    @Test
    public void testButtonGoogle() {
        Espresso.onView(withId(R.id.btn_google_sign_in)).check(matches(isClickable()));
    }

    @Test
    public void testButtonFacebook() {
        Espresso.onView(withId(R.id.btn_facebook_sign_in)).check(matches(isClickable()));
    }

    @Test
    public void testButtonLogout() {
        Espresso.onView(withId(R.id.btn_logout)).check(matches(isClickable()));
    }

    @Test
    public void testLogin(){
        Espresso.onView(withId(R.id.et_username)).perform(typeText(userName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.et_username)).check(matches(withText(userName)));
        Espresso.onView(withId(R.id.et_password)).perform(typeText(passWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.et_password)).check(matches(withText(passWord)));
        Espresso.onView(withId(R.id.bt_sign_in)).check(matches(isClickable()));
    }
}