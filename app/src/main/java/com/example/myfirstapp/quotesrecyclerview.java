package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class quotesrecyclerview extends RecyclerView.Adapter<quotesrecyclerview.customholder> {
    private Context context;
    private List<QuotesResponse> quotes;

    public quotesrecyclerview(Context context, List<QuotesResponse> quotes) {
        this.context = context;
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public customholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View v= layoutInflater.inflate(R.layout.quotesrecyclerlist,parent,false);
       customholder ch = new customholder(v);


        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull customholder holder, int position) {
        QuotesResponse allquotes= quotes.get(position);
        holder.tv.setText(allquotes.getText());
        holder.tvauthor.setText(allquotes.getAuthor());

    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class customholder extends RecyclerView.ViewHolder{
        TextView tv;
        TextView tvauthor;


        public customholder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.quotestv);
            tvauthor =(TextView)itemView.findViewById(R.id.tvauthor);
        }
    }
}
