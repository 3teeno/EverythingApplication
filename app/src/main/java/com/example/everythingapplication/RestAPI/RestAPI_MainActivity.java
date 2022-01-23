package com.example.everythingapplication.RestAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.everythingapplication.HomePage;
import com.example.everythingapplication.R;

public class RestAPI_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restapi_main);
    }
    public void gotohome(View view) {
        startActivity(new Intent(this,HomePage.class));
    }
}