package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.bt_sign_in)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.btn_google_sign_in)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.btn_facebook_sign_in)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.btn_logout)).check(matches(isClickable()));
    }
}