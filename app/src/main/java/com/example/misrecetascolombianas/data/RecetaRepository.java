package com.example.misrecetascolombianas.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.misrecetascolombianas.R;
import com.example.misrecetascolombianas.model.Receta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RecetaRepository {
    private static final String FILE_NAME = "recetas.json";

    private static String readStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toString("UTF-8");
    }

    public static List<Receta> loadAll(Context ctx) {
        try (InputStream is = ctx.getResources().openRawResource(R.raw.recetas)) {
            String json = readStream(is);
            Type listType = new TypeToken<List<Receta>>(){}.getType();
            List<Receta> lista = new Gson().fromJson(json, listType);
            // Resuelve los IDs de drawable
            for (Receta r : lista) {
                r.resolveResources(ctx);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
