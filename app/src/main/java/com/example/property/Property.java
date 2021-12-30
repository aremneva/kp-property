package com.example.property;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class Property implements Serializable {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_agency() {
        return id_agency;
    }

    public void setId_agency(int id_agency) {
        this.id_agency = id_agency;
    }

    public int getId_house() {
        return id_house;
    }

    public void setId_house(int id_house) {
        this.id_house = id_house;
    }

    public String getDesc_image() {
        return desc_image;
    }

    public void setDesc_image(String desc_image) {
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
        try {
            db.getAllFromProperty(prop);
        }
        catch (Exception e){
            Log.d(LOG_TAG,e.getMessage());
        }
        return prop;
    }

    public int getId_property() {
        return id_property;
    }

    public void setId_property(int id_property) {
        this.id_property = id_property;
    }

    public  static ArrayList<Property> setDataFinal(int num){
        ArrayList<Property> prop = new ArrayList<Property>();

        return prop;

    }
}
