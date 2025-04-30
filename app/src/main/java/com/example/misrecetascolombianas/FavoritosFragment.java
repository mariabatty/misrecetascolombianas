package com.example.misrecetascolombianas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misrecetascolombianas.data.FavoriteManager;
import com.example.misrecetascolombianas.data.RecetaRepository;
import com.example.misrecetascolombianas.model.Receta;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoritosFragment extends Fragment {

    private List<Receta> recetaFavoriteList;
    private RecetaAdapter adapter;

    public FavoritosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Toolbar
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        activity.getSupportActionBar().setTitle("Favoritas");



        // Carga de recertas
        List<Receta> todas = RecetaRepository.loadAll(requireContext());
        // Filtramos favoritas
        for (Receta r : todas) {
            r.setFavorita(FavoriteManager.getFavorites(requireContext()).contains(r.getNombre()));
        }
        recetaFavoriteList = new ArrayList<>();
        for (Receta r : todas) {
            if (r.isFavorita()) recetaFavoriteList.add(r);
        }
        // View
        RecyclerView rvFav = view.findViewById(R.id.rvRecetasFavoritas);
        adapter = new RecetaAdapter(requireContext(), recetaFavoriteList);
        rvFav.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        rvFav.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle("Favorito");
    }
}
