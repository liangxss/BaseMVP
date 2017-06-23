package com.liang.basemvp.model.impl;

import com.alibaba.fastjson.JSON;
import com.liang.basemvp.http.OkHttpClientManager;
import com.liang.basemvp.model.GoodsListModel;
import com.liang.basemvp.model.WeatherModel;
import com.liang.basemvp.model.bean.GoodsBean;
import com.liang.basemvp.model.bean.WeatherInfo;
import com.liang.basemvp.presenter.OnGoodsListListener;
import com.liang.basemvp.presenter.OnWeatherListener;
import com.liang.basemvp.util.JsonValidator;
import com.liang.basemvp.util.LogUtil;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by liang on 2017/6/14.
 */
public class GoodsListModelImpl implements GoodsListModel {

    @Override
    public void loadGoodsList(String url, HashMap<String, String> params, final OnGoodsListListener listener) {
        OkHttpClientManager.postAsyn(url, params, new OkHttpClientManager.StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.i("liang", e.toString());
                listener.onError();
            }

            @Override
            public void onResponse(String response) {
                LogUtil.i("liang", response);
                JsonValidator validator = new JsonValidator();
                boolean isJson = validator.validate(response);
                if (!isJson) {
                    listener.onError();
                    return;
                }
                try {
                    GoodsBean bean = JSON.parseObject(response, GoodsBean.class);
                    if (bean != null) {
                        listener.onSuccess(bean);
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
