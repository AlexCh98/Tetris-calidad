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


public class GamePauseTest {

    @Rule
    ActivityTestRule rule = new ActivityTestRule<>(ActivityClasico.class, false, false);

    @Before("@pause-feature")
    public void setup() {
        rule.launchActivity(null);
    }

    @After("@pause-feature")
    public void tearDown() {
        rule.getActivity().finish();

    }

    @Given("^I am on activity screen$")
    public void i_am_on_activity_clasico() throws Throwable {
        onView(withId(R.id.button1)).perform(click());
    }

    @When("^I press the button$")
    public void i_press_button() throws Throwable {
        onView(withId(R.id.PauseButton)).perform(click());
    }

    @Then("^The game pause$")
    public void theGamePause() {
        assertTrue(!((ActivityClasico) rule.getActivity()).isPause());
    }

}
