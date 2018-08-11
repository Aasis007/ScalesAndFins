package com.example.aasis.scalesandfins.TabFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aasis.scalesandfins.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TankAccsesoriesfragment extends Fragment {

    private static TankAccsesoriesfragment INSTANCE = null;


    public TankAccsesoriesfragment() {
        // Required empty public constructor
    }

    public static TankAccsesoriesfragment getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new TankAccsesoriesfragment();
        return INSTANCE;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tank_accsesoriesfragment, container, false);
    }

}
