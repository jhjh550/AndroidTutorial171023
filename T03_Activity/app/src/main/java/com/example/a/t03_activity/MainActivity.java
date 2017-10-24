package com.example.a.t03_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("id", "abcd");
        intent.putExtra("pw", "qwer");
        intent.putExtra("level", 10);
        //startActivity(intent);
        startActivityForResult(intent,MY_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MY_REQ){
            if(resultCode == RESULT_OK){
                String str = data.getStringExtra("myResult");
                Toast.makeText(this, "str : "+str, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
