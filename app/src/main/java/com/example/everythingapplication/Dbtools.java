package com.example.everythingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Dbtools extends SQLiteOpenHelper {
    public Dbtools(Context context)
    {
        super(context,
                "User",
                null,
                1);
    }
    View view;

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SqlCreateTable="CREATE TABLE CART ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Title TEXT,"+
                "Image TEXT,"+
                "Price TEXT,"+
                "Description TEXT,"+
                "URL TEXT)";
        try{
            db.execSQL(SqlCreateTable);
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }
    void delete(String id)
    {
        SQLiteDatabase db =getWritableDatabase();
        db.delete("CART", "_id = " + id, null);
    }
    void update(HashMap<String,String> contact, String id)
    {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Title",contact.get("firstName"));
        contentValues.put("Image",contact.get("lastName"));
        contentValues.put("Price",contact.get("phoneNumber"));
        contentValues.put("Description",contact.get("emailAddress"));
        contentValues.put("URL",contact.get("homeAddress"));
        long i=db.update("CART",contentValues,"_id="+id,null);
        if(i!=-1)
        {
            Log.d("contact","Data Inserted Successfully");
            //Snackbar.make(view,"Data Inserted",Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Log.d("contact","Data Not Inserted Successfully");

            //Snackbar.make(view,"Failed",Snackbar.LENGTH_LONG).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addNewContact(HashMap<String,String> contact)
    {
        Log.d("Idr hon ma", contact.toString());

        SQLiteDatabase db =getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("_id",contact.get("_id"));
        contentValues.put("Title",contact.get("Title"));
        contentValues.put("Image",contact.get("Image"));
        contentValues.put("Price",contact.get("Price"));
        contentValues.put("Description",contact.get("Description"));
        contentValues.put("URL",contact.get("URL"));
        Log.d("Idr hon ma", contact.toString());
        long i=db.insert("CART",null,contentValues);
        if(i!=-1)
        {
            Log.d("contact","Data Inserted Successfully");
            //Snackbar.make(view,"Data Inserted",Snackbar.LENGTH_LONG).show();
        }
        else
        {
            Log.d("contact","Data Not Inserted Successfully");

            //Snackbar.make(view,"Failed",Snackbar.LENGTH_LONG).show();
        }
    }
    public ArrayList<HashMap<String,String>> getAllContacts()
    {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<HashMap<String,String>> contactlist=new ArrayList<HashMap<String,String>>();
        String query="SELECT * FROM CART ORDER BY _id";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                HashMap<String,String> contact = new HashMap<String,String>();
                contact.put("_id",cursor.getString(0));
                contact.put("Title",cursor.getString(1));
                contact.put("Image",cursor.getString(2));
                contact.put("Price",cursor.getString(3));
                contact.put("Description",cursor.getString(4));
                contact.put("URL",cursor.getString(5));
                contactlist.add(contact);

            }while(cursor.moveToNext());
        }
        return contactlist;
    }
    public HashMap<String,String> getSingleContact(String id){
        SQLiteDatabase db=getReadableDatabase();
        HashMap<String,String> singleRecord=new HashMap<String,String>();
        String query="SELECT * FROM CART WHERE _id="+id;
        Cursor cursor=db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            singleRecord.put("_id",cursor.getString(0));
            singleRecord.put("Title",cursor.getString(1));
            singleRecord.put("Image",cursor.getString(2));
            singleRecord.put("Price",cursor.getString(3));
            singleRecord.put("Description",cursor.getString(4));
            singleRecord.put("URL",cursor.getString(5));
        }
        return  singleRecord;
    }
}

