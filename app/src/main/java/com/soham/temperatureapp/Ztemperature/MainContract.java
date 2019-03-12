package com.soham.temperatureapp.Ztemperature;

import android.content.Context;
import com.soham.temperatureapp.API.Entities.WeatherData;

public interface MainContract {

    //view methods to be implemented
    interface View {
        void getWeather(String cityName);
        void displayWeather(WeatherData weatherData);
        Context getActivityContext();
    }

    //presenter methods to be implemented
    interface Presenter {
        void getWeatherData(String city);
        void loadWeatherData(WeatherData weatherData);
    }

    interface model{
        void loadWeather(String city, String appId, Context context);
    }
}
