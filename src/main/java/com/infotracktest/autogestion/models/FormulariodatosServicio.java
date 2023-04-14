package com.infotracktest.autogestion.models;

public class FormulariodatosServicio {

    private String iDExterno;
    private String Fecha;
    private String TipoServicio;
    private String producto;
    private String falla;
    private String observaciones;
    private String Rango;


    public String getTipoServicio() {
        return TipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        TipoServicio = tipoServicio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String rango) {
        Rango = rango;
    }

    public String getiDExterno() {
        return iDExterno;
    }

    public void setiDExterno(String iDExterno) {
        this.iDExterno = iDExterno;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
