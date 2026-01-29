package com.automation.stepdefinitions;

import com.automation.questions.DateFormat;
import com.automation.questions.SelectedDate;
import com.automation.tasks.NavigateToDatepicker;
import com.automation.tasks.SelectDate;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;

/**
 * Step Definitions para los escenarios de selección de fecha
 */
public class DatepickerSteps {

    @Dado("que el usuario navega a la página del datepicker")
    public void queElUsuarioNavegaALaPaginaDelDatepicker() {
        Actor usuario = OnStage.theActorCalled("Usuario");

        usuario.attemptsTo(
                NavigateToDatepicker.page()
        );
    }

    @Cuando("el usuario selecciona la fecha {string} del mes {string} del año {string}")
    public void elUsuarioSeleccionaLaFecha(String dia, String mes, String año) {
        Actor usuario = OnStage.theActorInTheSpotlight();

        int day = Integer.parseInt(dia);
        int month = Integer.parseInt(mes);
        int year = Integer.parseInt(año);

        usuario.attemptsTo(
                SelectDate.on(day, month, year)
        );
    }

    @Entonces("la fecha seleccionada no debe estar vacía")
    public void laFechaSeleccionadaNoDebeEstarVacia() {
        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.should(
                seeThat("la fecha seleccionada",
                        SelectedDate.value(),
                        is(not(emptyString())))
        );
    }

    @Y("la fecha seleccionada debe tener el formato correcto {string}")
    public void laFechaSeleccionadaDebeTenerElFormatoCorrecto(String formato) {
        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.should(
                seeThat("el formato de la fecha",
                        DateFormat.isValid(),
                        is(true))
        );
    }

    @Entonces("la fecha seleccionada debe ser {string}")
    public void laFechaSeleccionadaDebeSer(String fechaEsperada) {
        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.should(
                seeThat("la fecha seleccionada",
                        SelectedDate.value(),
                        equalTo(fechaEsperada))
        );
    }

    @Y("la fecha debe tener el formato correcto")
    public void laFechaDebeTenerElFormatoCorrecto() {
        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.should(
                seeThat("el formato de la fecha",
                        DateFormat.isValid(),
                        is(true))
        );
    }
}