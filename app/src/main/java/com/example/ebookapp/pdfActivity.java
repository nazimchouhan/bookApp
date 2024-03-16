package com.example.ebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;

public class pdfActivity extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Intent int4=getIntent();
        String assetName=int4.getStringExtra("assetname");
        String assetFolder = int4.getStringExtra("assetFolder");
        Log.w("Tag","assetname is :" + assetName);

        pdfView=findViewById(R.id.pdfView);
        if (assetName != null) {
            try {

                InputStream inputStream = getAssets().open(assetFolder+ "/" + assetName);
                pdfView.fromStream(inputStream).load();
            } catch (IOException e) {
                e.printStackTrace();

                Log.e("PDFActivity", "Error loading PDF: " + e.getMessage());
                Toast.makeText(this, "Error loading PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle case where assetName is null
            Toast.makeText(this, "Asset name is null", Toast.LENGTH_SHORT).show();
        }
    }
}