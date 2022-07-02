package com.example.deptfreedom;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class setdataAdapter extends FirebaseRecyclerAdapter<getdatamodel,setdataAdapter.viewholder> {



    public setdataAdapter(@NonNull FirebaseRecyclerOptions<getdatamodel> options) {
        super(options);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dept_cards,parent,false);
        return new viewholder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull getdatamodel model) {
        holder.name.setText(model.getDeptname());
        holder.sbalance.setText(model.getSbalance());
        holder.remdata.setText(model.getPaydate());
        holder.itemView.setBackgroundColor(model.isIsselect() ? Color.CYAN:Color.TRANSPARENT);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setIsselect(!model.isIsselect());
                holder.itemView.setBackgroundColor(model.isIsselect() ? Color.RED : Color.TRANSPARENT);
            }
        });

    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,sbalance,remdata;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.setdeptname);
            sbalance = itemView.findViewById(R.id.setsbalance);
            remdata = itemView.findViewById(R.id.setremdate);
        }
    }
}
