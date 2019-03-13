package com.soham.temperatureapp.Ztemperature;

import android.content.Context;
import android.util.Log;

import com.soham.temperatureapp.API.Entities.WeatherData;
import com.soham.temperatureapp.API.TemperatureAPI;
import com.soham.temperatureapp.API.TemperatureService;
import com.soham.temperatureapp.Data.Database;
import com.soham.temperatureapp.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Model Interactor in MVP
public class MainModel implements MainContract.model {

    private MainContract.Presenter presenter;
    private TemperatureAPI api;
    private Database database;

    public MainModel(MainContract.Presenter mPresenter){
        presenter = mPresenter;
        api = TemperatureService.getInstance().getOpenWeatherMapApi();
    }

    @Override
    public void loadWeather(String city, String appId, final Context context, Database mDatabase) {
        Log.i("Model","loading weather data from api");
        database = mDatabase;

        Call<WeatherData> call = api.getCityWeather(city, appId);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Log.i("Response Code: ", "" +  response.code());
                if (response.code() == 200) {
                    WeatherData weatherData = response.body();

                    //insert/update data into database.
                    for (int i=0; i < weatherData.getWeather().size(); i++){
                        Log.i("Model", "Saving data to database");
                        Log.i("NAME", String.valueOf(weatherData.getWeather().get(i).main));

                        //insert/update data into database
                        if (!database.isTableEmpty()) {
                            database.insertData( "" + weatherData.getMain().getTemp(),
                                    "" + weatherData.getMain().getHumidity(),
                                    "" + weatherData.getMain().getPressure(),
                                    weatherData.getSys().getCountry(),
                                    weatherData.getName());
                        } else {
                            //update database
                            database.updateData("1",
                                    "" + weatherData.getMain().getTemp(),
                                    "" + weatherData.getMain().getHumidity(),
                                    "" + weatherData.getMain().getPressure(),
                                    weatherData.getSys().getCountry(),
                                    weatherData.getName());
                        }
                    }

                    presenter.loadWeatherData(weatherData);

                } else {
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
