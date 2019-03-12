package com.soham.temperatureapp.API;

import com.soham.temperatureapp.API.Entities.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TemperatureAPI {
    @GET("weather")
    Call<WeatherData> getCityWeather(@Query("q") String city, @Query("APPID") String appId);
}
