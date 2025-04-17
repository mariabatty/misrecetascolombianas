package com.example.misrecetascolombianas; // Cambia esto si tu paquete tiene otro nombre

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Conectamos con el archivo XML que hiciste
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}
