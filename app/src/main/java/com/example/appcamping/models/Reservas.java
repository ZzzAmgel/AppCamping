package com.example.appcamping.models;

import android.widget.EditText;

public class Reservas {
    String Casa;
    String DNI;
    String Email;
    String FechaFin;
    String FechaInicio;
    String NumAdultos;
    String NumNinos;
    String NumeroTelefono;
    String Titulo;

    public Reservas(){}
    public Reservas(String casa, String dni, String email, String fechaFin, String fechaInicio, String numAdultos, String numNinos,String numeroTelefono, String titulo){
        Casa = casa;
        DNI = dni;
        Email = email;
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

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dni) {
        DNI = dni;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
        return "Casa o parcela: "+ Casa + "\nDni: " + DNI + "\nDirección de correo: " + Email +"\nDía de Salida: "+ FechaFin +"\nDía de Entrada: "+ FechaInicio +"\nNº Adultos: "+ NumAdultos +"\nNº Niños: "+ NumNinos +"\nTelefono: "+ NumeroTelefono +"\nTitular: "+ Titulo;
    }

}
