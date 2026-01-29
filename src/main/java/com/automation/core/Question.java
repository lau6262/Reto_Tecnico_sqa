package com.automation.core;

import net.serenitybdd.screenplay.Actor;

/**
 * Interfaz base para las preguntas (Questions) que se pueden hacer sobre el estado de la aplicación.
 * Las preguntas se usan para verificar y obtener información del sistema.
 *
 * @param <T> Tipo del valor de respuesta esperado
 */
public interface Question<T> {

    /**
     * Obtiene la respuesta a la pregunta utilizando las habilidades del actor
     * @param actor El actor que realiza la pregunta
     * @return El valor de respuesta de tipo T
     */
    T answeredBy(Actor actor);
}