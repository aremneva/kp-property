package com.example.property;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Favorite {
    int id_property;

    public int getId_property() {
        return id_property;
    }

    public void setId_property(int id_property) {
        this.id_property = id_property;
    }

    public Favorite(int id_property) {
        this.id_property = id_property;
    }
    public static ArrayList<Favorite> setFavData(int num, Context context) {
        DBHelper db = new DBHelper(context);
        ArrayList<Favorite> prop = new ArrayList<Favorite>();
        try {
            db.getFavProperty(prop);
        }
        catch (Exception e){
            Log.d("MAIN",e.getMessage());
        }
        return prop;
    }
}
