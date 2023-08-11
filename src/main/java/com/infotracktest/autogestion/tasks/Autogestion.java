package com.infotracktest.autogestion.tasks;

import com.infotracktest.autogestion.interactions.SeleccionarTipoDocumento;
import com.infotracktest.autogestion.models.FormularioAutogestion;
import com.infotracktest.autogestion.models.FormularioUbicacion;
import com.infotracktest.autogestion.models.FormulariodatosServicio;
import com.infotracktest.autogestion.userinterfaces.ObjectAutogestion;
import com.infotracktest.autogestion.userinterfaces.ObjectdatosServicio;
import com.infotracktest.autogestion.userinterfaces.ObjectubicacionServicio;
import com.infotracktest.autogestion.utlis.DatosRandom;
import com.infotracktest.autogestion.utlis.ExcelReader;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    /**
     * Nombre de la hoja de cálculo que contiene los datos de prueba*/
    private static final String SHEET_NAME = "testData";

    /**
     * Lista de mapas de cadenas de texto que representan los datos de prueba
     */
    private static final String DEFAULT_FILE_PATH = "src\\test\\resources\\data\\testData.xls";

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

        /**
         * Crear una instancia de Autogestion con los datos de prueba inicializados a partir del archivo de Excel por defecto
         * */
        Autogestion autogestion = new Autogestion(DEFAULT_FILE_PATH);

        /**
         * Recorre todas las filas de la lista testData y extrae el valor de la columna "NumDocumento" de cada una.
         * */
        for (Map<String, String> row : autogestion.testData) {

            /**
             * Instancias
             * */
            FormularioAutogestion formulario = new FormularioAutogestion();
            FormularioUbicacion formulariou = new FormularioUbicacion();
            FormulariodatosServicio formulariods = new FormulariodatosServicio();
            int numeroDocumentoAleatorio = DatosRandom.numDocumentoAleatorio();
            /**
             * Datos del usuario
             * */
            formulario.setTipoDocumentos(row.get("TipoDocumento"));
            formulario.setNumDocumentos(row.get("NumDocumento")+numeroDocumentoAleatorio);
            formulario.setCorreoElectronicos(row.get("CorreoElectronico"));
            formulario.setRazonSocials(row.get("RazonSocial"));
            formulario.setNombress(row.get("Nombres"));
            formulario.setApellidoss(row.get("Apellidos"));
            formulario.setTFijos(row.get("TFijo"));
            formulario.setNCelulars(row.get("NCelular"));
            /**
             * Datos de ubicación en el excel
             * */
            formulariou.setCiudad(row.get("Ciudad"));
            formulariou.setDireccion(row.get("Direccion"));
            formulariou.setDatosAdicionales(row.get("DatosAdicionales"));
            /**
             * Datos del servicio
             * */
            formulariods.setTipoServicio(row.get("TipoServicio"));
            formulariods.setProducto(row.get("producto"));
            formulariods.setFalla(row.get("falla"));
            formulariods.setObservaciones(row.get("observaciones"));
            formulariods.setRango(row.get("Rango"));
            formulariods.setiDExterno(row.get("idExterno")+numeroDocumentoAleatorio);
            formulariods.setFecha(row.get("Fecha"));

            actor.attemptsTo(Wait.until(
                            WebElementQuestion.the(ObjectAutogestion.checkedG),
                            WebElementStateMatchers.isPresent()
                    ).forNoLongerThan(60).seconds(),
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

            /**
             * Interfaz datos de Ubicación
             * */
            actor.attemptsTo(Wait.until(WebElementQuestion.the(ObjectubicacionServicio.Ciudad),
                            WebElementStateMatchers.isVisible()).forNoLongerThan(60).seconds(),
                    Enter.theValue(formulariou.getCiudad()).into(ObjectubicacionServicio.Ciudad),
                    Click.on(ObjectubicacionServicio.ListCiudad),
                    Enter.theValue(formulariou.getDireccion()).into(ObjectubicacionServicio.Direccion),
                    Click.on(ObjectubicacionServicio.Buscar)
            );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            actor.attemptsTo(
                    Enter.theValue(formulariou.getDatosAdicionales())
                            .into(ObjectubicacionServicio.DatosAdicionales)

            );
            actor.attemptsTo(Scroll.to(ObjectubicacionServicio.Continuar),
                    Click.on(ObjectubicacionServicio.Continuar));

            /**
             * Interfaz de Datos del servicio
             * */

            /**
             * Realizar las acciones en el formulario datos del servicio
             * */
            actor.attemptsTo(Wait.until(
                            WebElementQuestion.the(ObjectdatosServicio.TipoServicio),
                            WebElementStateMatchers.isPresent()
                    ).forNoLongerThan(60).seconds(),
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

            /**
             * Seleccionar el rango horario de acuerdo a lo establecido en el formato excel.
             * */
            if (formulariods.getRango().equals("AM")) {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangoam));
            } else {
                actor.attemptsTo(Click.on(ObjectdatosServicio.Rangopm));
            }

            /**
             * fecha e identificador externo
             * */
            actor.attemptsTo(
                    Wait.until(
                            WebElementQuestion.the(ObjectdatosServicio.Fecha),
                            WebElementStateMatchers.isClickable()
                    ).forNoLongerThan(60).seconds());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            /**
             *  Fecha del servicio a agendar
             * * */
            actor.attemptsTo(
                    Enter.theValue(formulariods.getiDExterno()).into(ObjectdatosServicio.idExterno),
                    Wait.until(WebElementQuestion.the(ObjectdatosServicio.Fecha),
                            WebElementStateMatchers.isPresent()).forNoLongerThan(60).seconds(),
            Click.on(ObjectdatosServicio.Fecha),
                    Click.on(ObjectdatosServicio.OK),
                    Click.on(ObjectdatosServicio.Programar)
            );
            actor.attemptsTo(Wait.until(WebElementQuestion.the(ObjectdatosServicio.UbicionServicio),
                            WebElementStateMatchers.isPresent()).forNoLongerThan(60).seconds(),
                    Scroll.to(ObjectdatosServicio.UbicionServicio),
                    Click.on(ObjectdatosServicio.Finalizar)
            );
        }
        /**
         * Una vez finalice de crear los casos del archico cierro la ventana del navegador*/
        WebDriver driver = new ChromeDriver(); // Inicializar el WebDriver con el navegador deseado
        driver.quit(); // Cerrar el navegador y liberar recursos



    }
}