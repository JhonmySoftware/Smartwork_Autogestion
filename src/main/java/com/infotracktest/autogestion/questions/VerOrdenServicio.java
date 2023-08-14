package com.infotracktest.autogestion.questions;

import com.infotracktest.autogestion.userinterfaces.ObjectdatosServicio;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class VerOrdenServicio implements Question {

    public static VerOrdenServicio one() {
        return new VerOrdenServicio();
    }

    @Override
    public Object answeredBy(Actor actor) {
        return Text.of(ObjectdatosServicio.VerOrdenServicio).viewedBy(actor).asString();
    }
}
