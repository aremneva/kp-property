package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class page_property extends AppCompatActivity {

    private ImageView img_prop;
    private TextView txt_price;
    private TextView txt_rooms;
    private TextView txt_area;
    private TextView txt_floor;
    private Button fav;
    Property property;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_property);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        img_prop = (ImageView) findViewById(R.id.img_prop);
        txt_price= (TextView) findViewById(R.id.price);
        txt_rooms= (TextView) findViewById(R.id.txt_rooms);
        txt_area=(TextView) findViewById(R.id.txt_area);
        txt_floor=(TextView) findViewById(R.id.txt_floor);
        fav = (Button) findViewById(R.id.heart);

        try{

            property= (Property) getIntent().getExtras().getSerializable("obj");

            txt_price.setText(property.getPrice()+"$");
            Picasso.with(this)
                    .load(property.getImage())
                    .into(img_prop);
            txt_floor.setText(property.getFloor()+"");
            txt_area.setText(property.getArea()+" m²");
            txt_rooms.setText(property.getRooms()+"");
        }catch (Exception e){
            Log.d("MAIN","ex: "+e);
        }
    }

    public void GoToOwner(View view) {
        Intent intent = new Intent(this,page_owner.class);
        startActivity(intent);

    }

    public void GoToMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    public void AddToFav(View view) {
        try {

            DBHelper db = new DBHelper(this);
            db.addFav(property.getId_property());
            Toast.makeText(this,"Добавлено в избранное",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Ошибка",Toast.LENGTH_SHORT).show();
        }

    }
}