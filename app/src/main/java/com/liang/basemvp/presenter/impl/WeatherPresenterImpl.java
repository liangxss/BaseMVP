package com.liang.basemvp.presenter.impl;

import com.liang.basemvp.view.WeatherView;
import com.liang.basemvp.model.WeatherModel;
import com.liang.basemvp.model.bean.WeatherInfo;
import com.liang.basemvp.model.impl.WeatherModelImpl;
import com.liang.basemvp.presenter.OnWeatherListener;
import com.liang.basemvp.presenter.WeatherPresenter;

/**
 * 天气 Prestener实现
 */
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener {
	/*Presenter作为中间层，持有View和Model的引用*/
	private WeatherView weatherView;
	private WeatherModel weatherModel;

	public WeatherPresenterImpl(WeatherView weatherView) {
		this.weatherView = weatherView;
		weatherModel = new WeatherModelImpl();
	}

	@Override
	public void getWeather(String cityNO) {
		weatherView.showLoading();
		weatherModel.loadWeather(cityNO, this);
	}

	@Override
	public void onSuccess(WeatherInfo weather) {
		weatherView.hideLoading();
		weatherView.setWeatherInfo(weather);
	}

	@Override
	public void onError() {
		weatherView.hideLoading();
		weatherView.showError();
	}
}
