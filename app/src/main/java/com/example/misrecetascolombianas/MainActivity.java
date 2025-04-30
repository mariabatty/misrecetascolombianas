package com.example.misrecetascolombianas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar())
                .setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MenuFragment())
                    .addToBackStack(null)
                    .commit();

            getSupportActionBar().setTitle("Menú");
        });

        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Cargar la pantalla de inicio por defecto con título
        loadFragment(new HomeFragment(), "Inicio");

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String title = "";

            int id = item.getItemId();
            if (id == R.id.nav_inicio) {
                selectedFragment = new HomeFragment();
                title = "Inicio";
            } else if (id == R.id.nav_favoritos) {
                selectedFragment = new FavoritosFragment();
                title = "Favoritos";
            } else if (id == R.id.nav_bookmark) {
                selectedFragment = new BookmarkFragment();
                title = "Guardados";
            } else if (id == R.id.nav_mas) {
                selectedFragment = new TiendasFragment();
                title = "Tiendas";
            }

            return loadFragment(selectedFragment, title);
        });
    }

    private boolean loadFragment(Fragment fragment, String title) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            Objects.requireNonNull(getSupportActionBar()).setTitle(title);
            return true;
        }
        return false;
    }
}
