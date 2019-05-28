package com.example.project;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Pikachu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pikachu);
        MediaPlayer bgSound = MediaPlayer.create(this, R.raw.bgm);
        DrawView drawView = new DrawView(this);
        setContentView(drawView);
        bgSound.start();
        drawView.requestFocus();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_pikachu, menu);
        return true;
    }
}
