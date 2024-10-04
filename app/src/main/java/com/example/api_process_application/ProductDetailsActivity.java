package com.example.api_process_application;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.api_process_application.databinding.ActivityMainBinding;
import com.example.api_process_application.databinding.ActivityProductDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    private ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        binding= ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        int clickedPosition =getIntent().getIntExtra("id",0);

        if(clickedPosition !=0){
            RetroFit.getInstance().getProductDetails(clickedPosition).enqueue(new Callback<ProductData>() {
                @Override
                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                    ProductData data = response.body();
                    binding.idTV.setText(String.valueOf(data.getid()));
                    binding.titleTV.setText(data.getTitle());
                    binding.descriptionTV.setText(data.getDescription());
                    binding.priceTV.setText(String.valueOf(data.getPrice()));

                    Glide
                            .with(ProductDetailsActivity.this)
                            .load(data.getImage())
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.pictureIV);

                }

                @Override
                public void onFailure(Call<ProductData> call, Throwable t) {

                }
            });
        }




    }
}