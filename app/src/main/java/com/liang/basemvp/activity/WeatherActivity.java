package com.liang.basemvp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.liang.basemvp.BaseActivity;
import com.liang.basemvp.R;
import com.liang.basemvp.view.WeatherView;
import com.liang.basemvp.model.bean.WeatherInfo;
import com.liang.basemvp.presenter.WeatherPresenter;
import com.liang.basemvp.presenter.impl.WeatherPresenterImpl;


/**
 * 天气界面
 */
public class WeatherActivity extends BaseActivity implements WeatherView {
    private Dialog loadingDialog;
    private EditText cityNOInput;
    private TextView city;
    private TextView cityNO;
    private TextView temp;
    private TextView wd;
    private TextView ws;
    private TextView sd;
    private TextView wse;
    private TextView time;
    private TextView njd;
    private Button btn_go;

    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_weather;
    }

    @Override
    public void initView(View view) {
        cityNOInput = find(R.id.et_city_no);
        city = find(R.id.tv_city);
        cityNO = find(R.id.tv_city_no);
        temp = find(R.id.tv_temp);
        wd = find(R.id.tv_WD);
        ws = find(R.id.tv_WS);
        sd = find(R.id.tv_SD);
        wse = find(R.id.tv_WSE);
        time = find(R.id.tv_time);
        njd = find(R.id.tv_njd);
        btn_go = find(R.id.btn_go);
    }

    @Override
    public void doBusiness(Context mContext) {
        weatherPresenter = new WeatherPresenterImpl(this); //传入WeatherView
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("加载天气中...");
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                weatherPresenter.getWeather(cityNOInput.getText().toString().trim());
                break;
        }
    }

    @Override
    public void setOnClickListener() {
        btn_go.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public void showError() {
        //Do something
        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWeatherInfo(WeatherInfo info) {
        city.setText(info.getCity());
        cityNO.setText(info.getCityid());
        temp.setText(info.getTemp());
        wd.setText(info.getWD());
        ws.setText(info.getWS());
        sd.setText(info.getSD());
        wse.setText(info.getWS());
        time.setText(info.getTemp());
        njd.setText(info.getNjd());
    }

}
