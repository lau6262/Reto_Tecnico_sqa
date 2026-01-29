package com.automation.core;

/**
 * Interfaz base para las habilidades (Abilities) que un Actor puede tener.
 * Las habilidades representan las capacidades del actor para interactuar con el sistema.
 *
 * Esta es una interfaz personalizada basada en tu implementación original de TypeScript.
 * Nota: Serenity BDD ya tiene su propia interfaz Ability en net.serenitybdd.screenplay.Ability
 */
public interface Ability {

    /**
     * Verifica si la habilidad está disponible y lista para usar
     * @return true si la habilidad está disponible
     */
    boolean can();

    /**
     * Método opcional para limpiar recursos de la habilidad.
     * Por defecto no hace nada (método default de Java 8+).
     * Las clases que implementen pueden sobrescribir si necesitan limpieza.
     */
    default void cleanup() {
        // Implementación por defecto vacía
        // Las clases que implementen pueden sobrescribir si necesitan limpieza
    }
}