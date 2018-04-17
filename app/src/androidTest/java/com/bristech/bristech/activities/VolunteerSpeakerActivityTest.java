package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class VolunteerSpeakerActivityTest {
    private static final String name = "Aneesh";
    private static final String email = "123456789@bristol.ac.uk";
    private static final String topic = "Testing";

    @Rule
    public ActivityTestRule<VolunteerSpeakerActivity> mActivityRule = new ActivityTestRule<>(VolunteerSpeakerActivity.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btn_submit)).check(matches(isClickable()));
    }

    @Test
    public void testText(){
        Espresso.onView(withId(R.id.speaker_email)).perform(typeText(email), closeSoftKeyboard());
        Espresso.onView(withId(R.id.speaker_email)).check(matches(withText(email)));
        Espresso.onView(withId(R.id.speaker_name)).perform(typeText(name), closeSoftKeyboard());
        Espresso.onView(withId(R.id.speaker_name)).check(matches(withText(name)));
        Espresso.onView(withId(R.id.speaker_topic)).perform(typeText(topic), closeSoftKeyboard());
        Espresso.onView(withId(R.id.speaker_topic)).check(matches(withText(topic)));
    }


}