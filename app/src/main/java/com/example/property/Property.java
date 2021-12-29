package com.example.property;

import android.content.ContentValues;
import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Property {
    private int id_property;
    private String address;
    private double price;
    private String metro;
    private double area;
    private int rooms;
    private int floor;
    private int id_user;
    private int id_agency;
    private int id_house;

    private String image;
    private String desc_image;


    public static final String LOG_TAG="MAIN";
    public Property(int id_property,  String address, double price, String metro, double area, int rooms, int floor, int id_user, int id_agency, int id_house, String image, String desc_image) {
        this.id_property = id_property;
        this.address = address;
        this.price = price;
        this.metro = metro;
        this.area = area;
        this.rooms = rooms;
        this.floor = floor;
        this.id_user = id_user;
        this.id_agency = id_agency;
        this.id_house = id_house;
        this.image = image;
        this.desc_image = desc_image;
    }

    public Property(String image) {
        this.image = image;
    }

    public Property(String image, double price) {
        this.image = image;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private static int lastContactId = 0;

    public static ArrayList<Property> setInitialData(int num, Context context) {
        DBHelper db = new DBHelper(context);
        ArrayList<Property> prop = new ArrayList<Property>();
       // prop.add(new Property(R.drawable.kv1));
       // prop.add(new Property(R.drawable.kv2));
       // prop.add(new Property(R.drawable.kv3));
       // prop.add(new Property(R.drawable.kv4));
       // prop.add(new Property(R.drawable.kv5));
        try {
            db.getAllFromProperty(prop);
        }
        catch (Exception e){
            Log.d(LOG_TAG,e.getMessage());
        }
        return prop;
    }



    public  static ArrayList<Property> setDataFinal(int num){
        ArrayList<Property> prop = new ArrayList<Property>();

        return prop;

    }





}
