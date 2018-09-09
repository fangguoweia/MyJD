package com.bwei.MyJD.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.MyJD.R;
import com.bwei.MyJD.adapter.SDFSD;
import com.bwei.MyJD.adapter.ShouYeXreAdapter;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment {

private RecyclerView shop_rv;

private SDFSD shouYeXreAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
         shop_rv = view.findViewById(R.id.shop_RecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            list.add("Sfds"+i);
        }
        shop_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        shouYeXreAdapter = new SDFSD(R.layout.sdf, list);
        shop_rv.setAdapter(shouYeXreAdapter);

        View fenleiview = getLayoutInflater().inflate(R.layout.fenlei_layout, (ViewGroup) shop_rv.getParent(), false);
        shouYeXreAdapter.addHeaderView(fenleiview);

    }
}
