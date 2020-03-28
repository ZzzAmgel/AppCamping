package com.example.appcamping.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
//casa, String correo, String fechaFin, String fechaInicio, String numAdultos, String numNinos,String numeroTelefono, String titulo
public class Module3 {
    public ArrayList<String> garrList = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_casa;
    public String gvalue_correo;
    public String gvalue_fechaFin;
    public String gvalue_fechaInicio;
    public String gvalue_numAdultos;
    public String gvalue_numNinos;
    public String gvalue_numeroTelefono;
    public String gvalue_titulo;

    public String getGvalue_casa() {
        return gvalue_casa;
    }

    public void setGvalue_casa(String gvalue_casa) {
        this.gvalue_casa = gvalue_casa;
    }

    public String getGvalue_correo() {
        return gvalue_correo;
    }

    public void setGvalue_correo(String gvalue_correo) {
        this.gvalue_correo = gvalue_correo;
    }

    public String getGvalue_fechaFin() {
        return gvalue_fechaFin;
    }

    public void setGvalue_fechaFin(String gvalue_fechaFin) {
        this.gvalue_fechaFin = gvalue_fechaFin;
    }

    public String getGvalue_fechaInicio() {
        return gvalue_fechaInicio;
    }

    public void setGvalue_fechaInicio(String gvalue_fechaInicio) {
        this.gvalue_fechaInicio = gvalue_fechaInicio;
    }

    public String getGvalue_numAdultos() {
        return gvalue_numAdultos;
    }

    public void setGvalue_numAdultos(String gvalue_numAdultos) {
        this.gvalue_numAdultos = gvalue_numAdultos;
    }

    public String getGvalue_numNinos() {
        return gvalue_numNinos;
    }

    public void setGvalue_numNinos(String gvalue_numNinos) {
        this.gvalue_numNinos = gvalue_numNinos;
    }

    public String getGvalue_numeroTelefono() {
        return gvalue_numeroTelefono;
    }

    public void setGvalue_numeroTelefono(String gvalue_numeroTelefono) {
        this.gvalue_numeroTelefono = gvalue_numeroTelefono;
    }

    public String getGvalue_titulo() {
        return gvalue_titulo;
    }

    public void setGvalue_titulo(String gvalue_titulo) {
        this.gvalue_titulo = gvalue_titulo;
    }
}
