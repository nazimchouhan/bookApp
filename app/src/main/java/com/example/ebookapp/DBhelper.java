package com.example.ebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private static final String name="login.db";
    private static final int version=1;
    private static final String TABLE_NAME="mydata";
    private static final String ID_COL="id";
    private static final String Username="username";
    public static final String Password="password";


    public DBhelper( Context context) {
        super(context, name, null ,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Username+ " TEXT,"
                + Password + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists TABLE_NAME");

    }
    public Boolean insertdata(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("password",password);
        long result=db.insert(TABLE_NAME,null,values);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from mydata where username=?",new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select*from mydata where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<contactmodel> fetchlogindata(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select*from mydata",null);
        ArrayList<contactmodel> logindata=new ArrayList<>();
        while(cursor.moveToNext()){
            contactmodel model=new contactmodel();
            model.id=cursor.getInt(0);
            model.username=cursor.getString(1);
            model.password= cursor.getString(2);
            logindata.add(model);
        }
        return logindata;

    }
}
