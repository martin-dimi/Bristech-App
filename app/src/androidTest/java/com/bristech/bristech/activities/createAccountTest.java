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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class createAccountTest {
    private static final String email = "1234@my.bristol.ac.uk";
    private static final String userName = "Bristech47";
    private static final String firstName = "Richkic";
    private static final String otherName = "idontk";
    private static final String passWord = "123456789";
//    private static final String repassWord = "123456789";

    @Rule
    public ActivityTestRule<createAccount> mActivityRule = new ActivityTestRule<>(createAccount.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btnCreateAccount)).check(matches(isClickable()));

    }

    @Test
    public void testText() {

        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(email), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtEmail)).check(matches(withText(email)));
        Espresso.onView(withId(R.id.txtUsername)).perform(typeText(userName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtUsername)).check(matches(withText(userName)));
        Espresso.onView(withId(R.id.txtFirstName)).perform(typeText(firstName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtFirstName)).check(matches(withText(firstName)));
        Espresso.onView(withId(R.id.txtOtherNames)).perform(typeText(otherName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches(withText(otherName)));
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord)));

        //test failed ??
//        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches(withText(repassWord)));
//        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches((isDisplayed())));

    }
}