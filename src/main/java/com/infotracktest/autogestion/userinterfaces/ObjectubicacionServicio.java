package com.infotracktest.autogestion.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ObjectubicacionServicio extends PageObject {

    // Formulario de ubicacionServicio

    public static final Target Ciudad = Target.the("Ciudad")
            .located(By.id("ciudad"));

    public static final Target ListCiudad = Target.the("Lista de ciudades")
            .located(By.xpath("//li[@id='ciudad-option-0']"));

    public static final Target Direccion = Target.the("Direccion")
            .located(By.id("DatosDireccion"));

    public static final Target Buscar = Target.the("Buscar la dirección")
            .located(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root']//span[@class='MuiIconButton-label']//*[name()='svg']"));

    public static final Target DatosAdicionales = Target.the("Datos adicionales")
            .located(By.id("datosAdicionales"));

    public static final Target Continuar = Target.the("Continuar ubicación servicio")
            .located(By.xpath("(//span[normalize-space()='Continuar'])[1]"));
}
