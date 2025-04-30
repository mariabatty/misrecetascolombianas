package com.example.misrecetascolombianas;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FullscreenVideoActivity extends AppCompatActivity {
    public static final String EXTRA_VIDEO_RES = "extra_video_res";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Oculta barra de sistema (inmersivo)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        setContentView(R.layout.activity_fullscreen_video);

        int videoResId = getIntent().getIntExtra(EXTRA_VIDEO_RES, 0);
        VideoView vv = findViewById(R.id.vvFullscreen);
        String uri = "android.resource://" + getPackageName() + "/" + videoResId;
        vv.setVideoURI(Uri.parse(uri));

        MediaController mc = new MediaController(this);
        mc.setAnchorView(vv);
        vv.setMediaController(mc);
        // No auto-start, usuario da play
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Re-aplicar inmersivo en cambios de foco
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
