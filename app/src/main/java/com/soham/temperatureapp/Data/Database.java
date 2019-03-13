package com.soham.temperatureapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.soham.temperatureapp.API.Entities.RetrieveWeatherData;
import com.soham.temperatureapp.API.Entities.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private Context context;

    private static final String TAG = Database.class.getSimpleName();
    //private static final String TABLE_NAME = Database.class.getName();

    public Database(Context dContext) {
        super(dContext, DatabaseTable.DB_NAME, null, DatabaseTable.DB_VERSION);
        context = dContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(DatabaseTable.CREATE_TABLE_QUERY);
        } catch (SQLException ex) {
            Log.i(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseTable.DROP_TABLE_QUERY);
        this.onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
    }

    //database operations
    public void insertData(String temp, String humidity, String pressure, String country, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseTable.TEMP, temp);
        values.put(DatabaseTable.HUMIDITY, humidity);
        values.put(DatabaseTable.PRESSURE, pressure);
        values.put(DatabaseTable.COUNTRY, country);
        values.put(DatabaseTable.NAME, name);

        try {
            Log.i("Database","Trying to insert data");
            db.insert(DatabaseTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
        db.close();
    }

    public void updateData(String x, String temp, String humidity, String pressure, String country, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseTable.TEMP, temp);
        values.put(DatabaseTable.HUMIDITY, humidity);
        values.put(DatabaseTable.PRESSURE, pressure);
        values.put(DatabaseTable.COUNTRY, country);
        values.put(DatabaseTable.NAME, name);

        //update data
        db.update(DatabaseTable.TABLE_NAME, values, DatabaseTable.KEY_ID + " = ?", new String[]{String.valueOf(x)});
        db.close();
    }

    public void deleteData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseTable.TABLE_NAME, null, null);
        sqLiteDatabase.close();
    }

    //retrieve data from database
    public List<RetrieveWeatherData> getAllData() {
        List<RetrieveWeatherData> weatherList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DatabaseTable.GET_DATA_QUERY, null);

        if (cursor.moveToFirst()) {
            do {
                RetrieveWeatherData weather = new RetrieveWeatherData();
                weather.setTemp(cursor.getString(cursor.getColumnIndex(DatabaseTable.TEMP)));
                weather.setHumidity(cursor.getString(cursor.getColumnIndex(DatabaseTable.HUMIDITY)));
                weather. setCountry(cursor.getString(cursor.getColumnIndex(DatabaseTable.COUNTRY)));
                weather.setName(cursor.getString(cursor.getColumnIndex(DatabaseTable.NAME)));
                weatherList.add(weather);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return weatherList;
    }

    //check whether table is empty
    public boolean isTableEmpty() {
        Log.i("Database" , "Checking if table is empty");
        SQLiteDatabase db = getWritableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + DatabaseTable.TABLE_NAME, null);
        Boolean tableEmpty;

        if (mCursor.moveToFirst()) {
            mCursor.close();
            tableEmpty = false;
            db.close();
        } else {
            mCursor.close();
            tableEmpty = true;
            db.close();
        }

        db.close();
        return tableEmpty;
    }
}
