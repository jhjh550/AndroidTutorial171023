package com.example.a.p02_mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PlayerService extends Service {
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

    }

    public void stop(){

    }
}
