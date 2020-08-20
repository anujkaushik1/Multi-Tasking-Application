package com.example.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class diaryrecycler extends RecyclerView.Adapter<diaryrecycler.customholder> {
  private  ArrayList<String> anuj;
  private ArrayList<String> anuj1 ; //description
   private ArrayList<String> anuj2;   //currentdate


    public diaryrecycler(ArrayList<String> anuj, ArrayList<String> anuj1,ArrayList<String> anuj2) {
        this.anuj = anuj;
        this.anuj1 = anuj1;
        this.anuj2 = anuj2;

    }

    @NonNull
    @Override
    public customholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.diaryrecyclerlist,parent,false);
        customholder ch = new customholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull customholder holder, int position) {
        String data = anuj.get(position);
        String data1 = anuj1.get(position); //description
        String data2 = anuj2.get(position);


        holder.diaryrecyclertitle.setText(data);
        holder.diaryrecyclerdescription.setText(data1);
        holder.diaryrecyclerdate.setText(data2);

    }

    @Override
    public int getItemCount() {
        return anuj.size();
    }

    public class customholder extends RecyclerView.ViewHolder {
        TextView diaryrecyclerdate,diaryrecyclertitle,diaryrecyclerdescription;
        public customholder(@NonNull View itemView) {
            super(itemView);
            diaryrecyclerdate= (TextView)itemView.findViewById(R.id.diaryrecyclerdate);
            diaryrecyclertitle= (TextView)itemView.findViewById(R.id.diaryrecyclertitle);
            diaryrecyclerdescription= (TextView)itemView.findViewById(R.id.diaryrecyclerdescription);
        }
    }
}
