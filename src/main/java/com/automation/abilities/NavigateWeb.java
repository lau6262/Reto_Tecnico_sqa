package com.automation.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;
import org.openqa.selenium.WebDriver;

/**
 * Habilidad personalizada que permite al Actor navegar por la web.
 * Proporciona acceso al WebDriver para interacciones personalizadas.
 */
public class NavigateWeb implements Ability, RefersToActor {

    private final WebDriver driver;
    private Actor actor;

    private NavigateWeb(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Factory method para crear la habilidad
     */
    public static NavigateWeb using(WebDriver driver) {
        return new NavigateWeb(driver);
    }

    /**
     * Método helper para obtener esta habilidad desde un Actor
     */
    public static NavigateWeb as(Actor actor) {
        return actor.abilityTo(NavigateWeb.class);
    }

    /**
     * Obtiene el WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Verifica si la habilidad está disponible
     */
    public boolean can() {
        return driver != null;
    }

    /**
     * Limpia recursos de la habilidad
     */
    public void cleanup() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error al cerrar el navegador: " + e.getMessage());
            }
        }
    }

    @Override
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T) this;
    }
}