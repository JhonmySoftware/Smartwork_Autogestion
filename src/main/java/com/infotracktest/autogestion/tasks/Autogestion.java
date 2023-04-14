package com.infotracktest.autogestion.tasks;

import com.infotracktest.autogestion.interactions.DatosServicio;
import com.infotracktest.autogestion.interactions.SeleccionarTipoDocumento;
import com.infotracktest.autogestion.interactions.ubicacionServicio;
import com.infotracktest.autogestion.models.FormularioAutogestion;
import com.infotracktest.autogestion.userinterfaces.ObjectAutogestion;
import com.infotracktest.autogestion.utlis.ExcelReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Autogestion implements Task {


    /**
     * Constructor para la clase Autogestion que inicializa la lista de datos de prueba.
     *
     * @param testData Lista de mapas de cadenas de texto que representan los datos de prueba.
     */
    public Autogestion(List<Map<String, String>> testData) {
        this.testData = testData;
    }


    public static Autogestion Two() {
        return Tasks.instrumented(Autogestion.class);
    }

    // Nombre de la hoja de cálculo que contiene los datos de prueba
    private static final String SHEET_NAME = "testData";

    // Ubicación del archivo de hoja de cálculo por defecto
    private static final String DEFAULT_FILE_PATH = "src\\test\\resources\\data\\testData.xls";

    // Lista de mapas de cadenas de texto que representan los datos de prueba
    private List<Map<String, String>> testData;


    /**
     * Constructor para la clase Autogestion que inicializa la lista de
     * datos de prueba a partir de un archivo de Excel.
     *
     * @param filePath Ubicación del archivo de Excel que contiene los datos de prueba.
     */
    public Autogestion(String filePath) {
        try {
            this.testData = ExcelReader.readExcel(filePath, SHEET_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Método estático que devuelve una instancia de la clase Autogestion con
     * los datos de prueba inicializados a partir de un archivo de Excel por defecto.
     */
    public static Autogestion withExcelFile() {
        return Tasks.instrumented(Autogestion.class, DEFAULT_FILE_PATH);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        // Crear una instancia de Autogestion con los datos de prueba inicializados a partir del archivo de Excel por defecto
        Autogestion autogestion = new Autogestion(DEFAULT_FILE_PATH);

        // Recorre todas las filas de la lista testData y extrae el valor de la columna "NumDocumento" de cada una.
        for (Map<String, String> row : autogestion.testData) {
            FormularioAutogestion formulario = new FormularioAutogestion();
            formulario.setTipoDocumentos(row.get("TipoDocumento"));
            formulario.setNumDocumentos(row.get("NumDocumento"));
            formulario.setCorreoElectronicos(row.get("CorreoElectronico"));
            formulario.setRazonSocials(row.get("RazonSocial"));
            formulario.setNombress(row.get("Nombres"));
            formulario.setApellidoss(row.get("Apellidos"));
            formulario.setTFijos(row.get("TFijo"));
            formulario.setNCelulars(row.get("NCelular"));

            actor.attemptsTo(Wait.until(
                            WebElementQuestion.the(ObjectAutogestion.checkedG),
                            WebElementStateMatchers.isPresent()
                    ).forNoLongerThan(30).seconds(),
                    Click.on(ObjectAutogestion.checkedG),
                    Click.on(ObjectAutogestion.TipoDocumento),
                    SeleccionarTipoDocumento.conValor(formulario.getTipoDocumentos()),
                    Enter.theValue(formulario.getNumDocumentos()).into(ObjectAutogestion.NumDocumento),
                    Enter.theValue(formulario.getCorreoElectronicos()).into(ObjectAutogestion.CorreoElectronico));

            if (ObjectAutogestion.RazonSocial.resolveFor(actor).isEnabled()) {
                actor.attemptsTo(Enter.theValue(formulario.getRazonSocials()).into(ObjectAutogestion.RazonSocial));
            } else {
                actor.attemptsTo(
                        Enter.theValue(formulario.getNombress()).into(ObjectAutogestion.Nombres),
                        Enter.theValue(formulario.getApellidoss()).into(ObjectAutogestion.Apellidos)
                );
            }
            actor.attemptsTo(
                    Enter.theValue(formulario.getTFijos()).into(ObjectAutogestion.TFijo),
                    Enter.theValue(formulario.getNCelulars()).into(ObjectAutogestion.NCelular),
                    Click.on(ObjectAutogestion.Continuar)
            );

            // Ubicación
            actor.attemptsTo(ubicacionServicio.withExcelFile());

            //Datos del servicio
            actor.attemptsTo(DatosServicio.withExcelFile());

        }



    }
}