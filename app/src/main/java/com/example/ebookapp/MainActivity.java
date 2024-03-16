package com.example.ebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,confpassword;
    Button register;
    DBhelper db;
    TextView aha;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confpassword=findViewById(R.id.confpass);
        register=findViewById(R.id.register);
        aha=findViewById(R.id.aha);
        db=new DBhelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String confpass=confpassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confpass)){
                    Toast.makeText(MainActivity.this,"ALl fields are Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(confpass)){
                        Boolean checkuser=db.checkusername(user);
                        if(checkuser==false){
                            Boolean insertdata=db.insertdata(user,pass);
                            if(insertdata==true){
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Already Registered",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Password and Confirm password should be same",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        aha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1=new Intent(MainActivity.this, login.class);
                startActivity(int1);
            }
        });
    }
}






