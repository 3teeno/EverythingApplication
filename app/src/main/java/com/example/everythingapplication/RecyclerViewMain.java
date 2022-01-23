package com.example.everythingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.everythingapplication.RestAPI.MyRetrofit;
import com.example.everythingapplication.RestAPI.MyRetrofit_Interface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewMain extends AppCompatActivity {

    MyRetrofit_Interface myRetrofit_interface;
    MyRetrofit myRetrofit = new MyRetrofit();
    RecyclerView recyclerView;
    List<ProductsModel> Data=new ArrayList<>();
    ProductRecyclerAdapter productRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        productRecyclerAdapter=new ProductRecyclerAdapter(this);
        getProductList();
        productRecyclerAdapter.setDataset(Data);
        recyclerView = (RecyclerView) findViewById(R.id.ProductsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(productRecyclerAdapter);

//        getProductList();
    }
    private void getProductList()
    {
        myRetrofit_interface = myRetrofit.getRetrofit().create(MyRetrofit_Interface.class);
        Call<List<ProductsModel>> list = myRetrofit_interface.getProductList();
        list.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                Log.d("tfgfrdsrh", "onCreate: ");
                Data=response.body();
                if(response.body().size()>0)
                {
                    Log.d("123456", "andr hon: ");
                    List<ProductsModel> list = response.body();
                    productRecyclerAdapter.setDataset(Data);
                    productRecyclerAdapter.notifyDataSetChanged();

                    Log.d("123456", "andr hon: ");
                    Toast.makeText(getApplicationContext(), "Data received Succesfully.", Toast.LENGTH_SHORT).show();
                    for(ProductsModel productsModel : list)
                    {
                        Log.d("Response", "Title: "
                                +productsModel.getTitle()
                                +"Image : " +productsModel.getImage()
                                +"Price : " +productsModel.getPrice()
                                +"Description : " +productsModel.getDescription()
                                +"URL : " +productsModel.getImage()
                        );
                    }
                }

            }
            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

//        productsAdapter=new ProductsAdapter(lst,getApplicationContext());
//        recyclerView=new RecyclerView(getApplicationContext());
//        recyclerView.findViewById(R.id.ProductsRecycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(productsAdapter);