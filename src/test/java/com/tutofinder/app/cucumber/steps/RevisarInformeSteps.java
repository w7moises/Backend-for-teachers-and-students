package com.tutofinder.app.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RevisarInformeSteps {
    @Given("el padre de familia se encuentra en la sección “Mis clases”")
    public void elPadreDeFamiliaSeEncuentraEnLaSecciónMisClases() {
    }

    @When("se dirija a los detalles de una clase y no hay informacion")
    public void seDirijaALosDetallesDeUnaClaseYNoHayInformacion() {
    }

    @Then("no podra descargar el informe del alumno y se mostrara el mensaje “informe no disponible”")
    public void noPodraDescargarElInformeDelAlumnoYSeMostraraElMensajeInformeNoDisponible() {
    }

    @When("se dirija a los detalles de la clase y el informe ha sido subido")
    public void seDirijaALosDetallesDeLaClaseYElInformeHaSidoSubido() {
    }

    @Then("podra visualizar el nombre del informe y al lado un botón para descargarlo")
    public void podraVisualizarElNombreDelInformeYAlLadoUnBotónParaDescargarlo() {
    }

    @And("el informe ya está disponible")
    public void elInformeYaEstáDisponible() {
    }

    @When("el padre determine que el archivo no cumple con lo esperado y seleccione la opción notificar docente")
    public void elPadreDetermineQueElArchivoNoCumpleConLoEsperadoYSeleccioneLaOpciónNotificarDocente() {
    }

    @Then("se enviara una notificación al docente solicitando la corrección del informe.")
    public void seEnviaraUnaNotificaciónAlDocenteSolicitandoLaCorrecciónDelInforme() {
    }
}
