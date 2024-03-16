package com.example.ebookapp;

import static com.example.ebookapp.DBhelper.Password;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<String> booklist=new ArrayList<>();
    TextView booktitle;
    RecyclerView recyclerview;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AssetManager assetManager=getAssets();
        booktitle=findViewById(R.id.booktitle);
        recyclerview=findViewById(R.id.RVview);
        try {
            // List all files in the "pdfs" folder
            String[] files = assetManager.list("pdfs");

            if (files != null) {
                // Convert the array to an ArrayList
                booklist = new ArrayList<>(Arrays.asList(files));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Tag","booklist is : " + booklist);

        bookadapter adapter=new bookadapter(this,booklist);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        DBhelper dbhelper=new DBhelper(this);

        ArrayList<contactmodel> logindata=dbhelper.fetchlogindata();
        for(int i=0;i<logindata.size();i++){
            Log.d("CONTACT_INFO","Username: "+ logindata.get(i).username +",Password:"+logindata.get(i).password+"/");
        }

    }
}