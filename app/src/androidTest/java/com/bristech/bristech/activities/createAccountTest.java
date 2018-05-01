package com.bristech.bristech.activities;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.bristech.bristech.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.bristech.bristech.R.id.txtEmail;
import static com.bristech.bristech.R.id.txtFirstName;
import static com.bristech.bristech.R.id.txtOtherNames;
import static org.junit.Assert.assertFalse;



public class createAccountTest {
    private static final String emailValid = "1234@my.bristol.ac.uk";
    private static final String invalidEmail = "fdlghbdakb";

    private static final String userName = "Bristech47";
    private static final String userNameInvalid = "memes™";

    private static final String firstName = "Richard";
    private static final String firstNameInvalid = "爱chicken";

    private static final String otherName = "Davies";
    private static final String otherNameInvalid = "死亡ofme";


    private static final String passWord1 = "123456789";
    private static final String passWord2 = "ab87jiKlm";

    private static final String passWordInvalid1 = "oJ9m©jiK";
    private static final String passWordInvalid2 = "a";
    private static final String passWordInvalid3 = "kjdhflksjdhflksjdhflskjdhflskhdf";

    private static final String retypePassWord = "123456789";
    private static final String retypePassWordInvalid = "abcde";


    @Rule
    public ActivityTestRule<createAccount> mActivityRule = new ActivityTestRule<>(createAccount.class);

    @Test
    public void testButton() {
        Espresso.onView(withId(R.id.btnCreateAccount)).check(matches(isClickable()));
    }

    @Test
    public void testValidEmail() {
        Espresso.onView(withId(txtEmail)).perform(typeText(emailValid), closeSoftKeyboard());
        Espresso.onView(withId(txtEmail)).check(matches(withText(emailValid)));
        createAccount.isStringValid(emailValid);
    }

    @Test
    public void testInvalidEmail () {
        Espresso.onView(withId(txtEmail)).perform(typeText(invalidEmail), closeSoftKeyboard());
        createAccount.isStringValid(invalidEmail);
        assertFalse("Invalid email", createAccount.isEmailValid(invalidEmail));
    }

    @Test
    public void testValidUsername() {
        Espresso.onView(withId(R.id.txtUsername)).perform(typeText(userName), closeSoftKeyboard());
        createAccount.isStringValid(userName);
        Espresso.onView(withId(R.id.txtUsername)).check(matches(withText(userName)));
    }

    @Test
    public void testInvalidUsername() {
        Espresso.onView(withId(R.id.txtUsername)).perform(replaceText(userNameInvalid), closeSoftKeyboard());
        Boolean isValid = createAccount.isStringValid(userNameInvalid);
        assertFalse(isValid);
    }

    @Test
    public void testFirstNameValid() {
        Espresso.onView(withId(txtFirstName)).perform(typeText(firstName), closeSoftKeyboard());
        createAccount.isStringValid(firstName);
        Espresso.onView(withId(R.id.txtFirstName)).check(matches(withText(firstName)));
    }

    @Test
    public void testFirstNameInvalid() {
        Espresso.onView(withId(R.id.txtFirstName)).perform(replaceText(firstNameInvalid), closeSoftKeyboard());
        Boolean isValid = createAccount.isStringValid(firstNameInvalid);
        assertFalse(isValid);
    }

    @Test
    public void testOtherNamesValid() {
        Espresso.onView(withId(txtOtherNames)).perform(typeText(otherName), closeSoftKeyboard());
        createAccount.isStringValid(otherName);
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches(withText(otherName)));
    }

    @Test
    public void testOtherNamesInvalid() {
        Espresso.onView(withId(R.id.txtOtherNames)).perform(replaceText(otherNameInvalid), closeSoftKeyboard());
        Boolean isValid = createAccount.isStringValid(otherNameInvalid);
        assertFalse(isValid);
    }

    @Test
    public void testPassword1() {
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord1), closeSoftKeyboard());
        createAccount.isStringValid(passWord1);
        createAccount.isPasswordLengthValid(passWord1);
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord1)));
    }

    @Test
    public void testPassword2() {
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord2), closeSoftKeyboard());
        createAccount.isStringValid(passWord2);
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord2)));
    }

    @Test
    public void testPasswordInvalid1() {
        Espresso.onView(withId(R.id.txtPassword)).perform(replaceText(passWordInvalid1), closeSoftKeyboard());
        Boolean isValid = createAccount.isStringValid(passWordInvalid1);
        assertFalse(isValid);
    }

    @Test
    public void testPasswordInvalid2() {
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWordInvalid2), closeSoftKeyboard());
        Boolean isValid = createAccount.isPasswordLengthValid(passWordInvalid2);
        assertFalse(isValid);
    }

    @Test
    public void testPasswordInvalid3() {
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWordInvalid3), closeSoftKeyboard());
        Boolean isValid = createAccount.isPasswordLengthValid(passWordInvalid3);
        assertFalse(isValid);
    }

    @Test
    public void testRetypePassword() {
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWord), closeSoftKeyboard());
        createAccount.isPassEqual(passWord1, retypePassWord);
    }

    @Test
    public void testRetypePasswordInvalid() {
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWordInvalid), closeSoftKeyboard());
        createAccount.isPassEqual(passWord1, retypePassWordInvalid);
        assertFalse("Passwords don't match" , createAccount.isPassEqual(passWord1, retypePassWordInvalid));
    }

    @Test
    public void testAccountCreation() {
        Espresso.onView(withId(txtEmail)).perform(typeText(emailValid), closeSoftKeyboard());
        Espresso.onView(withId(txtEmail)).check(matches(withText(emailValid)));
        Espresso.onView(withId(R.id.txtUsername)).perform(typeText(userName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtUsername)).check(matches(withText(userName)));
        Espresso.onView(withId(R.id.txtFirstName)).perform(typeText(firstName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtFirstName)).check(matches(withText(firstName)));
        Espresso.onView(withId(R.id.txtOtherNames)).perform(typeText(otherName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtOtherNames)).check(matches(withText(otherName)));
        Espresso.onView(withId(R.id.txtPassword)).perform(typeText(passWord1), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtPassword)).check(matches(withText(passWord1)));
        Espresso.onView(withId(R.id.txtRetypePassword)).perform(typeText(retypePassWord), closeSoftKeyboard());
        Espresso.onView(withId(R.id.txtRetypePassword)).check(matches(withText(retypePassWord)));
        Espresso.onView(withId(R.id.btnCreateAccount)).check(matches(isClickable()));
    }
}