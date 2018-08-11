package com.example.aasis.scalesandfins.TabFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aasis.scalesandfins.R;

import retrofit2.http.PUT;


public class TankDecorations extends Fragment {

    private static TankDecorations INSTANCE = null;


    public TankDecorations() {
        // Required empty public constructor
    }
    public static TankDecorations getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new TankDecorations();
        return INSTANCE;

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tank_decorations, container, false);
    }

}
