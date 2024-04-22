package com.example.aboagyemaxwell.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private onItemClickListener mListener;


    Context context;
    ArrayList<message> messages;

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public MyAdapter(Context c, ArrayList<message> p){
        context = c;
        messages = p;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.username.setText(messages.get(position).getUsername());
        holder.name.setText(messages.get(position).getFull_name());
        holder.ref.setText(messages.get(position).getRef_number());
        holder.gen.setText(messages.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,username,ref,gen;
        LinearLayout details;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            ref = itemView.findViewById(R.id.ref);
            gen = itemView.findViewById(R.id.gen);
            details = itemView.findViewById(R.id.checkDetails);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        }

    }

