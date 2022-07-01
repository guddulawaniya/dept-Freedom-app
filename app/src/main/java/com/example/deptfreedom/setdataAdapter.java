package com.example.deptfreedom;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class setdataAdapter extends RecyclerView.Adapter<setdataAdapter.viewholder> {

    ArrayList<getdatamodel> list;
    Context context;

    public setdataAdapter(ArrayList<getdatamodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dept_cards,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        getdatamodel user = list.get(position);

        holder.name.setText(user.getDeptname());
        holder.sbalance.setText(user.getSbalance());
        holder.remdata.setText(user.getPaydate());

    }

    @Override
    public int getItemCount() {
        return list.size();
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
