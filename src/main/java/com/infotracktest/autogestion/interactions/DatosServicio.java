package com.infotracktest.autogestion.interactions;

import com.infotracktest.autogestion.models.FormularioUbicacion;
import com.infotracktest.autogestion.models.FormulariodatosServicio;
import com.infotracktest.autogestion.userinterfaces.ObjectdatosServicio;
import com.infotracktest.autogestion.utlis.ExcelReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
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
            actor.attemptsTo(
                    Enter.theValue(formulariods.getTipoServicio()).into(ObjectdatosServicio.TipoServicio),
                    Click.on(ObjectdatosServicio.TipoServiciolist),
                    Enter.theValue(formulariods.getProducto()).into(ObjectdatosServicio.producto),
                    Click.on(ObjectdatosServicio.productolist),
                    Enter.theValue(formulariods.getFalla()).into(ObjectdatosServicio.falla),
                    Click.on(ObjectdatosServicio.fallalist),
                    Enter.theValue(formulariods.getObservaciones()).into(ObjectdatosServicio.observaciones));


            // Seleccionar el rango horario de acuerdo a lo establecido en el formato excel.
            if (formulariods.getRango().equals("AM")) {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangoam));
            } else {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangopm));
            }

            // fecha e identificador externo
            actor.attemptsTo(
                    Enter.theValue(formulariods.getiDExterno()).into(ObjectdatosServicio.idExterno),
                    Click.on(ObjectdatosServicio.Fecha)
            );
            break; // Detener la primera iteración

        }
    }
}
