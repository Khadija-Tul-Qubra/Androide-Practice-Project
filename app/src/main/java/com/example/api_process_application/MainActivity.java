package com.example.api_process_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.api_process_application.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProductCategoriesAdapter categoriesAdapter;
    private ProductDataAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();


    }

    private void init(){
        getAllProducts();
        getAllCategories();

    }

    private void fetchClickCategory() {
        if (categoriesAdapter != null) {
            categoriesAdapter.OnProductListener(new ProductCategoriesAdapter.OnProductListener() {
                @Override
                public void onProductClick(String categories) {
                    RetroFit.getInstance().getProductCategory(categories).enqueue(new Callback<List<ProductData>>() {
                        @Override
                        public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                            if (!response.body().isEmpty())  {
                                productAdapter.UpdateListByCategory(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ProductData>> call, Throwable t) {

                        }
                    });

                }


            });
        }
    }

    private void getAllCategories() {
        RetroFit.getInstance().getProductCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (!response.body().isEmpty()) {
                    categoriesAdapter = new ProductCategoriesAdapter(MainActivity.this, response.body());
                    binding.categoriesRV.setAdapter(categoriesAdapter);
                    fetchClickCategory();
                }

                Log.d("zahra", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("zahra", t.getLocalizedMessage());
            }
        });
    }

    private void getAllProducts() {
        RetroFit.getInstance().getProduct().enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if (!response.body().isEmpty()) {

                    binding.progressbar.setVisibility(View.INVISIBLE);
                    productAdapter = new ProductDataAdapter(MainActivity.this, response.body());
                    binding.recycler.setAdapter(productAdapter);

                    productAdapter.onProductClickListener(new ProductDataAdapter.onProductClickListener() {
                        @Override
                        public void onProductClick(int position) {
                            Intent startActivity = new Intent(MainActivity.this, ProductDetailsActivity.class);
                            startActivity.putExtra("id", position + 1);
                            startActivity(startActivity);
                        }
                    });
                }

                Log.d("zahra", response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                Log.d("zahra", t.getLocalizedMessage());
            }
        });
    }
}