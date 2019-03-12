package com.soham.temperatureapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TemperatureService {

    private static TemperatureService instance = null;
    TemperatureAPI apiService;

    public TemperatureService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(TemperatureAPI.class);
    }

    public static TemperatureService getInstance() {
        if (instance == null) {
            instance = new TemperatureService();
        }
        return instance;
    }

    public TemperatureAPI getOpenWeatherMapApi() {
        return apiService;
    }

}
