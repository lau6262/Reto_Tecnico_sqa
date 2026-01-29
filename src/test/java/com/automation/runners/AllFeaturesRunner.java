package com.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar TODOS los features del proyecto.
 * Este runner ejecuta todos los archivos .feature encontrados en src/test/resources/features
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.automation.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class AllFeaturesRunner {
    // Esta clase permanece vacía - solo sirve como punto de entrada para JUnit
}