package com.soham.temperatureapp.Util;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static float convertKelvinToCelcius(float tempKelvin){
        float tempCelsius = (float) (tempKelvin - 273.15);
        return tempCelsius;
    }
}
