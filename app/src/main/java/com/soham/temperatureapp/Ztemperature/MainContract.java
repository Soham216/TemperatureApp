package com.soham.temperatureapp.Ztemperature;

import android.content.Context;
import android.provider.ContactsContract;

import com.soham.temperatureapp.API.Entities.WeatherData;
import com.soham.temperatureapp.Data.Database;

public interface MainContract {

    //view methods to be implemented
    interface View {
        void onSubmitClicked(String cityName);
        void displayWeather(WeatherData weatherData);
        Context getActivityContext();
        Database getDatabaseContext();
    }

    //presenter methods to be implemented
    interface Presenter {
        void getWeatherData(String city);
        void loadWeatherData(WeatherData weatherData);
    }

    interface model{
        void loadWeather(String city, String appId, Context context, Database database);
    }
}
