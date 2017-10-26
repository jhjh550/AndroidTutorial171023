package com.example.a.p02_mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import java.io.IOException;

public class PlayerService extends Service {
    MediaPlayer mp = null;

    public PlayerService() {
    }
    public class MyBinder extends Binder {
        public PlayerService getService(){
            return PlayerService.this;
        }
    }
    MyBinder binder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void play(){
        String path = Environment.getExternalStorageDirectory()+
                "/Download/Kalimba.mp3";
        mp = new MediaPlayer();
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
