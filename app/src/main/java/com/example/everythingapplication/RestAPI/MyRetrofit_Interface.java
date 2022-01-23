package com.example.everythingapplication.RestAPI;

import com.example.everythingapplication.ProductsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MyRetrofit_Interface {
    @GET("products")
    Call<List<ProductsModel>> getProductList();
}
