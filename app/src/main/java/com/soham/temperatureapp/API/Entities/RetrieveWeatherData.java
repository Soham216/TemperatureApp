package com.soham.temperatureapp.API.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RetrieveWeatherData implements Serializable {

    @SerializedName("keyid")
    @Expose
    private int keyid;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;

    private boolean isFromDatabaseNCO;

    public int getKeyid() {
        return keyid;
    }

    public void setKeyid(int keyid) {
        this.keyid = keyid;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFromDatabaseQuestions() {
        return isFromDatabaseNCO;
    }
    public void setFromDatabaseQuestions(boolean fromDatabase) {
        isFromDatabaseNCO = fromDatabase;
    }
}
