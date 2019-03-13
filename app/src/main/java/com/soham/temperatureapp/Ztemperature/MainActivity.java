package com.soham.temperatureapp.Ztemperature;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.soham.temperatureapp.API.Entities.WeatherData;
import com.soham.temperatureapp.Data.Database;
import com.soham.temperatureapp.R;
import com.soham.temperatureapp.Util.Utils;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    EditText cityName;
    TextView cityTitle;
    TextView cityTemperature;
    TextView cityHumidity;
    TextView cityPressure;

    MainContract.Presenter presenter;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.enter_city);
        cityTitle= findViewById(R.id.city_title_main);
        cityTemperature = findViewById(R.id.city_temperature_main);
        cityHumidity = findViewById(R.id.city_humidity_main);
        cityPressure = findViewById(R.id.city_pressure_main);
        Button buttonSubmit = findViewById(R.id.show_temperature);

        MainPresenter mPresenter = new MainPresenter(this);
        MainModel mModel = new MainModel(mPresenter);
        mPresenter.setModel(mModel);
        presenter = mPresenter;

        database = new Database(getActivityContext());

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the entered city
                String city = cityName.getText().toString();
                if(city.isEmpty()){
                    Utils.showToast(getActivityContext(), "Enter City");
                } else {
                    onSubmitClicked(city);
                }
            }
        });


    }

    @Override
    public void onSubmitClicked(String city) {
        Log.i("GetWeather", "Reched");
        presenter.getWeatherData(city);
    }

    @Override
    public void displayWeather(WeatherData weatherData) {
        //set data to the textviews
        if(weatherData.main != null) {
            cityTitle.setText(weatherData.name + ", " + weatherData.sys.country);
            cityTemperature.setText(Utils.convertKelvinToCelcius(weatherData.main.temp) + " °C");
            cityHumidity.setText("Humidity: " + weatherData.main.humidity + " °C" );
            cityPressure.setText("Pressure: " + weatherData.main.pressure);
        }
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Database getDatabaseContext() {
        return database;
    }
}
