package com.example.tetris.test;

import android.app.Activity;
import android.content.Intent;


import androidx.test.rule.ActivityTestRule;

import com.example.tetris.ActivityClasico;
import com.example.tetris.R;
import junit.framework.TestCase;

import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class MusicTest {

    @Rule
    ActivityTestRule rule = new ActivityTestRule<>(ActivityClasico.class, false, false);

    private ActivityClasico activity;
    @Before("@music-feature")
    public void setup() {
        rule.launchActivity(null);
    }

    @After("@music-feature")
    public void tearDown() {
        rule.getActivity().finish();

    }

    @Given("^I am on activity screen")
    public void i_am_on_activity_clasico() throws Throwable {
        onView(withId(R.id.button1)).perform(click());
    }

    @When("^I press the button$")
    public void i_press_button() throws Throwable {
        onView(withId(R.id.musicButton)).perform(click());

    }

    @Then("^The music stops$")
    public void the_music_stops() throws Throwable {
        assertTrue(!((ActivityClasico) rule.getActivity()).estaMusicaReproduciendo());
    }

}