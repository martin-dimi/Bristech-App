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
import static org.hamcrest.CoreMatchers.containsString;


public class createAccountTest {
    private static final String emailValid = "1234@my.bristol.ac.uk";
    private static final String userName = "Bristech47";
    private static final String firstName = "Richkic";
    private static final String otherName = "idontk";
    private static final String passWord = "123456789";
    private static final String retypePassWord = "123456789";
    private static final String invalidEmail = "fdlghbdakb";
    private static final String retypePassWordInvalid = "abcde";
//    private static final String userName2 = "google";
//    private static final String firstName2 = "bristol";
//    private static final String otherName2 = "university";
//    private static final String passWord2 = "abcde";



    @Rule
    public ActivityTestRule<createAccount> mActivityRule = new ActivityTestRule<>(createAccount.class);

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.btnCreateAccount)).check(matches(isClickable()));

    }

    @Test
    public void testValidEmail() {
        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(emailValid), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtEmail)).check(matches(withText(emailValid)));
    }

    @Test
    public void testInvalidEmail() {
        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(invalidEmail), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtEmail)).check(matches(withText(containsString("@"))));
    }

    @Test
    public void testValidUsername() {
        Espresso.onView(withId(R.id.txtUsername)).perform(typeText(userName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtUsername)).check(matches(withText(userName)));
    }

    @Test
    public void testFirstName() {
        Espresso.onView(withId(R.id.txtFirstName)).perform(typeText(firstName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtFirstName)).check(matches(withText(firstName)));
    }

    @Test
    public void testOtherNames() {
        Espresso.onView(withId(R.id.txtOtherNames)).perform(typeText(otherName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches(withText(otherName)));
    }

    @Test
    public void testPassword() {
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord)));
    }

    @Test
    public void testRetpyePassword() {
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches(withText(passWord)));
    }

    @Test
    public void testRetypePasswordInvalid() {
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWordInvalid), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches(withText(passWord)));
    }

    @Test
    public void testAccountCreation() {

        Espresso.onView(withId(R.id.txtEmail)).perform(typeText(emailValid), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtEmail)).check(matches(withText(emailValid)));
        Espresso.onView(withId(R.id.txtUsername)).perform(typeText(userName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtUsername)).check(matches(withText(userName)));
        Espresso.onView(withId(R.id.txtFirstName)).perform(typeText(firstName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtFirstName)).check(matches(withText(firstName)));
        Espresso.onView(withId(R.id.txtOtherNames)).perform(typeText(otherName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches(withText(otherName)));
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord)));
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches(withText(retypePassWord)));
    }
}