package com.example.everythingapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    List<ProductsModel> Dataset;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view,parent,false);
        return new ViewHolder(view);
    }

    public ProductRecyclerAdapter() {
    }

    public ProductRecyclerAdapter( Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(Dataset.get(position).getTitle());
        Glide.with(context).load(Dataset.get(position).getImage()).into(holder.image);
    }
    public List<ProductsModel> getDataset() {
        return Dataset;
    }

    public void setDataset(List<ProductsModel> dataset) {
        Dataset = dataset;
    }

    @Override
    public int getItemCount() {
        return Dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView image;
        private final Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.product_card_text);
            image = itemView.findViewById(R.id.product_card_image);
            button= itemView.findViewById(R.id.CartButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Log.d("Click","han han click"+getAdapterPosition());
                    Log.d("Click howa","han han click howa");
                    Dbtools dbtools=new Dbtools(view.getContext());

                    HashMap<String,String> CARTentry = new HashMap<String,String>();
                    CARTentry.put("Title",Dataset.get(position).getTitle());
                    CARTentry.put("Image",Dataset.get(position).getImage());
                    CARTentry.put("Price",Dataset.get(position).getPrice());
                    CARTentry.put("Description",Dataset.get(position).getDescription());
                    CARTentry.put("URL","URL");
                    dbtools.addNewContact(CARTentry);
                    MyBroadCast myBroadCast=new MyBroadCast();
                    myBroadCast.onReceive(context,new Intent());

                }
            });
        }

        public TextView getTextView() {
            return textView;
        }

    }


}
