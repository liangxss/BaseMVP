package com.liang.basemvp.view;


import com.liang.basemvp.model.bean.GoodsBean;

public interface GoodsListView {
    void showLoading();

    void hideLoading();

    void showError();

    void setGoodsList(GoodsBean bean);
}
