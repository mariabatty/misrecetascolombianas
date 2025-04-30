package com.example.misrecetascolombianas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misrecetascolombianas.model.Tienda;

import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.VH> {
    private final Context context;
    private final List<Tienda> items;

    public TiendaAdapter(Context ctx, List<Tienda> tiendas) {
        this.context = ctx;
        this.items   = tiendas;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int vt) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_tienda, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Tienda t = items.get(pos);
        h.tvNombre.setText(t.getNombre());
        h.imgLogo.setImageResource(t.getLogoResId());

        h.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, WebViewActivity.class);
            i.putExtra(WebViewActivity.EXTRA_URL, t.getUrl());
            context.startActivity(i);
        });

    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView tvNombre;
        VH(@NonNull View itemView) {
            super(itemView);
            imgLogo  = itemView.findViewById(R.id.imgLogo);
            tvNombre = itemView.findViewById(R.id.tvNombreTienda);
        }
    }
}
