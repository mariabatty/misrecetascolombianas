package com.example.misrecetascolombianas;

public class Receta {
    private String nombre;
    private int imagenResId;

    public Receta(String nombre, int imagenResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
