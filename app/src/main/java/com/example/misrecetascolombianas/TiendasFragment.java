package com.example.misrecetascolombianas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misrecetascolombianas.data.TiendaRepository;
import com.example.misrecetascolombianas.model.Tienda;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TiendasFragment extends Fragment {
    public TiendasFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             ViewGroup ctr, Bundle s) {
        return inf.inflate(R.layout.fragment_tiendas, ctr, false);
    }

    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        super.onViewCreated(v, s);
        // Configura Toolbar
        AppCompatActivity act = (AppCompatActivity) requireActivity();

        // Carga lista de tiendas y adapter
        RecyclerView rv = v.findViewById(R.id.rvTiendas);
        rv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        List<Tienda> tiendas = TiendaRepository.loadAll(requireContext());
        TiendaAdapter adapter = new TiendaAdapter(requireContext(), tiendas);
        rv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar())
                .setTitle("Tiendas");
    }

}
