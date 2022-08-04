package com.example.coolwealther.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coolwealther.model.City;
import com.example.coolwealther.model.County;
import com.example.coolwealther.model.Province;

import java.util.ArrayList;
import java.util.List;

public class CoolWealtherDB {
    //数据库名称
    public static final String DB_NAME = "cool_wealther";
    //数据库版本
    public static final int VERSION = 1;

    private static CoolWealtherDB cooleWealtherDB;

    private SQLiteDatabase db;

    private CoolWealtherDB(Context context) {
        CooleWealtherOpenHelper dbHelper = new CooleWealtherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWealtherDB getInstance(Context context) {
        if (cooleWealtherDB == null) {
            cooleWealtherDB = new CoolWealtherDB(context);
        }
        return cooleWealtherDB;
    }
//    将province实例存储到数据库
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            values.put("province_code", province.getId());
            db.insert("Province", null,values);
        }
    }
    @SuppressLint("Range")
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor
                        .getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor
                                .getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }
    public void saveCity(City city) {
        if (city != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name", city.getCityName());;
            contentValues.put("city_code", city.getCityCode());
            contentValues.put("id", city.getId());
            db.insert("City", null, contentValues);
        }
    }
    //根据省份获取城市列表
    @SuppressLint("Range")
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCity_id());
            db.insert("County", null, values);
        }
    }
    @SuppressLint("Range")
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountryCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCity_id(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;

    }
}
