package com.example.everythingapplication;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ListViewAdapter extends ArrayAdapter<String> {

    ArrayList<HashMap<String,String>> Data;
    Context context;
    public ListViewAdapter(Activity context,ArrayList<HashMap<String,String>> data) {
        super(context, R.layout.cartitem, Collections.singletonList("ListView"));
        this.Data=data;
        this.context=context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.cartitem, parent,false);
        Log.d("TAGGER",""+position);
        Log.d("TAGGER",Data.toString());
        TextView titleText =  rowView.findViewById(R.id.list_title);
        ImageView imageView =  rowView.findViewById(R.id.list_img);
        TextView subtitleText =  rowView.findViewById(R.id.list_description);
        TextView priceText= rowView.findViewById(R.id.list_title2);
        if(Data.size()==0)
        {
            titleText.setText("Cart is Empty");
        }
        else {
            titleText.setText(Data.get(position).get("Title"));
            Glide.with(context).load(Data.get(position).get("Image")).into(imageView);
            subtitleText.setText(Data.get(position).get("Description"));
            priceText.setText(Data.get(position).get("Price"));
        }
        return rowView;

    };

}