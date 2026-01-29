package com.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Test Runner para ejecutar los escenarios de Datepicker con Serenity BDD y Cucumber
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/select_date.feature",
        glue = "com.automation.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@datepicker"
)
public class DatepickerRunner {
    // Esta clase permanece vacía - solo sirve como punto de entrada para JUnit
}