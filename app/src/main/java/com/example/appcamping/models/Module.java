package com.example.appcamping.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module {
    public ArrayList<String> garrList = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_titulo;
    public String gvalue_descripcion;


    public String getGvalue_titulo() {
        return gvalue_titulo;
    }

    public void setGvalue_titulo(String gvalue_titulo) {
        this.gvalue_titulo = gvalue_titulo;
    }

    public String getGvalue_descripcion() {
        return gvalue_descripcion;
    }

    public void setGvalue_descripcion(String gvalue_descripcion) {
        this.gvalue_descripcion = gvalue_descripcion;
    }
}
