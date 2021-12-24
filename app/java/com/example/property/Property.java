package com.example.property;

import java.util.ArrayList;

public class Property {
    private int image;

    public Property(int image) {
        this.image = image;
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

}
