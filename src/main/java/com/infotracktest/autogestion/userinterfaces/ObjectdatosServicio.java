package com.infotracktest.autogestion.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ObjectdatosServicio extends PageObject {

    public static final Target TipoServicio = Target.the("¿Qué tipo de servicio solicitas?")
            .located(By.id("TipoServicio"));

    public static final Target TipoServiciolist = Target.the("¿Qué tipo de servicio solicitas? Lista")
            .located(By.xpath("//li[@id='TipoServicio-option-0']"));

    public static final Target producto = Target.the("¿Cuál es el producto?")
            .located(By.id("producto"));

    public static final Target productolist = Target.the("¿Cuál es el producto? Lista")
            .located(By.xpath("//li[@id='producto-option-0']"));

    public static final Target falla = Target.the("¿Cuál es el motivo de la reparación?")
            .located(By.id("falla"));

    public static final Target fallalist = Target.the("¿Cuál es el motivo de la reparación? Lista")
            .located(By.xpath("//li[@id='falla-option-0']"));

    public static final Target observaciones = Target.the("observaciones")
            .located(By.id("observaciones"));

    // Rango horario
    public static final Target Rangoam = Target.the("Rango AM")
            .located(By.xpath("(//input[@value='AM'])[1]"));

    public static final Target Rangopm = Target.the("Rango PM")
            .located(By.xpath("(//input[@value='PM'])[1]"));

    public static final Target idExterno = Target.the("Rango PM")
            .located(By.id("idExterno"));

    public static final Target Fecha = Target.the("Fecha")
            .located(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='PM'])[1]/following::*[name()='svg'][1]"));

    public static final Target Programar = Target.the("Programar")
            .located(By.xpath("(//span[normalize-space()='Programar'])[1]"));
}
