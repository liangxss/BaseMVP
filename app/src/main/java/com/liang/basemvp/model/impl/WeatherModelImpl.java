package com.liang.basemvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.liang.basemvp.http.OkHttpClientManager;
import com.liang.basemvp.model.WeatherModel;
import com.liang.basemvp.model.bean.WeatherInfo;
import com.liang.basemvp.presenter.OnWeatherListener;

import okhttp3.Request;

/**
 * Created by liang on 2017/6/14.
 */
public class WeatherModelImpl implements WeatherModel {

    @Override
    public void loadWeather(String cityNO, final OnWeatherListener listener) {
        /*数据层操作*/
        OkHttpClientManager.getAsyn("http://www.weather.com.cn/data/sk/" + cityNO + ".html", new OkHttpClientManager.StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onError();
            }

            @Override
            public void onResponse(String response) {
                try {
                    WeatherInfo weather = JSON.parseObject(response, WeatherInfo.class);
                    if (weather != null) {
                        listener.onSuccess(weather);
                    } else {
                        listener.onError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
