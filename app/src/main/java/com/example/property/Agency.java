package com.example.property;

public class Agency {
    int id_agency;
    String name;
    String desc;
    int year;
    String phone;
    String site;

    public int getId_agency() {
        return id_agency;
    }

    public void setId_agency(int id_agency) {
        this.id_agency = id_agency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Agency(int id_agency, String name, String desc, int year, String phone, String site) {
        this.id_agency = id_agency;
        this.name = name;
        this.desc = desc;
        this.year = year;
        this.phone = phone;
        this.site = site;
    }
}
