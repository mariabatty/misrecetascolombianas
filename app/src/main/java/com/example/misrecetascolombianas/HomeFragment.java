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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misrecetascolombianas.data.FavoriteManager;
import com.example.misrecetascolombianas.data.RecetaRepository;
import com.google.android.material.appbar.MaterialToolbar;

import com.example.misrecetascolombianas.model.Receta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated( @NonNull View root, @Nullable Bundle savedInstanceState ){
        super.onViewCreated(root, savedInstanceState);




        rvRecetas = root.findViewById(R.id.rvRecetas);
        etBuscar = root.findViewById(R.id.etBuscar);

        // Lista original de recetas
        recetaListOriginal = RecetaRepository.loadAll(requireContext());
        Set<String> favs = FavoriteManager.getFavorites(requireContext());
        for (Receta r : recetaListOriginal) {
            r.setFavorita(favs.contains(r.getNombre()));
        }

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

    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle("Inicio");
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
