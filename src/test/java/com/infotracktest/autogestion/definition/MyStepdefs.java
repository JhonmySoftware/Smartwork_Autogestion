package com.infotracktest.autogestion.definition;

import com.infotracktest.autogestion.questions.VerOrdenServicio;
import com.infotracktest.autogestion.tasks.AbrirPagina;
import com.infotracktest.autogestion.tasks.Autogestion;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

public class MyStepdefs {

    @Before
    public void IniciarEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^el usuario final cuando desee crear o agendar una orden de servicio$")
    public void elUsuarioFinalCuandoDeseeCrearOAgendarUnaOrdenDeServicio() {
        OnStage.theActorCalled("jhon").wasAbleTo(AbrirPagina.One());
    }

    @Cuando("^Crea la orden de servicio$")
    public void creaLaOrdenDeServicio() {
        OnStage.theActorCalled("jhon").wasAbleTo(Autogestion.withExcelFile());
    }

    @Entonces("^verifica que se creo correctamente la orden de servicio$")
    public void verificaQueSeCreoCorrectamenteLaOrdenDeServicio() {

        }
}
