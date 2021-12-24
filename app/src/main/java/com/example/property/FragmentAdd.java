package com.example.property;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class FragmentAdd extends Fragment {

    TextInputLayout eDesc;
    TextInputLayout eAddress;
    TextInputLayout eArea;
    TextInputLayout eRooms;
    TextInputLayout eFloor;
    TextInputLayout ePrice;

    Uri selectedImageUri;
    public static final int PICK_IMAGE = 1;


    ImageView imgProp;
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
    public void Post(View v){



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add, container, false);
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

        Button button = (Button)  view.findViewById(R.id.post);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tDesc = eDesc.getEditText().getText().toString();
                String tAddress = eAddress.getEditText().getText().toString();
                String tArea = eArea.getEditText().getText().toString();
                String tRooms = eRooms.getEditText().getText().toString();
                String tFloor = eFloor.getEditText().getText().toString();
                String tPrice = ePrice.getEditText().getText().toString();


               String tImage = selectedImageUri.toString(); //тут
                try {

                    DBHelper db = new DBHelper(v.getContext());
                    db.addProperty(tDesc, tAddress, tPrice, "later", tArea, tRooms, tFloor, 0, 0, 0);
                }
                catch (Exception e){
                    Log.d("MAIN",e.getMessage(),e);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eDesc= (TextInputLayout) getView().findViewById(R.id.textDesc);
        eAddress= (TextInputLayout) getView().findViewById(R.id.textIAdress);
        eArea= (TextInputLayout) getView().findViewById(R.id.textIArea);
        eRooms= (TextInputLayout) getView().findViewById(R.id.textRooms);
        eFloor= (TextInputLayout) getView().findViewById(R.id.textFloor);
        ePrice= (TextInputLayout) getView().findViewById(R.id.textIPrice);

        imgProp = (ImageView) getView().findViewById(R.id.imageProp);
        imgProp.setClickable(true);
        imgProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
                Log.d("MAIN", "click!");
            }
        });

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                       // Uri selectedImageUri = data.getData();
                        Log.d("MAIN", "data: " + data.getData());
                        selectedImageUri = data.getData();
                        Picasso.with(getContext())
                                .load(selectedImageUri)
                                .into(imgProp);
                        Log.d("MAIN","data: "+data.getData());
                    }
                }
            });
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }



}