package com.example.property;

import android.icu.util.Freezable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentFav extends Fragment {

    ArrayList<Property> properties;
    ArrayList<Favorite> favorites;
    RecyclerView rec;
    public FragmentFav() {

    }

    public static FragmentFav newInstance() {
        FragmentFav fragment = new FragmentFav();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<Property> mProperties = new ArrayList<Property>();

        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        properties=Property.setInitialData(1, getContext());
        favorites = Favorite.setFavData(1,getContext());
        rec = (RecyclerView) view.findViewById(R.id.rec_fav);
        rec.setLayoutManager(new LinearLayoutManager(view.getContext()));
        try {

            for (int i = 0; i < favorites.size(); i++) {
                if (properties.get(i).getId_property() == favorites.get(i).getId_property()) {
                    mProperties.add(properties.get(i));
                }
            }
            PropertyAdapter adapter = new PropertyAdapter(mProperties);
            rec.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d("MAIN",e.getMessage());
        }
        return view;

    }
}