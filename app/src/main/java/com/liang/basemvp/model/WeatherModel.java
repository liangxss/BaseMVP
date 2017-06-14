package com.liang.basemvp.model;

import com.liang.basemvp.presenter.OnWeatherListener;

/**
 * Created by liang on 2017/6/14.
 */
public interface WeatherModel {
    void loadWeather(String cityNO, OnWeatherListener listener);
}
