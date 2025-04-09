package com.example.misrecetascolombianas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvRecetas;
    private EditText etBuscar;
    private RecetaAdapter adapter;
    private List<Receta> recetaListOriginal;
    private List<Receta> recetaListFiltrada;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rvRecetas = root.findViewById(R.id.rvRecetas);
        etBuscar = root.findViewById(R.id.etBuscar);

        // Lista original de recetas
        recetaListOriginal = new ArrayList<>();
        recetaListOriginal.add(new Receta("Arroz ranchero", R.drawable.arroz_ranchero));
        recetaListOriginal.add(new Receta("Arepas con queso", R.drawable.arepas_queso));
        recetaListOriginal.add(new Receta("Buñuelos", R.drawable.bunuelos));
        recetaListOriginal.add(new Receta("Bandeja paisa", R.drawable.bandeja_paisa));
        // Agrega más si deseas

        // Crear una copia para filtrar
        recetaListFiltrada = new ArrayList<>(recetaListOriginal);

        // Configurar el adaptador
        adapter = new RecetaAdapter(getContext(), recetaListFiltrada);
        rvRecetas.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvRecetas.setAdapter(adapter);

        // Lógica del buscador
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarRecetas(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return root;
    }

    private void filtrarRecetas(String texto) {
        recetaListFiltrada.clear();
        if (texto.isEmpty()) {
            recetaListFiltrada.addAll(recetaListOriginal);
        } else {
            for (Receta receta : recetaListOriginal) {
                if (receta.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    recetaListFiltrada.add(receta);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
