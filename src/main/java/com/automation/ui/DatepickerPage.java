package com.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Page Object que contiene los elementos (Targets) de la página del Datepicker.
 * Equivalente a DatepickerScreen en Playwright.
 */
public class DatepickerPage {

    // Frame del demo
    public static final Target DEMO_FRAME = Target.the("demo frame")
            .located(By.cssSelector("iframe.demo-frame"));

    // Input del datepicker
    public static final Target DATE_INPUT = Target.the("date input field")
            .locatedBy("#datepicker");

    // Calendario (aparece al hacer click en el input)
    public static final Target DATEPICKER_CALENDAR = Target.the("datepicker calendar")
            .locatedBy(".ui-datepicker");

    // Header del calendario
    public static final Target DATEPICKER_HEADER = Target.the("datepicker header")
            .locatedBy(".ui-datepicker-header");

    // Mes y año actual
    public static final Target CURRENT_MONTH_YEAR = Target.the("current month and year")
            .locatedBy(".ui-datepicker-title");

    // Botón mes anterior
    public static final Target PREV_MONTH_BUTTON = Target.the("previous month button")
            .locatedBy(".ui-datepicker-prev");

    // Botón mes siguiente
    public static final Target NEXT_MONTH_BUTTON = Target.the("next month button")
            .locatedBy(".ui-datepicker-next");

    // Tabla del calendario
    public static final Target CALENDAR_TABLE = Target.the("calendar table")
            .locatedBy(".ui-datepicker-calendar");

    // Días disponibles
    public static final Target ALL_AVAILABLE_DAYS = Target.the("all available days")
            .locatedBy("td a:not(.ui-state-disabled)");

    // Días deshabilitados
    public static final Target DISABLED_DAYS = Target.the("disabled days")
            .locatedBy("td a.ui-state-disabled");

    /**
     * Target dinámico para seleccionar un día específico
     * @param day Número del día (1-31)
     * @return Target del día específico
     */
    public static Target dayWithNumber(int day) {
        return Target.the("day " + day)
                .locatedBy("td a:not(.ui-state-disabled):contains('" + day + "')");
    }

    /**
     * Target dinámico para un día específico (versión alternativa más robusta)
     */
    public static Target specificDay(int day) {
        return Target.the("day " + day)
                .located(By.xpath(String.format(
                        "//td[@data-handler='selectDay']/a[text()='%d' and not(contains(@class, 'ui-state-disabled'))]",
                        day
                )));
    }
}