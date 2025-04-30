package com.example.misrecetascolombianas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misrecetascolombianas.data.FavoriteManager;
import com.example.misrecetascolombianas.model.Receta;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {

    private List<Receta> recetas;
    private Context context;

    public RecetaAdapter(Context context, List<Receta> recetas) {
        this.context = context;
        this.recetas = recetas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receta receta = recetas.get(position);
        holder.nombre.setText(receta.getNombre());
        holder.imagen.setImageResource(receta.getImagenPrincipalResId());

        if (receta.isFavorita()) {
            holder.favorito.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.favorito.setImageResource(R.drawable.ic_favorite_border);
        }

        holder.favorito.setOnClickListener(v ->{
            boolean nueva = !receta.isFavorita();
            receta.setFavorita(nueva);
            notifyItemChanged(position);
            if (nueva) {
                FavoriteManager.addFavorite(context, receta.getNombre());
            } else {
                FavoriteManager.removeFavorite(context, receta.getNombre());
            }
            notifyItemChanged(position);
        });

        holder.itemView.setOnClickListener(v -> {
            ((AppCompatActivity)context).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            DetalleRecetaFragment.newInstance(receta))
                    .addToBackStack(null)
                    .commit();
        });

    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre;
        ImageButton favorito;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgReceta);
            nombre = itemView.findViewById(R.id.tvNombreReceta);
            favorito = itemView.findViewById(R.id.btnFavorito);
        }
    }
}
