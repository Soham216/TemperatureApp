package com.soham.temperatureapp.Data;

public class DatabaseTable {
    public static final String DB_NAME = "weatherdata";
    public static final String TABLE_NAME = "weathertable";
    public static final int DB_VERSION = +1;
    public static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String GET_DATA_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String KEY_ID = "keyid";
    public static final String MAIN = "main";
    public static final String DESCRIPTION = "description";
    public static final String TEMP = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";
    public static final String COUNTRY = "country";
    public static final String NAME = "name";

    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + TEMP + " TEXT,"
            + HUMIDITY + " TEXT,"
            + PRESSURE + " TEXT,"
            + COUNTRY + " TEXT,"
            + NAME + " TEXT" + ")";
}
