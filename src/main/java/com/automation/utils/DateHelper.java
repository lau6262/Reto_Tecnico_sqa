package com.automation.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utilidades para manejo de fechas en los escenarios de datepicker.
 */
public class DateHelper {

    private static final DateTimeFormatter MM_DD_YYYY_FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    /**
     * Convierte mes 1-12 a 0-11.
     */
    public static int toZeroBasedMonth(int monthOneBased) {
        return monthOneBased - 1;
    }

    /**
     * Convierte mes 0-11 a 1-12.
     */
    public static int toOneBasedMonth(int monthZeroBased) {
        return monthZeroBased + 1;
    }

    /**
     * Devuelve un objeto fecha objetivo desplazado n meses desde hoy.
     * @param offsetMonths cantidad de meses a desplazar (puede ser negativo)
     */
    public static DateResult monthOffsetFromToday(int offsetMonths) {
        LocalDate today = LocalDate.now();
        LocalDate target = today.plusMonths(offsetMonths);

        return new DateResult(
                target.getDayOfMonth(),
                target.getMonthValue(), // Ya es 1-12
                target.getYear()
        );
    }

    /**
     * Formatea una fecha (mes 1-12) a MM/DD/YYYY con padding de 2 dígitos.
     */
    public static String formatMMDDYYYY(int day, int monthOneBased, int year) {
        LocalDate date = LocalDate.of(year, monthOneBased, day);
        return date.format(MM_DD_YYYY_FORMATTER);
    }

    /**
     * Clase interna para retornar resultados de fecha
     */
    public static class DateResult {
        public final int day;
        public final int monthOneBased;
        public final int year;

        public DateResult(int day, int monthOneBased, int year) {
            this.day = day;
            this.monthOneBased = monthOneBased;
            this.year = year;
        }
    }
}