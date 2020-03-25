package com.example.appcamping.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module2 {

    public ArrayList<String> garrList = new ArrayList<>();
    public ArrayAdapter<String> garrAdp;
    public String gvalue_nombregasto;
    public String gvalue_precio;
    public String gvalue_fechagasto;

    public String getGvalue_nombregasto() {
        return gvalue_nombregasto;
    }

    public void setGvalue_nombregasto(String gvalue_nombregasto) {
        this.gvalue_nombregasto = gvalue_nombregasto;
    }

    public String getGvalue_precio() {
        return gvalue_precio;
    }

    public void setGvalue_precio(String gvalue_precio) {
        this.gvalue_precio = gvalue_precio;
    }

    public String getGvalue_fechagasto() {
        return gvalue_fechagasto;
    }

    public void setGvalue_fechagasto(String gvalue_fechagasto) {
        this.gvalue_fechagasto = gvalue_fechagasto;
    }
}
