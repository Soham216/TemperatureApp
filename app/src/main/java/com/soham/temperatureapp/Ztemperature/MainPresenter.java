package com.soham.temperatureapp.Ztemperature;
import android.content.Context;
import android.util.Log;

import com.soham.temperatureapp.API.Entities.WeatherData;
import com.soham.temperatureapp.Data.Database;

//Presenter in MVP
public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    MainContract.model model;

    private Context context;

    public MainPresenter(MainContract.View mView){
        setView(mView);
        context = mView.getActivityContext();
    }

    public void setModel(MainContract.model mModel){
        model = mModel;
    }

    public void setView(MainContract.View mView){
        view = mView;
    }

    @Override
    public void getWeatherData(String city) {
        Log.i("Presenter", "Getting weather data from model");
        model.loadWeather(city, "fa4459684db38dbec6269af0c66ca68f", context, view.getDatabaseContext());
    }

    @Override
    public void loadWeatherData(WeatherData weatherData){
        view.displayWeather(weatherData);
    }

}
