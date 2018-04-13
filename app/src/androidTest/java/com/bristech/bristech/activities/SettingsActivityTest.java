package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> mActivityRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btn_log_out)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.btn_modify_interests)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.btn_edit_user_settings)).check(matches(isClickable()));
    }
}