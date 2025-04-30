package com.example.misrecetascolombianas.model;

import android.content.Context;

public class Tienda {
    private String nombre;
    private String logo;
    private String url;

    private transient int logoResId;

    public Tienda() {}

    public String getNombre() { return nombre; }
    public String getLogo()   { return logo; }
    public String getUrl()    { return url; }

    public int getLogoResId() { return logoResId; }

    public void resolveResources(Context ctx) {
        logoResId = ctx.getResources()
                .getIdentifier(logo, "drawable", ctx.getPackageName());
    }
}
