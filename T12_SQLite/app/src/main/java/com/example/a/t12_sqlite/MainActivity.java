package com.example.a.t12_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        TestSQLiteHandler dbHandler = new TestSQLiteHandler(this);
        dbHandler.insert("kim", 10, "서울");
        dbHandler.insert("홍", 11, "부산");
        dbHandler.update("kim", 15);

        String res = dbHandler.selectAll();
        textView.setText(res);
    }
}
