package com.example.ebookapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText loguser,logpass;
    Button logbtn;
    DBhelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loguser=findViewById(R.id.loguser);
        logpass=findViewById(R.id.logpass);
        logbtn=findViewById(R.id.logbtn);
        db=new DBhelper(this);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=loguser.getText().toString();
                String pass=logpass.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(login.this,"ALl fields are Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=db.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(login.this,"Successfully login",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(login.this,"login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
