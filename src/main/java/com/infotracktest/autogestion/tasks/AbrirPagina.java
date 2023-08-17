package com.infotracktest.autogestion.tasks;

import com.infotracktest.autogestion.userinterfaces.ObjectAbrirPagina;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class AbrirPagina implements Task {

    private ObjectAbrirPagina objectAbrirPagina;

    public static AbrirPagina One() {
        return Tasks.instrumented(AbrirPagina.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        System.setProperty("webdriver.edge.driver", "src/test/resources/driver/msedgedriver.exe");

        // Open the browser and maximize the window
        actor.attemptsTo(Open.browserOn(objectAbrirPagina));

        // Maximize the browser window
        WebDriverFacade webDriverFacade = (WebDriverFacade) getDriver();
        WebDriver originalDriver = webDriverFacade.getProxiedDriver();
        originalDriver.manage().window().maximize();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
