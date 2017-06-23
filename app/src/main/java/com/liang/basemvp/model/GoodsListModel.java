package com.liang.basemvp.model;

import com.liang.basemvp.presenter.OnGoodsListListener;

import java.util.HashMap;

/**
 * Created by liang on 2017/6/14.
 */
public interface GoodsListModel {
    void loadGoodsList(String url, HashMap<String, String> params, OnGoodsListListener listener);
}
