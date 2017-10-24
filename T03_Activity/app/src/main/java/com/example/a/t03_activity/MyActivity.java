package com.example.a.t03_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pw = intent.getStringExtra("pw");
        int level = intent.getIntExtra("level",0);
    }

    public void onFinishClick(View v){
        Intent intent = new Intent();
        intent.putExtra("myResult", "abcdefg");
        intent.putExtra("myValue", 1234);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "my activity detroy", Toast.LENGTH_SHORT).show();
    }
}
