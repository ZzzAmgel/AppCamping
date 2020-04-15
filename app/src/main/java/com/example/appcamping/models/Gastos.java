package com.example.appcamping.models;

public class Gastos {
    String FechaGasto;
    String NombreGasto;
    String Precio;
    public Gastos(){}
    public Gastos(String fechaGasto, String nombreGasto, String precio){
        FechaGasto = fechaGasto;
        NombreGasto = nombreGasto;
        Precio = precio;
    }

    public String getFechaGasto() {
        return FechaGasto;
    }

    public void setFechaGasto(String fechaGasto) {
        FechaGasto = fechaGasto;
    }

    public String getNombreGasto() {
        return NombreGasto;
    }

    public void setNombreGasto(String nombreGasto) {
        NombreGasto = nombreGasto;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }


    public String toString(){
        return "Nombre del producto:  " + NombreGasto + "\n" + "Precio:  " + Precio + "\n" + "Día:  "+FechaGasto;
    }
}
