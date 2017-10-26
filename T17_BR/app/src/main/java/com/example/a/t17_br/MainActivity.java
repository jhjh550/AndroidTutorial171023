package com.example.a.t17_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                Toast.makeText(context, "batt : " + level, Toast.LENGTH_SHORT).show();
            }else if(action.equals(Intent.ACTION_BATTERY_LOW)){
                Toast.makeText(context, "battery low", Toast.LENGTH_SHORT).show();
            }else if (action.equals("abcdefg")){
                Toast.makeText(context, "custom br", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction("abcdefg");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(receiver);
    }

    public void onBtnClick(View v){
        Intent intent = new Intent("abcdefg");
        sendBroadcast(intent);
    }
}
