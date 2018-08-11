package com.example.aasis.scalesandfins.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aasis.scalesandfins.R;

public class AquapetsAdapter extends RecyclerView.Adapter<AquapetsViewHolder>{

    Context context;

    @NonNull
    @Override
    public AquapetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.menu_aquapets,null);
        return new AquapetsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AquapetsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
