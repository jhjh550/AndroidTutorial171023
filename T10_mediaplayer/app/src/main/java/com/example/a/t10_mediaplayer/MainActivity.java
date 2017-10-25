package com.example.a.t10_mediaplayer;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar) findViewById(R.id.seekBar);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions,0);
        }
    }


    MediaPlayer mp = null;
    public void onPlayClick(View v){
        String path = Environment.getExternalStorageDirectory()
                +"/Download/Kalimba.mp3";

        mp = new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            seekbar.setMax(mp.getDuration());
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(mp != null){
                        try {
                            seekbar.setProgress(mp.getCurrentPosition());
                        }catch (Exception e){
                            seekbar.setProgress(0);
                        }
                    }
                }
            });
            th.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onStopClick(View v){
        if( mp != null ){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
