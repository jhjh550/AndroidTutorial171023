package com.example.a.p02_mediaplayer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    PlayerService playerService = null;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayerService.MyBinder myBinder = (PlayerService.MyBinder) iBinder;
            playerService = myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            playerService = null;
        }
    };

    Boolean isDestroyed = false;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    if(playerService != null){
                      playerService.seekTo(i);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDestroyed == false){
                    if(playerService != null){
                        try {
                            Thread.sleep(3000);
                            seekBar.setProgress(playerService.getCurrentPostion());
                        }catch (Exception e){
                            seekBar.setProgress(0);
                        }
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    public void onPlayClick(View v){
        if(playerService != null) {
            int duration = playerService.play();
            seekBar.setMax(duration);
        }
    }

    public void onStopClick(View v){
        if(playerService != null)
            playerService.stop();
    }
}
