package com.example.property;

import android.content.ContentValues;
import android.provider.SyncStateContract;

import java.util.ArrayList;

public class Property {
    private int image;
    private String price;
    public Property(int image) {
        this.image = image;
    }

    public Property(int image, String price) {
        this.image = image;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    private static int lastContactId = 0;

    public static ArrayList<Property> setInitialData(int num) {
        ArrayList<Property> prop = new ArrayList<Property>();
        prop.add(new Property(R.drawable.kv1));
        prop.add(new Property(R.drawable.kv2));
        prop.add(new Property(R.drawable.kv3));
        prop.add(new Property(R.drawable.kv4));
        prop.add(new Property(R.drawable.kv5));

        return prop;
    }

    public  static ArrayList<Property> setDataFinal(int num){
        ArrayList<Property> prop = new ArrayList<Property>();

        return prop;

    }





}
