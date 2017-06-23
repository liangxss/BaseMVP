package com.liang.basemvp.presenter;

import java.util.HashMap;

/**
 * 商品 Presenter接口
 */
public interface GoodsListPresenter {
    /**
     * 获取商品的逻辑
     */
    void getGoodsList(String url, HashMap<String, String> params);

}
