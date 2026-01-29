package com.automation.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;

/**
 * Step Definitions base para configurar el escenario y los actores
 */
public class BaseSteps {

    private EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage() {
        // Configurar el escenario con los actores
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void cleanUp() {
        // Limpiar después de cada escenario
        OnStage.drawTheCurtain();
    }
}