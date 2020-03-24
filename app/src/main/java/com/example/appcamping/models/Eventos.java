package com.example.appcamping.models;

public class Eventos {
    String Titulo;
    String Descripcion;
    String Fecha;
    public Eventos(){

    }

    public Eventos(String titulo, String descripcion, String fecha){
        Titulo = titulo;
        Descripcion = descripcion;
        Fecha = fecha;
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
    public String toString(){
        return Titulo + "\n" + Descripcion + "\n" + Fecha;
    }


}
