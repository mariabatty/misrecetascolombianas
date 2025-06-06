package com.example.misrecetascolombianas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {

    public MenuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Captura del btn
        View btnPerfil = view.findViewById(R.id.btnMiPerfil);
        btnPerfil.setOnClickListener( v -> {
            // Creamos el perfil
            PerfilFragment perfil = new PerfilFragment();

            // Remplazamos
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, perfil)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
