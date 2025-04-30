package com.example.misrecetascolombianas.data;



import android.content.Context;

import com.example.misrecetascolombianas.model.Tienda;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TiendaRepository {
    private static final String FILE = "tiendas.json";

    private static String readAll(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int r;
        while ((r = is.read(buf)) != -1) baos.write(buf, 0, r);
        return baos.toString("UTF-8");
    }

    public static List<Tienda> loadAll(Context ctx) {
        try (InputStream is = ctx.getResources().openRawResource(
                ctx.getResources().getIdentifier(
                        FILE.replace(".json",""),
                        "raw", ctx.getPackageName()
                ))) {
            String json = readAll(is);
            Type listType = new TypeToken<List<Tienda>>(){}.getType();
            List<Tienda> lista = new Gson().fromJson(json, listType);
            for (Tienda t : lista) {
                t.resolveResources(ctx);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
