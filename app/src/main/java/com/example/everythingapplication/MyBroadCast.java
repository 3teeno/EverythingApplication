package com.example.everythingapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


public class MyBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Junaid", "onReceive: ");
        Toast.makeText(context.getApplicationContext(), "The product added to Cart", Toast.LENGTH_SHORT).show();
    }
}

