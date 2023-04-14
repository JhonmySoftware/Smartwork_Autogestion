package com.infotracktest.autogestion.interactions;

import com.infotracktest.autogestion.userinterfaces.ObjectAutogestion;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.openqa.selenium.support.ui.Select;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SeleccionarTipoDocumento implements Interaction {
    private final String tipoDocumento;

    public SeleccionarTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public static SeleccionarTipoDocumento conValor(String tipoDocumento) {
        return instrumented(SeleccionarTipoDocumento.class, tipoDocumento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Select dropdown = new Select(ObjectAutogestion.TipoDocumento.resolveFor(actor));
        dropdown.selectByVisibleText(tipoDocumento);
    }
}
