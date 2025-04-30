package com.example.misrecetascolombianas.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receta implements Serializable {

    private String nombre;
    private String imagenPrincipal;
    private List<String> galeria;
    private List<String> pasos;
    private String video;
    private boolean favorita;
    private transient int videoResId;

    //Multimedia
    private transient int imagenPrincipalResId;
    private transient List<Integer> galeriaResIds;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<String> getGaleria() {
        return galeria;
    }

    public void setGaleria(List<String> galeria) {
        this.galeria = galeria;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public int getImagenPrincipalResId() {
        return imagenPrincipalResId;
    }

    public void setImagenPrincipalResId(int imagenPrincipalResId) {
        this.imagenPrincipalResId = imagenPrincipalResId;
    }

    public List<Integer> getGaleriaResIds() {
        return galeriaResIds;
    }

    public void setGaleriaResIds(List<Integer> galeriaResIds) {
        this.galeriaResIds = galeriaResIds;
    }

    public int getVideoResId() {
        return videoResId;
    }
    // Constructor para GSON
    public Receta(){
    }

    // Despues del parceo convertimos nombres en ID's
    public void resolveResources(Context ctx) {
        imagenPrincipalResId = ctx.getResources()
                .getIdentifier(imagenPrincipal, "drawable", ctx.getPackageName());

        galeriaResIds = new ArrayList<>();
        for (String name : galeria) {
            String base = name.contains(".")
                    ? name.substring(0, name.lastIndexOf('.'))
                    : name;
            int id = ctx.getResources()
                    .getIdentifier(base, "drawable", ctx.getPackageName());
            galeriaResIds.add(id);
        }

        String vidName = video.contains(".")
                ? video.substring(0, video.lastIndexOf('.'))
                : video;
        videoResId = ctx.getResources()
                .getIdentifier(vidName, "raw", ctx.getPackageName());
    }

}
