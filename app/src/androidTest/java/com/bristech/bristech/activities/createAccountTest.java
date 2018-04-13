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

public class createAccountTest {

    @Rule
    public ActivityTestRule<createAccount> mActivityRule = new ActivityTestRule<>(createAccount.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btnCreateAccount)).check(matches(isClickable()));

    }

    @Test
    public void testText() {

        Espresso.onView(withId(R.id.txtEmail)).check(matches((isDisplayed())));
        Espresso.onView(withId(R.id.txtUsername)).check(matches((isDisplayed())));
        Espresso.onView(withId(R.id.txtFirstName)).check(matches((isDisplayed())));
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches((isDisplayed())));
        Espresso.onView(withId(R.id.txtPassword)).check(matches((isDisplayed())));
        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches((isDisplayed())));

    }
}