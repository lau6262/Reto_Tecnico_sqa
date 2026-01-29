package com.automation.tasks;

import com.automation.interactions.SwitchToFrame;
import com.automation.interactions.WaitForElement;
import com.automation.ui.DatepickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea que selecciona una fecha específica en el datepicker
 */
public class SelectDate implements Task {

    private final int day;
    private final int month; // 0-11 (0 = Enero, 11 = Diciembre)
    private final int year;
    private static final int MAX_ATTEMPTS = 50;

    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();

    static {
        MONTH_MAP.put("January", 0);
        MONTH_MAP.put("February", 1);
        MONTH_MAP.put("March", 2);
        MONTH_MAP.put("April", 3);
        MONTH_MAP.put("May", 4);
        MONTH_MAP.put("June", 5);
        MONTH_MAP.put("July", 6);
        MONTH_MAP.put("August", 7);
        MONTH_MAP.put("September", 8);
        MONTH_MAP.put("October", 9);
        MONTH_MAP.put("November", 10);
        MONTH_MAP.put("December", 11);
    }

    /**
     * Constructor privado
     */
    private SelectDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Método estático para crear la tarea de forma más legible.
     * Acepta mes en formato 1-12 (más intuitivo).
     *
     * @param day Día del mes (1-31)
     * @param month Mes (1-12, donde 1 = Enero, 12 = Diciembre)
     * @param year Año (ej: 2025)
     * @return Instancia de SelectDate
     */
    public static SelectDate on(int day, int month, int year) {
        // Convertir de 1-12 a 0-11 para el estándar interno
        return instrumented(SelectDate.class, day, month - 1, year);
    }

    /**
     * Método alternativo usando enum Month de Java
     */
    public static SelectDate on(int day, Month month, int year) {
        return instrumented(SelectDate.class, day, month.getValue() - 1, year);
    }

    @Override
    @Step("{0} selecciona la fecha {day}/{month}/{year}")
    public <T extends Actor> void performAs(T actor) {
        System.out.println(String.format("Seleccionando fecha: %d/%d/%d", day, month + 1, year));

        // 1. Cambiar al iframe
        actor.attemptsTo(
                SwitchToFrame.on(DatepickerPage.DEMO_FRAME)
        );

        // 2. Abrir el datepicker
        actor.attemptsTo(
                Click.on(DatepickerPage.DATE_INPUT),
                WaitForElement.toBeVisible(DatepickerPage.DATEPICKER_CALENDAR, 5)
        );

        // 3. Navegar al mes y año correctos
        navigateToTargetMonth(actor);

        // 4. Verificar que el día no esté deshabilitado y seleccionarlo
        WebElement dayElement = DatepickerPage.specificDay(day).resolveFor(actor);

        boolean isDisabled = dayElement.getAttribute("class").contains("ui-state-disabled");
        if (isDisabled) {
            throw new IllegalStateException(
                    String.format("El día %d del mes %d/%d no está disponible para selección",
                            day, month + 1, year)
            );
        }

        actor.attemptsTo(
                Click.on(DatepickerPage.specificDay(day))
        );

        // 5. Esperar a que el calendario se cierre (opcional)
        waitBriefly(500);
    }

    private void navigateToTargetMonth(Actor actor) {
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            // Obtener mes y año actual del calendario
            String currentText = DatepickerPage.CURRENT_MONTH_YEAR
                    .resolveFor(actor)
                    .getText()
                    .trim();

            String[] parts = currentText.split("\\s+");
            if (parts.length < 2) {
                attempts++;
                waitBriefly(200);
                continue;
            }

            String monthName = parts[0];
            int currentYear = Integer.parseInt(parts[1]);
            Integer currentMonth = MONTH_MAP.get(monthName);

            if (currentMonth == null) {
                throw new IllegalStateException("Mes no reconocido: " + monthName);
            }

            // Verificar si ya estamos en el mes correcto
            if (currentMonth == month && currentYear == year) {
                return; // Ya llegamos
            }

            // Calcular diferencia total en meses
            int totalMonthDiff = (year - currentYear) * 12 + (month - currentMonth);

            if (totalMonthDiff < 0) {
                // Ir hacia atrás
                actor.attemptsTo(Click.on(DatepickerPage.PREV_MONTH_BUTTON));
            } else {
                // Ir hacia adelante
                actor.attemptsTo(Click.on(DatepickerPage.NEXT_MONTH_BUTTON));
            }

            attempts++;
            waitBriefly(500);
        }

        throw new IllegalStateException(
                String.format("No se pudo navegar a %d/%d después de %d intentos",
                        month + 1, year, MAX_ATTEMPTS)
        );
    }

    private void waitBriefly(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}