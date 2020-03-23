package com.example.appcamping.models;

public class Mensaje {

    private String Titulo;
    private String Descripcion;
    private String Fecha;

    public Mensaje(){}
    public Mensaje(String Tilulo,String Descripcion,String Fecha){

        this.Titulo = Tilulo;
        this.Descripcion = Descripcion;
        this.Fecha = Fecha;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


}
