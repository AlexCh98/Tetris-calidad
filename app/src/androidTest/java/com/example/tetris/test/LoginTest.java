package com.example.tetris.test;

import android.app.Activity;
import android.content.Intent;


import androidx.test.rule.ActivityTestRule;

import com.example.tetris.ActivityClasico;
import com.example.tetris.Pieza;
import com.example.tetris.R;
import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;


public class LoginTest {

    @Rule
    ActivityTestRule rule = new ActivityTestRule<>(ActivityClasico.class, false, false);

    private ActivityClasico activity;
    @Before("@piece-feature")
    public void setup() {
        //activityTestRule.launchActivity(new Intent());
        //activity = activityTestRule.getActivity();*/
        rule.launchActivity(null);
    }

    @After("@piece-feature")
    public void tearDown() {
        //activityTestRule.finishActivity();
        rule.getActivity().finish();

    }

    @Given("^The user has began the game$")
    public void i_am_on_activity_clasico() throws Throwable {
        onView(withId(R.id.button1)).perform(click());
        //TestCase.assertNotNull(activity);
    }

    @Then("^You have to positionate the new piece$")
    public void you_have_to_positionate_the_new_piece() throws Throwable {
        Pieza nueva = new Pieza(8);
        assertEquals(2,2);
        assertEquals(nueva,activity.getPiezas().get(0));
        assertEquals(nueva,activity.getPiezas().get(0));
    }

}