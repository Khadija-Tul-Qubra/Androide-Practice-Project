package com.example.api_process_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductDataAdapter  extends RecyclerView.Adapter<ProductDataAdapter.MyViewHolder> {
    private Context context;
    private List<ProductData> list ;


    //variable for callback interface
    private onProductClickListener listener;
    //


    public ProductDataAdapter(Context context, List<ProductData> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_items, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductData data = list.get(position);


        //glide is for hold the images first add the dependencies in gradle then you accesses the glide
        Glide.with(context)
                .load(data.getImage())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error_image)
                .into(holder.imageView);

        holder.id.setText(String.valueOf(data.getid()));
        holder.title.setText(data.getTitle());
        holder.price.setText(String.valueOf(data.getPrice()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //invoke the call back
                listener.onProductClick((int)data.getid());
                //



//                Intent startActivity =new Intent(context,ProductDetailsActivity.class);
//                startActivity.putExtra("id",position+1);
//                context.startActivity(startActivity);

            }

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //initialization for call back
    public void onProductClickListener(onProductClickListener listener){

        this.listener=listener;
    }
    //




    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private CardView cardView;

        private TextView title, price,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.productid);
            title = itemView.findViewById(R.id.producttitle);
            price= itemView.findViewById(R.id.productprice);
            imageView=itemView.findViewById(R.id.images);
            cardView=itemView.findViewById(R.id.cardview);


        }

    }

    // interface for callback
    public interface onProductClickListener{
        void onProductClick(int position);
    }
    //

    public void UpdateListByCategory(List<ProductData>list){
        this.list=list;
        notifyDataSetChanged();
    }
    }


