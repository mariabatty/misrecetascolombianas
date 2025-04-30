package com.example.misrecetascolombianas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PasosAdapter extends RecyclerView.Adapter<PasosAdapter.VH> {

    private final List<String> pasos;
    public PasosAdapter(List<String> pasos) {
        this.pasos = pasos;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int vt) {
        TextView tv = new TextView(parent.getContext());
        tv.setPadding(8,8,8,8);
        return new VH(tv);
    }
    @Override public void onBindViewHolder(@NonNull VH h, int i) {
        h.tv.setText((i+1) + ". " + pasos.get(i));
    }
    @Override public int getItemCount() { return pasos.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;
        public VH(@NonNull View v) {
            super(v);
            tv = (TextView)v;
        }
    }
}
