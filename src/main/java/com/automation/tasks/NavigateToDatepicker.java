package com.automation.tasks;

import com.automation.ui.DatepickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Tarea que navega a la página del datepicker de jQuery UI
 */
public class NavigateToDatepicker implements Task {

    private final String url;

    public NavigateToDatepicker(String url) {
        this.url = url;
    }

    public NavigateToDatepicker() {
        this.url = "https://jqueryui.com/datepicker/";
    }

    public static NavigateToDatepicker page() {
        return instrumented(NavigateToDatepicker.class);
    }

    public static NavigateToDatepicker at(String url) {
        return instrumented(NavigateToDatepicker.class, url);
    }

    @Override
    @Step("{0} navega a la página del datepicker")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );

        // Esperar a que el frame esté disponible
        actor.attemptsTo(
                WaitUntil.the(DatepickerPage.DEMO_FRAME, isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }
}