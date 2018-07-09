package com.example.aasis.scalesandfins.nav_menu_fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.aasis.scalesandfins.Adapter.ArrivalAdapter;
import com.example.aasis.scalesandfins.Model.Banner;
import com.example.aasis.scalesandfins.Model.Newarrival;
import com.example.aasis.scalesandfins.R;
import com.example.aasis.scalesandfins.Retrofit.ScalesAPI;
import com.example.aasis.scalesandfins.Utls.Common;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragment extends Fragment {
    SliderLayout sliderLayout;
    ScalesAPI mservice;
    RecyclerView arrivalnew;




    //rxjava
    CompositeDisposable compositeDisposable = new CompositeDisposable();




    public Home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);




        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        mservice = Common.getAPI();

        arrivalnew = (RecyclerView)view.findViewById(R.id.arrival_recycler);
        arrivalnew.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        arrivalnew.setHasFixedSize(true);

        //get banner images
        getBannerImage();

        getnewarrival();
        return view;
    }

    private void getnewarrival() {
        compositeDisposable.add(mservice.getnewarrival()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Newarrival>>() {
                    @Override
                    public void accept(List<Newarrival> newarrivals) throws Exception {
                        displaynewarrival(newarrivals);
                    }
                }));

    }

    private void displaynewarrival(List<Newarrival> newarrivals) {
        ArrivalAdapter adapter = new ArrivalAdapter(getActivity(),newarrivals);
        arrivalnew.setAdapter(adapter);
    }

    private void getBannerImage() {
        compositeDisposable.add(mservice.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        displayImage(banners);
                    }
                }));

    }


    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void displayImage(List<Banner> banners) {
        HashMap<String,String> bannerMap = new HashMap<>();
        for (Banner item:banners)
            bannerMap.put(item.getName(),item.getLink());

        for (String name:bannerMap.keySet())
        {
            TextSliderView textSliderView =  new TextSliderView(getActivity());
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            sliderLayout.addSlider(textSliderView);
        }
    }

    }


