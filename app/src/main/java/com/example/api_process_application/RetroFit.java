package com.example.api_process_application;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFit {

   public static final String Base_URL="https://fakestoreapi.com/";
   public static API_Service apiService=null;

   public  static API_Service getInstance(){
       if(apiService == null){
            apiService=new Retrofit.Builder()
                   .baseUrl(Base_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
                   .create(API_Service.class);



       }
       return apiService;

   }
}
