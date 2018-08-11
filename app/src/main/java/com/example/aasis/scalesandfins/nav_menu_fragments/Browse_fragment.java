package com.example.aasis.scalesandfins.nav_menu_fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.aasis.scalesandfins.Adapter.TabsfragmentAdapter;
import com.example.aasis.scalesandfins.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Browse_fragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;


    public Browse_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.browse_layout,container,false);

        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        TabsfragmentAdapter adapter = new TabsfragmentAdapter(getChildFragmentManager(),getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
