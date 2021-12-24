package com.example.property;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class FragmentAdd extends Fragment {

    public FragmentAdd() {

    }

    public static FragmentAdd newInstance() {
        FragmentAdd fragment = new FragmentAdd();
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
       // ImageView imgProp = (ImageView) getView().findViewById(R.id.imageProp);
       // imgProp.setClickable(true);
       // imgProp.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Toast.makeText(v.getContext(),
       //                 "OK",
       //                 Toast.LENGTH_LONG).show();
       //     }
       // });
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
}