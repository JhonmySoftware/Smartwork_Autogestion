package com.infotracktest.autogestion.models;

public class FormularioUbicacion {
    private String Ciudad;
    private String Direccion;
    private String DatosAdicionales;

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getDatosAdicionales() {
        return DatosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        DatosAdicionales = datosAdicionales;
    }
}
