package com.infotracktest.autogestion.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ObjectAutogestion extends PageObject {
    public static final Target checkedG = Target.the("Heabeas Data")
            .located(By.name("checkedG"));
    public static final Target TipoDocumento = Target.the("Seleccionar el tipo de documento")
            .located(By.id("tipoCliente"));
    public static final Target NumDocumento = Target.the("Seleccionar el tipo de documento")
            .located(By.id("idCliente"));
    public static final Target CorreoElectronico = Target.the("¿Cuál es tu correo electrónico?")
            .located(By.id("email"));
    public static final Target RazonSocial = Target.the("Ingresa tu nombre")
            .located(By.id("razonSocial"));
    public static final Target Nombres = Target.the("¿Cuales son tus apellidos?")
            .located(By.id("nombrePersona"));
    public static final Target Apellidos = Target.the("¿Cual es tu telefono fijo?")
            .located(By.id("apellidoPersona"));
    public static final Target TFijo = Target.the("Ingresa tu numero celular")
            .located(By.id("telefonoFijo"));
    public static final Target NCelular = Target.the("Seleccionar el tipo de documento")
            .located(By.id("telefonoCelular"));
    public static final Target Continuar = Target.the("Continuar")
            .located(By.xpath("(//span[@class='MuiButton-label'])[1]"));


}
