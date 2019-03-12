package com.soham.temperatureapp.API.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    @Expose
    public float temp;
    @SerializedName("humidity")
    @Expose
    public float humidity;
    @SerializedName("pressure")
    @Expose
    public float pressure;
    @SerializedName("temp_min")
    @Expose
    public float temp_min;
    @SerializedName("temp_max")
    @Expose
    public float temp_max;
}
