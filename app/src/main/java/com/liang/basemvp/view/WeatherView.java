package com.liang.basemvp.view;


import com.liang.basemvp.model.bean.WeatherInfo;

public interface WeatherView {
    void showLoading();

    void hideLoading();

    void showError();

    void setWeatherInfo(WeatherInfo weather);
}
