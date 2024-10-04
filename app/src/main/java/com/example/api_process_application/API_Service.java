package com.example.api_process_application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API_Service {
    @GET("products")
    Call<List<ProductData>>getProduct();


    @GET("products/{id}")
    Call<ProductData> getProductDetails(@Path("id")int id);


    @GET("products/categories")
    Call<List<String>>getProductCategories();


    @GET("products/category/{categories}")
    Call<List<ProductData>>getProductCategory(@Path("categories")String category);









}
