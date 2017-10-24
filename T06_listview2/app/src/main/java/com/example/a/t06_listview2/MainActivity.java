package com.example.a.t06_listview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        int itemIcon;
        String title;
        String desc;
    }
    ArrayList<MyData> myList = new ArrayList<>();

    private void initData(){
        for(int i=0; i<50; i++){
            MyData myData = new MyData();
            myData.title = "title"+i;
            myData.desc = "desc"+i;
            myData.itemIcon = R.mipmap.ic_launcher;
            myList.add(myData);
        }
    }

    class MyAdapter extends BaseAdapter{

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }
}
