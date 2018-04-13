package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class VolunteerSpeakerActivityTest {

    @Rule
    public ActivityTestRule<VolunteerSpeakerActivity> mActivityRule = new ActivityTestRule<>(VolunteerSpeakerActivity.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btn_submit)).check(matches(isClickable()));
    }
}