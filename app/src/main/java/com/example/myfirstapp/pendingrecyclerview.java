package com.example.myfirstapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;


public class pendingrecyclerview extends RecyclerView.Adapter<pendingrecyclerview.customholder> {

    private ArrayList<String> anuj;
    private ArrayList<String> anuj1;
    private ArrayList<String> anuj2;
    private Context context;
    private ArrayList<Integer> anuj3;

    DatePickerDialog.OnDateSetListener listener;		//initalise

    String date;
    int year,day,month;
    final Calendar cal = Calendar.getInstance();



    //UPDATE =
    ArrayList<String> updatetitle = new ArrayList<>();


    ArrayList<String> newupdatetitle = new ArrayList<>();
    String updateddate;


    public pendingrecyclerview(ArrayList<String> anuj, ArrayList<String> anuj1, ArrayList<String> anuj2, Context context, ArrayList<Integer> anuj3) {
        this.anuj = anuj;
        this.anuj1 = anuj1;
        this.anuj2 = anuj2;
        this.context = context;
        this.anuj3 = anuj3;  //databseid
    }


    @NonNull
    @Override
    public customholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.pendingrecyclerlist,parent,false);
        customholder ch = new customholder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull final customholder holder, final int position) {
        final String data = anuj.get(position);
        final String data1 = anuj1.get(position);  // deadline
        final String data2 = anuj2.get(position);  //currentdate
        final int databaseid = anuj3.get(position);  //databseid


        holder.tv.setText(data);



        holder.tv3.setText(data2);      //currentdate
        holder.tv.setText(data);
        holder.tv1.setText(data1);


        holder.cvo.setVisibility(View.GONE);
        holder.imageViewo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cvo.setVisibility(View.VISIBLE);
            }
        });
      /*  holder.linearLayouto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cvo.setVisibility(View.INVISIBLE);
            }
        });*/
        holder.deadlineo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getRootView().getContext(), android.R.style.Theme_DeviceDefault_Light, listener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                datePickerDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

                datePickerDialog.show();
            }
        });
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                System.out.println(year + " " + month + " " + day);
                date = year + "-" + month + "-" + day;
                System.out.println(date);
                holder.deadlineo.setText(date);
            }
        };
        holder.buttono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                updatetitle.add(holder.titleo.getText().toString());


                sqlitetask databaseclass = new sqlitetask(context);
                SQLiteDatabase db = databaseclass.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("stitle", updatetitle.toString());
                values.put("sdeadline", date);

                db.update("todo", values, "_id=" + anuj3.get(position), null);

                if (holder.titleo.length() != 0 && holder.deadlineo.length()!=0) {
                    sqlitetask databaseclass1 = new sqlitetask(context);
                    SQLiteDatabase db1 = databaseclass1.getReadableDatabase();
                    String columns[] = {"_id", "stitle", "sdeadline", "scurrentdate"};
                    Cursor c = db1.rawQuery("select * from todo", new String[]{});
                    while (c.moveToNext()) {
                        newupdatetitle.add(c.getString(1));
                        updateddate = c.getString(2);

                    }
                    String data4 = newupdatetitle.get(position);


                    holder.tv.setText(holder.titleo.getText().toString());
                    holder.tv1.setText(updateddate);
                    holder.cvo.setVisibility(View.GONE);
                    holder.titleo.clearComposingText();

                }
                else{
                    Toast toast = Toast.makeText(context,
                            "Please enter title and deadline to update",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }

            }
        });


    holder.linearLayouto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            holder.cvo.setVisibility(View.GONE);
        }
    });

    }



    @Override
    public int getItemCount() {
        return anuj.size();
    }

    public class customholder extends RecyclerView.ViewHolder {



        TextView tv,tv1,tv2,tv3;
        TextView titleo,deadlineo;  //update
        Button buttono;
        ImageView imageViewo;

        CardView cvo;
        LinearLayout linearLayouto;



        public customholder(@NonNull View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.pendingtextview);
            tv1=(TextView)itemView.findViewById(R.id.pendingtextview1);
            tv2=(TextView)itemView.findViewById(R.id.pendingtextview3);
            tv3=(TextView)itemView.findViewById(R.id.pendingtextview2);

            //UPDATE =

          titleo = (TextView)itemView.findViewById(R.id.titleo);
            deadlineo = (TextView)itemView.findViewById(R.id.deadlineo);
            buttono =(Button)itemView.findViewById(R.id.buttono);
            imageViewo =(ImageView)itemView.findViewById(R.id.imageviewo);

            cvo= (CardView)itemView.findViewById(R.id.cvo);
            linearLayouto= (LinearLayout)itemView.findViewById(R.id.linearlayouto);

            //calender=

}}}


