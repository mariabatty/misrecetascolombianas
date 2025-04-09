package com.example.misrecetascolombianas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Cargar la pantalla de inicio por defecto
        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();
            if (id == R.id.nav_inicio) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_favoritos) {
                selectedFragment = new FavoritosFragment();
            } else if (id == R.id.nav_bookmark) {
                selectedFragment = new BookmarkFragment();
            } else if (id == R.id.nav_mas) {
                selectedFragment = new MasFragment();
            }

            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
