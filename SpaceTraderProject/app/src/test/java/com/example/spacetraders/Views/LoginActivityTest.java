package com.example.spacetraders.Views;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    private LoginActivity loginActivity;
    private static final int TIMEOUT = 200;
    private String failurePass;
    private String goodPass;
    private String failureEmail;
    private String goodEmail;

    @Before
    public void setUp() {
        loginActivity = new LoginActivity();
        failurePass = "123";
        goodPass = "1234567";
        failureEmail = "no 'at' symbol here";
        goodEmail = "test@gmail.com";
    }

    @Test(timeout = TIMEOUT)
    public void isEmailValid() {
        assertFalse("Password too short", loginActivity.isEmailValid(failureEmail));
        assertFalse(loginActivity.isEmailValid(""));
        assertFalse(loginActivity.isEmailValid(null));
        assertTrue(loginActivity.isEmailValid(goodEmail));
    }

    @Test(timeout = TIMEOUT)
    public void isPasswordValid() {
        assertFalse("Password too short", loginActivity.isPasswordValid(failurePass));
        assertFalse(loginActivity.isPasswordValid(null));
        assertFalse(loginActivity.isPasswordValid(""));
        assertTrue(loginActivity.isPasswordValid(goodPass));
    }
}