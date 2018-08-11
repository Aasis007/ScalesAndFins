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
public class AquapetsFragment extends Fragment {
    private static AquapetsFragment INSTANCE = null;


    public AquapetsFragment() {
        // Required empty public constructor
    }

    public static AquapetsFragment getInstance()
    {
                if (INSTANCE == null)
                INSTANCE = new AquapetsFragment();
                return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aquapets, container, false);
    }

}
