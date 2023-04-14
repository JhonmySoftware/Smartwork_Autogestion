package com.infotracktest.autogestion.interactions;

import com.infotracktest.autogestion.models.FormularioAutogestion;
import com.infotracktest.autogestion.models.FormularioUbicacion;
import com.infotracktest.autogestion.tasks.Autogestion;
import com.infotracktest.autogestion.userinterfaces.ObjectdatosServicio;
import com.infotracktest.autogestion.userinterfaces.ObjectubicacionServicio;
import com.infotracktest.autogestion.utlis.ExcelReader;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ubicacionServicio implements Interaction {


    /**
     * Constructor para la clase Autogestion que inicializa la lista de datos de prueba.
     *
     * @param testData Lista de mapas de cadenas de texto que representan los datos de prueba.
     */
    public ubicacionServicio(List<Map<String, String>> testData) {
        ubicacionServicio.testData = testData;
    }

    // Nombre de la hoja de cálculo que contiene los datos de prueba
    private static final String SHEET_NAME = "testData";

    // Ubicación del archivo de hoja de cálculo por defecto
    private static final String DEFAULT_FILE_PATH = "src\\test\\resources\\data\\testData.xls";

    // Lista de mapas de cadenas de texto que representan los datos de prueba
    private static List<Map<String, String>> testData;


    /**
     * Constructor para la clase Autogestion que inicializa la lista de
     * datos de prueba a partir de un archivo de Excel.
     *
     * @param filePath Ubicación del archivo de Excel que contiene los datos de prueba.
     */
    public ubicacionServicio(String filePath) {
        try {
            testData = ExcelReader.readExcel(filePath, SHEET_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método estático que devuelve una instancia de la clase Autogestion con
     * los datos de prueba inicializados a partir de un archivo de Excel por defecto.
     */
    public static ubicacionServicio withExcelFile() {
        return Tasks.instrumented(ubicacionServicio.class, DEFAULT_FILE_PATH);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // Crear una instancia de UbicacionServicio con los datos de prueba inicializados a partir del archivo de Excel por defecto
        ubicacionServicio UbicacionServicio = new ubicacionServicio(DEFAULT_FILE_PATH);

        for (Map<String, String> row : ubicacionServicio.testData) {
            FormularioUbicacion formulariou = new FormularioUbicacion();
            formulariou.setCiudad(row.get("Ciudad"));
            formulariou.setDireccion(row.get("Direccion"));
            formulariou.setDatosAdicionales(row.get("DatosAdicionales"));
            actor.attemptsTo(Wait.until(WebElementQuestion.the(ObjectubicacionServicio.Ciudad),
                            WebElementStateMatchers.isVisible()).forNoLongerThan(10).seconds(),
                    Enter.theValue(formulariou.getCiudad()).into(ObjectubicacionServicio.Ciudad),
                    Click.on(ObjectubicacionServicio.ListCiudad),
                    Enter.theValue(formulariou.getDireccion()).into(ObjectubicacionServicio.Direccion),
                    Click.on(ObjectubicacionServicio.Buscar)
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            actor.attemptsTo(
                    Enter.theValue(formulariou.getDatosAdicionales())
                            .into(ObjectubicacionServicio.DatosAdicionales),
                    Click.on(ObjectubicacionServicio.Continuar)
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            break; // detener el ciclo en la primera iteración
        }


    }
}
