package com.example.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class completedrecyclerlist extends RecyclerView.Adapter<completedrecyclerlist.customholder> {
    private ArrayList<String> anuj;
    private ArrayList<String> anuj1; //pending
    private ArrayList<String> anuj2;
    public completedrecyclerlist(ArrayList<String> anuj, ArrayList<String> anuj1,ArrayList<String> anuj2) {
        this.anuj=anuj;
        this.anuj1 = anuj1;
        this.anuj2=anuj2;
    }

    @NonNull
    @Override
    public customholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.completedrecyclerlist,parent,false);
        customholder ch = new customholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull final customholder holder, int position) {
        String data = anuj.get(position);
        String data1 = anuj1.get(position);
        String data2 = anuj2.get(position);
        holder.tv.setText(data);

        holder.tv1.setText(data1);
        holder.tv2.setText(data2);
        holder.tv.setVisibility(View.INVISIBLE);
        holder.tv3.setVisibility(View.INVISIBLE);
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.tv.setVisibility(View.GONE);
                        holder.tv3.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {

        return anuj.size();
    }

    public class customholder extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2,tv3;
        public customholder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.completedtextview);  //deadline
            tv1 = (TextView)itemView.findViewById(R.id.completedtextview1); //title
            tv2 = (TextView)itemView.findViewById(R.id.completedtextview2); //currentdate
            tv3 = (TextView)itemView.findViewById(R.id.deadline1);
        }
    }
}
