package com.example.a.t20_json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String str = "[{'name':'kim', 'tel':'010-1111-2222', 'age':20}," +
            "{'name':'park', 'tel':'010-2222-3333', 'age':21}," +
            "{'name':'lee', 'tel':'010-3333-4444', 'age':22}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imgPath = "http://cdn.podbbang.com/data1/programmer/programmer-iamprogram20161211.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Glide.with(this).load(imgPath).into(imageView);




        try {
            JSONArray array = new JSONArray(str);
            for(int i=0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String tel = obj.getString("tel");
                int age = obj.getInt("age");

                Log.d("json", name+" "+tel+" "+age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onBtnClick(View v){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.androidhive.info/contacts/", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    response.getJSONArray("contacts");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
