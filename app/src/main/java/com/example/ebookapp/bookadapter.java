package com.example.ebookapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bookadapter extends RecyclerView.Adapter<bookadapter.Viewholder> {
    private final Context context;
    ArrayList<String> booklist;

    public bookadapter(Context context,ArrayList<String> booklist) {
        this.context = context;
        this.booklist=booklist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.bookitem,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if(booklist!=null && position>=0 && position< booklist.size()) {
            String book = booklist.get(position);
            holder.booktitle.setText(book);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int4=new Intent(context, pdfActivity.class);
                    String assetName=booklist.get(holder.getAdapterPosition());
                    Log.w("Tag","assetname is :" + assetName);
                    int4.putExtra("assetname",assetName);
                    int4.putExtra("assetFolder", "pdfs");
                    context.startActivity(int4);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }
    public void setBooklist(ArrayList<String> booklist) {
        this.booklist = booklist;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView booktitle;
        CardView cardview;
        public Viewholder(@NonNull View itemview){
            super(itemview);
            booktitle=itemview.findViewById(R.id.booktitle);
            cardview=itemview.findViewById(R.id.cardview);
        }
    }
}
