package com.automation.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Interaction para esperar que un elemento sea visible.
 */
public class WaitForElement implements Interaction {

    private final Target target;
    private final int timeoutInSeconds;

    public WaitForElement(Target target, int timeoutInSeconds) {
        this.target = target;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public static WaitForElement toBeVisible(Target target) {
        return instrumented(WaitForElement.class, target, 10);
    }

    public static WaitForElement toBeVisible(Target target, int seconds) {
        return instrumented(WaitForElement.class, target, seconds);
    }

    @Override
    @Step("{0} waits for #target to be visible")
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait wait = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(),
                Duration.ofSeconds(timeoutInSeconds)
        );
        wait.until(ExpectedConditions.visibilityOf(target.resolveFor(actor)));
    }
}