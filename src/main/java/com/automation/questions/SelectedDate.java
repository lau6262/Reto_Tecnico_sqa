package com.automation.questions;

import com.automation.ui.DatepickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

/**
 * Question que obtiene la fecha seleccionada en el campo de entrada del datepicker
 */
public class SelectedDate implements Question<String> {

    @Override
    @Step("{0} obtiene la fecha seleccionada")
    public String answeredBy(Actor actor) {
        // Primero cambiar al frame
        BrowseTheWeb.as(actor).getDriver()
                .switchTo()
                .frame(DatepickerPage.DEMO_FRAME.resolveFor(actor));

        // Obtener el valor del input
        String dateValue = DatepickerPage.DATE_INPUT
                .resolveFor(actor)
                .getAttribute("value");

        return dateValue != null ? dateValue : "";
    }

    /**
     * Método estático para crear la pregunta de forma más legible
     * @return Instancia de SelectedDate
     */
    public static SelectedDate value() {
        return new SelectedDate();
    }
}