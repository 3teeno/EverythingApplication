package com.example.everythingapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;

public class Search_Products extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
    }
    public void AlertDialog(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Search_Products.this);
        builder.setMessage("Do You Want to Continue ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Thanks", Toast.LENGTH_SHORT).show();;
            }
        })
                .setNegativeButton("CANCEL",null);
        AlertDialog alert= builder.create();
        alert.show();
    }
    public void Shared_Preference(View view) {
        //Save Data
        EditText text1;
        text1 = findViewById(R.id.savetofile_text1);
        String readData=text1.getText().toString();
        try {
            FileOutputStream outputStream=openFileOutput("MyFile", Context.MODE_PRIVATE); /// create file open in private mode
            try {
                outputStream.write(readData.getBytes());
                // output stream is an hanlder for file.
                outputStream.close();
                text1.setText(" ");
                Snackbar.make(view ,"Data Saved", Snackbar.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

        }
    }
    public void Shared_Preference_Read(View view) {
        EditText text1;
        text1 = findViewById(R.id.savetofile_text1);
        String readFileData=" ";
        try {
            FileInputStream inputStream=openFileInput("MyFile");
            InputStreamReader streamReader=new InputStreamReader((inputStream));
            BufferedReader bufferedReader=new BufferedReader(streamReader);
            String currentData=bufferedReader.readLine();
            if(currentData==null)
            {
                Snackbar.make(view,"File is Empty", Snackbar.LENGTH_LONG).show();
            }
            else
            {
                while (currentData!=null)
                {
                    readFileData=readFileData+currentData;
                    currentData=bufferedReader.readLine();
                }
                bufferedReader.close();
                streamReader.close();
                inputStream.close();
            }
           text1.setText(readFileData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void BroadCastMessage(View view) {
        Intent intent = new Intent();
        intent.setAction("android.CUSTOM_INTENT");
        sendBroadcast(intent);
        Log.d("Junaid", "BroadCastMessage: ");


    }
}
