package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class FeedbackActivityTest {

    @Rule
    public ActivityTestRule<FeedbackActivity> mActivityRule = new ActivityTestRule<>(FeedbackActivity.class);

    @Test
    public void testButton() {
        Espresso.onView(withId(R.id.btn_return_to_homepage)).check(matches(isClickable()));
    }
}