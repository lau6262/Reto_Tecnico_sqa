package com.automation.core;

import net.serenitybdd.screenplay.Actor;

/**
 * Interfaz base para las tareas (Tasks) que un Actor puede realizar.
 * Las tareas representan acciones de alto nivel que el actor ejecuta.
 */
public interface Task {

    /**
     * Ejecuta la tarea utilizando las habilidades del actor
     * @param actor El actor que ejecuta la tarea
     */
    void performAs(Actor actor);
}