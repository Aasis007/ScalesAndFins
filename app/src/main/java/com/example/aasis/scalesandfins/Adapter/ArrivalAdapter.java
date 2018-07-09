package com.example.aasis.scalesandfins.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aasis.scalesandfins.Model.Newarrival;
import com.example.aasis.scalesandfins.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArrivalAdapter extends RecyclerView.Adapter<NewArrivalholder> {

    Context context;
    List<Newarrival> newarrivals;

    public ArrivalAdapter(Context context, List<Newarrival> newarrivals) {
        this.context = context;
        this.newarrivals = newarrivals;
    }

    @NonNull
    @Override
    public NewArrivalholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.menu_new_arrival,null);
        return new NewArrivalholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalholder holder, int position) {
        //load image
        Picasso.with(context)
                .load(newarrivals.get(position).Link)
                .into(holder.arrivalimg);

        holder.arrivaltxt.setText(newarrivals.get(position).Name);

    }

    @Override
    public int getItemCount() {
        return newarrivals.size();
    }
}
