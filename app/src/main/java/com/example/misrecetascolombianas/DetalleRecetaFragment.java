package com.example.misrecetascolombianas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.misrecetascolombianas.model.Receta;

public class DetalleRecetaFragment extends Fragment {
    private static final String ARG_KEY = "arg_receta";

    public static DetalleRecetaFragment newInstance(Receta r) {
        DetalleRecetaFragment f = new DetalleRecetaFragment();
        Bundle b = new Bundle();
        b.putSerializable(ARG_KEY, r);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             ViewGroup ctr, Bundle s) {
        return inf.inflate(R.layout.fragment_detalle_receta, ctr, false);
    }

    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle s) {
        super.onViewCreated(v, s);
        // Toolbar
        ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();

        Receta receta = (Receta) requireArguments().getSerializable(ARG_KEY);

        // Titulo
        TextView tvTitulo = v.findViewById(R.id.tvTituloReceta);
        tvTitulo.setText(receta.getNombre());

        // Galería
        ViewPager2 vp = v.findViewById(R.id.vpGaleria);
        vp.setAdapter(new ImageCarouselAdapter(
                receta.getGaleriaResIds()
        ));

        // Pasos
        RecyclerView rvP = v.findViewById(R.id.rvPasos);
        rvP.setLayoutManager(new LinearLayoutManager(getContext()));
        rvP.setAdapter(new PasosAdapter(receta.getPasos()));

        // Video
        VideoView vv = v.findViewById(R.id.videoView);
        int vidRes = receta.getVideoResId();
        String uriStr = "android.resource://"
                + requireContext().getPackageName()
                + "/" + vidRes;
        vv.setVideoURI(Uri.parse(uriStr));

        FrameLayout container = v.findViewById(R.id.video_container);
        MediaController mc = new MediaController(requireContext());
        vv.setMediaController(mc);
        mc.setAnchorView(container);
//        vv.start();

        ImageButton btnFull = v.findViewById(R.id.btnFullscreen);
        btnFull.post(() -> btnFull.bringToFront());
        btnFull.setOnClickListener(x -> {
            Intent i = new Intent(requireContext(), FullscreenVideoActivity.class);
            i.putExtra(FullscreenVideoActivity.EXTRA_VIDEO_RES, vidRes);
            startActivity(i);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
    }
}
