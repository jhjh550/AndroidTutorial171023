package com.example.a.t02_widget2;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.helloBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "hello world2", Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup myRadioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.myRadio1:
                        Toast.makeText(MainActivity.this, "radio1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.myRadio2:
                        Toast.makeText(MainActivity.this, "radio2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.myRadio3:
                        Toast.makeText(MainActivity.this, "radio3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
