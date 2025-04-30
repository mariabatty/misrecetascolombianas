package com.example.misrecetascolombianas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.VH> {

    private final List<Integer> images;
    public GaleriaAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView iv = new ImageView(parent.getContext());
        iv.setLayoutParams(new ViewGroup.LayoutParams(200,200));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new VH(iv);
    }
    @Override public void onBindViewHolder(@NonNull VH h, int i) {
        h.iv.setImageResource(images.get(i));
    }
    @Override public int getItemCount() { return images.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView iv;
        public VH(@NonNull View v) {
            super(v);
            iv = (ImageView)v;
        }
    }

}
