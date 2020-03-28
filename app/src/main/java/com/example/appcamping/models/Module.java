package com.example.appcamping.models;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {
    public ArrayList<String> garrList = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_titulo;
    public String gvalue_descripcion;
    public String gvalue_fecha;
    public String gvalue_url;


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

    public String getGvalue_fecha() {
        return gvalue_fecha;
    }

    public void setGvalue_fecha(String gvalue_fecha) {
        this.gvalue_fecha = gvalue_fecha;
    }

    public String getGvalue_url() {
        return gvalue_url;
    }

    public void setGvalue_url(String gvalue_url) {
        this.gvalue_url = gvalue_url;
    }
}
