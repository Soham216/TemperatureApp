package com.soham.temperatureapp.Ztemperature;

import android.content.Context;
import android.util.Log;

import com.soham.temperatureapp.API.Entities.WeatherData;
import com.soham.temperatureapp.API.TemperatureAPI;
import com.soham.temperatureapp.API.TemperatureService;
import com.soham.temperatureapp.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Model in MVP
public class MainModel implements MainContract.model {

    MainContract.Presenter presenter;
    TemperatureAPI api;

    public MainModel(MainContract.Presenter mPresenter){
        presenter = mPresenter;
        api = TemperatureService.getInstance().getOpenWeatherMapApi();
    }

    @Override
    public void loadWeather(String city, String appId, final Context context) {
        Log.i("ModelLoadWeather","Reached");
        Call<WeatherData> call = api.getCityWeather(city, appId);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Log.i("Response Code", "" +  response.code());
                if (response.code() == 200) {
                    WeatherData weatherData = response.body();
                    presenter.loadWeatherData(weatherData);
                }else{
                    Utils.showToast(context, "Response Error");
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Utils.showToast(context, "Error fetching data");
            }
        });
    }
}
