package com.example.tetris;

import android.graphics.Bitmap;

public class DatosRanking {
    private String id;
    private String nombre;
    private String modo;
    private  int puntuacion;
    private Bitmap imagen;


    public DatosRanking(String id,String nombre, String modo, int puntuacion, Bitmap imagen) {

        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.modo = modo;
        this.imagen = imagen;

    }


    public String getNombre(){
        return this.nombre;
    }

    public String getModo(){
        return this.modo;
    }

    public int getPuntuacion(){
        return this.puntuacion;
    }

    public String getId(){
        return this.id;
    }

    public Bitmap getImagen(){
        return this.imagen;
    }

}
