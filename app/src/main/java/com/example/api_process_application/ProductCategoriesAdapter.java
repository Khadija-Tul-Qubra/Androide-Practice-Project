package com.example.api_process_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoriesAdapter extends RecyclerView.Adapter<ProductCategoriesAdapter.MyViewHolder> {
    private Context context;
    private List<String> list ;
    private OnProductListener listener;



    public ProductCategoriesAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productcategories_items, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       String data = list.get(position);

        holder.title.setText(data.toString());


        holder.cardView.setOnClickListener( View->{
            String categories=list.get(position);
            listener.onProductClick(categories);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void OnProductListener(OnProductListener lister){
        this.listener = lister;
    }






    class MyViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView cardView;

        private TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.categoriesTV);
            cardView=itemView.findViewById(R.id.categoriesCV);


        }

    }

    public interface OnProductListener{
        void onProductClick(String categories);
    }


    }


