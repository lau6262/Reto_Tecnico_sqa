package com.automation.questions;

import com.automation.ui.DatepickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Step;

import java.util.regex.Pattern;

/**
 * Question que valida si la fecha tiene el formato esperado.
 * El formato esperado del datepicker de jQuery UI es: MM/DD/YYYY (ej: "12/25/2024")
 */
public class DateFormat implements Question<Boolean> {

    private final Pattern expectedFormat;

    // Formato por defecto: MM/DD/YYYY
    private static final Pattern DEFAULT_FORMAT = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    public DateFormat(Pattern expectedFormat) {
        this.expectedFormat = expectedFormat != null ? expectedFormat : DEFAULT_FORMAT;
    }

    @Override
    @Step("{0} valida el formato de la fecha")
    public Boolean answeredBy(Actor actor) {
        // Cambiar al frame
        BrowseTheWeb.as(actor).getDriver()
                .switchTo()
                .frame(DatepickerPage.DEMO_FRAME.resolveFor(actor));

        // Obtener el valor del input
        String dateValue = DatepickerPage.DATE_INPUT
                .resolveFor(actor)
                .getAttribute("value");

        if (dateValue == null || dateValue.isEmpty()) {
            return false;
        }

        return expectedFormat.matcher(dateValue).matches();
    }

    /**
     * Método estático para crear la pregunta con el formato por defecto
     * @return Instancia de DateFormat con formato MM/DD/YYYY
     */
    public static DateFormat isValid() {
        return new DateFormat(DEFAULT_FORMAT);
    }

    /**
     * Método estático para crear la pregunta con un formato personalizado
     * @param format Patrón regex con el formato esperado
     * @return Instancia de DateFormat con el formato especificado
     */
    public static DateFormat matches(String format) {
        return new DateFormat(Pattern.compile(format));
    }

    /**
     * Método estático para crear la pregunta con un patrón personalizado
     * @param pattern Patrón con el formato esperado
     * @return Instancia de DateFormat con el formato especificado
     */
    public static DateFormat matches(Pattern pattern) {
        return new DateFormat(pattern);
    }
}