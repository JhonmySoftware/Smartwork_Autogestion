package com.infotracktest.autogestion.interactions;

import com.infotracktest.autogestion.models.FormularioUbicacion;
import com.infotracktest.autogestion.models.FormulariodatosServicio;
import com.infotracktest.autogestion.tasks.Autogestion;
import com.infotracktest.autogestion.userinterfaces.ObjectdatosServicio;
import com.infotracktest.autogestion.utlis.ExcelReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DatosServicio implements Interaction {

    /**
     * Constructor para la clase Autogestion que inicializa la lista de datos de prueba.
     *
     * @param testData Lista de mapas de cadenas de texto que representan los datos de prueba.
     */
    public DatosServicio(List<Map<String, String>> testData) {
        DatosServicio.testData = testData;
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
    public DatosServicio(String filePath) {
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
    public static DatosServicio withExcelFile() {
        return Tasks.instrumented(DatosServicio.class, DEFAULT_FILE_PATH);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        // Crear una instancia de UbicacionServicio con los datos de prueba inicializados a partir del archivo de Excel por defecto
        DatosServicio datosServicio = new DatosServicio(DEFAULT_FILE_PATH);
        // Obtener el índice de la última fila procesada en la última ejecución
        int lastIndex = Integer.parseInt(System.getProperty("lastProcessedIndex", "-1"));

        for (Map<String, String> row : DatosServicio.testData) {
            FormulariodatosServicio formulariods = new FormulariodatosServicio();
            formulariods.setTipoServicio(row.get("TipoServicio"));
            formulariods.setProducto(row.get("producto"));
            formulariods.setFalla(row.get("falla"));
            formulariods.setObservaciones(row.get("observaciones"));
            formulariods.setRango(row.get("Rango"));
            formulariods.setiDExterno(row.get("idExterno"));
            formulariods.setFecha(row.get("Fecha"));

            // Realizar las acciones en el formulario datos del servicio
            actor.attemptsTo(Wait.until(
                            WebElementQuestion.the(ObjectdatosServicio.TipoServicio),
                            WebElementStateMatchers.isPresent()
                    ).forNoLongerThan(20).seconds(),
                    Enter.theValue(formulariods.getTipoServicio()).into(ObjectdatosServicio.TipoServicio),
                    Click.on(ObjectdatosServicio.TipoServiciolist),
                    Enter.theValue(formulariods.getProducto()).into(ObjectdatosServicio.producto),
                    Click.on(ObjectdatosServicio.productolist),
                    Enter.theValue(formulariods.getFalla()).into(ObjectdatosServicio.falla),
                    Click.on(ObjectdatosServicio.fallalist),
                    Enter.theValue(formulariods.getObservaciones()).into(ObjectdatosServicio.observaciones));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Seleccionar el rango horario de acuerdo a lo establecido en el formato excel.
            if (formulariods.getRango().equals("AM")) {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangoam));
            } else {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangopm));
            }

            // fecha e identificador externo
            actor.attemptsTo(
                    Wait.until(
                            WebElementQuestion.the(ObjectdatosServicio.Fecha),
                            WebElementStateMatchers.isCurrentlyEnabled()
                    ).forNoLongerThan(10).seconds());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Fecha del servicio a agendar
            actor.attemptsTo(
                    Enter.theValue(formulariods.getiDExterno()).into(ObjectdatosServicio.idExterno),
                    Click.on(ObjectdatosServicio.Fecha),
                    Click.on(ObjectdatosServicio.OK),
                    Click.on(ObjectdatosServicio.Programar)
            );
            actor.attemptsTo(Wait.until(WebElementQuestion.the(ObjectdatosServicio.UbicionServicio),
                           WebElementStateMatchers.isPresent()).forNoLongerThan(10).seconds(),
                    Scroll.to(ObjectdatosServicio.UbicionServicio),
                    Click.on(ObjectdatosServicio.Finalizar)
            );


            // Augestión
            actor.attemptsTo(Autogestion.withExcelFile());

        }


    }

}

