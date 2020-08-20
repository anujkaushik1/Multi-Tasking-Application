package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class customadapter extends RecyclerView.Adapter<customadapter.customholder> {
    private List<Article> articles;
    private Context context;


    public customadapter(List<Article> articles, Context context) {
        this.context=context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public customholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.newsrecyclerlist,parent,false);
        customholder ch = new customholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull customholder holder, int position) {
    final Article anuj = articles.get(position);
    holder.tv.setText(anuj.getTitle());
        Glide.with(context).load(anuj.getUrlToImage()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context,newswebview.class);
            intent.putExtra("url",anuj.getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   //stackoverflow code
            context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class customholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public customholder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.newsiv);
            tv= (TextView) itemView.findViewById(R.id.newstv);
        }
    }
}
