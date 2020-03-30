package com.example.appcamping.models;

public class Upload {

    private String mName;
    private String mImageUrl;
    //private String mDescripcion;
    //private String mFecha;

    public Upload(){

    }


    public Upload(String name, String imageUrl){


        mName = name;
        //mDescripcion = descripcion;
        //mFecha = fecha;
        mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }


}
