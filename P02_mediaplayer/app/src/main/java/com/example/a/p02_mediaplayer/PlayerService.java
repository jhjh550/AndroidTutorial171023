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

    TestDBHandler dbHandler;
    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHandler = new TestDBHandler(this);
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

    public int getCurrentPostion(){
        if(mp == null)
            return 0;

        int current = mp.getCurrentPosition();
        dbHandler.update("Kalimba.mp3", current);
        return current;
    }
    public void seekTo(int position){
        if(mp != null){
            mp.seekTo(position);
        }
    }

    public int play(){
        String path = Environment.getExternalStorageDirectory()+
                "/Download/Kalimba.mp3";
        mp = new MediaPlayer();
        int duration = 0;
        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            int current = dbHandler.getCurrentPostion("Kalimba.mp3");
            mp.seekTo(current);
            duration = mp.getDuration();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duration;
    }

    public void stop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
