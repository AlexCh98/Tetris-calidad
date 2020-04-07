package com.example.tetris.test;

import android.app.Activity;
import android.content.Intent;


import androidx.test.rule.ActivityTestRule;

import com.example.tetris.ActivityClasico;

import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.Assert.assertNotNull;


public class LoginTest {

    @Rule
    ActivityTestRule rule = new ActivityTestRule<>(ActivityClasico.class, false, false);

    @Before("@login-feature")
    public void setup() {
        //activityTestRule.launchActivity(new Intent());
        //activity = activityTestRule.getActivity();*/
        rule.launchActivity(null);
    }

    @After("@login-feature")
    public void tearDown() {
        //activityTestRule.finishActivity();
        rule.getActivity().finish();

    }
    /*   @Before("@login-feature")
       public void launchActivity() throws Exception {
           rule.launchActivity(null);
       }


       @After("@login-feature")
       public void finishActivity() throws Exception {
           getActivity.finish();
       }

      /* private Activity getActivity() {
           return rule.getActivity();
       }

       @Given("^El usuario ya ha iniciado la partida")
       public void el_usuario_ya_ha_iniciado_la_partida() throws Throwable {
           throw new PendingException();
       }

       @When("^El usuario está jugando una partida")
       public void el_usuario_est_jugando_una_partida() throws Throwable {
           throw new PendingException();
       }

       @Then("^La partida sigue pero sin música")
       public void la_partida_sigue_pero_sin_musica() throws Throwable {
           throw new PendingException();
       }*/
   @Given("^I am on login screen")
   public void I_am_on_login_screen() {
       //assertNotNull(activity);
   }

    @When("^I input email (\\S+)$")
    public void I_input_email(final String email) {
        //onView(withId(R.id.email)).perform(typeText(email));
    }

    @When("^I input password \"(.*?)\"$")
    public void I_input_password(final String password) {
       // onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
    }

    @When("^I press submit button$")
    public void I_press_submit_button() {
        //onView(withId(R.id.submit)).perform(click());
    }

    @When("^I tap sign up button$")
    public void I_tap_sign_up_button() {
       // onView(withId(R.id.signup)).perform(click());
    }

    @Then("^I should see error on the (\\S+)$")
    public void I_should_see_error_on_the_editTextView(final String viewName) {
       /* int viewId = (viewName.equals("email")) ? R.id.email : R.id.password;
        int messageId = (viewName.equals("email")) ? R.string.msg_email_error : R.string.msg_password_error;

        onView(withId(viewId)).check(matches(hasErrorText(activity.getString(messageId))));*/
    }


}