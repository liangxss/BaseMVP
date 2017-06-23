package com.liang.basemvp.presenter.impl;

import com.liang.basemvp.model.GoodsListModel;
import com.liang.basemvp.model.bean.GoodsBean;
import com.liang.basemvp.model.impl.GoodsListModelImpl;
import com.liang.basemvp.presenter.GoodsListPresenter;
import com.liang.basemvp.presenter.OnGoodsListListener;
import com.liang.basemvp.view.GoodsListView;

import java.util.HashMap;

/**
 * @author liang
 * 商品 Prestener实现
 */
public class GoodsListPresenterImpl implements GoodsListPresenter, OnGoodsListListener {
	/*Presenter作为中间层，持有View和Model的引用*/
	private GoodsListView goodsListView;
	private GoodsListModel goodsListModel;

	public GoodsListPresenterImpl(GoodsListView goodsListView) {
		this.goodsListView = goodsListView;
		goodsListModel = new GoodsListModelImpl();
	}

	@Override
	public void getGoodsList(String url, HashMap<String, String> params) {
		goodsListView.showLoading();
		goodsListModel.loadGoodsList(url, params, this);
	}

	@Override
	public void onSuccess(GoodsBean bean) {
		goodsListView.hideLoading();
		goodsListView.setGoodsList(bean);
	}

	@Override
	public void onError() {
		goodsListView.hideLoading();
		goodsListView.showError();
	}
}
