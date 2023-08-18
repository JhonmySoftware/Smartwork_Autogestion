package com.infotracktest.autogestion.tasks;

import com.infotracktest.autogestion.userinterfaces.ObjectAbrirPagina;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirPagina implements Task {

    private ObjectAbrirPagina objectAbrirPagina;

    public static AbrirPagina One() {
        return Tasks.instrumented(AbrirPagina.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn(objectAbrirPagina));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
