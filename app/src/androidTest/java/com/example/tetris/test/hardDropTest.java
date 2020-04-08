package com.example.tetris.test;

import android.app.Activity;
import androidx.test.rule.ActivityTestRule;
import cucumber.api.java.After;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import org.junit.Rule;
import org.junit.runner.RunWith;



public class hardDropTest {
    @Rule
    ActivityTestRule rule = new ActivityTestRule<>(Activity.class, false, false);

    @Given("^El usuario ya ha iniciado la partida")
    public void el_usuario_ya_ha_iniciado_la_partida() throws Throwable {
        throw new PendingException();
    }

    @When("^El usuario está jugando una partida")
    public void el_usuario_est_jugando_una_partida() throws Throwable {
        throw new PendingException();
    }

    @Then("^La partida sigue pero pudiendo bajar las piezas más rápidamente")
    public void la_partida_sigue_pero_pudiendo_bajar_las_piezas_ms_rpidamente() throws Throwable {
        throw new PendingException();
    }
}




