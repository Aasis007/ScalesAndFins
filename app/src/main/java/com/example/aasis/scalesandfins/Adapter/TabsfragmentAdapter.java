package com.example.aasis.scalesandfins.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.aasis.scalesandfins.TabFragments.AquapetsFragment;
import com.example.aasis.scalesandfins.TabFragments.TankAccsesoriesfragment;
import com.example.aasis.scalesandfins.TabFragments.TankDecorations;

public class TabsfragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    public TabsfragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        return AquapetsFragment.getInstance();
        else if (position == 1)
            return TankDecorations.getInstance();
        else if (position == 2)
            return TankAccsesoriesfragment.getInstance();
        else
            return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                    return "AquaPets";
            case 1:
                    return "Tank Decorations";
            case 2:
                    return "Tank Accsesories";

        }
        return "";
    }
}
