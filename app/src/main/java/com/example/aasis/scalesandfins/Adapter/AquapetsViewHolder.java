package com.example.aasis.scalesandfins.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aasis.scalesandfins.R;



public class AquapetsViewHolder extends RecyclerView.ViewHolder {

    ImageView categoreyimage;
    TextView categorytitle;

    public AquapetsViewHolder(View itemView) {
        super(itemView);

        categoreyimage = (ImageView)itemView.findViewById(R.id.categoreyimg);
        categorytitle = (TextView)itemView.findViewById(R.id.categoreyname);
    }
}
