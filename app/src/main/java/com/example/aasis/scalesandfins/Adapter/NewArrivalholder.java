package com.example.aasis.scalesandfins.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aasis.scalesandfins.R;

public class NewArrivalholder extends RecyclerView.ViewHolder {

    ImageView arrivalimg;
    TextView arrivaltxt;
    public NewArrivalholder(View itemView) {
        super(itemView);

        arrivalimg = (ImageView)itemView.findViewById(R.id.arrival_image);
        arrivaltxt = (TextView)itemView.findViewById(R.id.arrival_text);
    }
}
