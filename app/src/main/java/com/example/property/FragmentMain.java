 package com.example.property;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FragmentMain extends Fragment {
ArrayList<Property> properties;
LinearLayoutManager llm;
RecyclerView rec;
    public FragmentMain() {

    }

    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        properties=Property.setInitialData(1, getContext());
        rec = (RecyclerView) view.findViewById(R.id.recMain);
        rec.setLayoutManager(new LinearLayoutManager(view.getContext()));

        PropertyAdapter adapter = new PropertyAdapter(properties);
        rec.setAdapter(adapter);

        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }


}