package com.example.coolwealther.model;

public class County {
    private int id;
    private String countyName;
    private String countyCode;
    private int city_id;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countryName) {
        this.countyName = countryName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountryCode(String countryCode) {
        this.countyCode = countryCode;
    }
}
