package com.example.appcamping.models;

public class Reservas {
    String Casa;
    String Correo;
    String FechaFin;
    String FechaInicio;
    String NumAdultos;
    String NumNinos;
    String NumeroTelefono;
    String Titulo;

    public Reservas(){}
    public Reservas(String casa, String correo, String fechaFin, String fechaInicio, String numAdultos, String numNinos,String numeroTelefono, String titulo){
        Casa = casa;
        Correo = correo;
        FechaFin = fechaFin;
        FechaInicio = fechaInicio;
        NumAdultos = numAdultos;
        NumNinos = numNinos;
        NumeroTelefono = numeroTelefono;
        Titulo = titulo;
    }

    public String getCasa() {
        return Casa;
    }

    public void setCasa(String casa) {
        Casa = casa;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getNumAdultos() {
        return NumAdultos;
    }

    public void setNumAdultos(String numAdultos) {
        NumAdultos = numAdultos;
    }

    public String getNumNinos() {
        return NumNinos;
    }

    public void setNumNinos(String numNinos) {
        NumNinos = numNinos;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        NumeroTelefono = numeroTelefono;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String toString(){
        return "Casa o parcela: "+ Casa + "\nDirección de correo: " + Correo +"\nDía de Salida"+ FechaFin +"\nDía de Entrada: "+ FechaInicio +"\nNº Adultos: "+ NumAdultos +"\nNº Niños: "+ NumNinos +"\nTelefono: "+ NumeroTelefono +"\nTitular: "+ Titulo;
    }

}
