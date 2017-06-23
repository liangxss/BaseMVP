package com.liang.basemvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.liang.basemvp.BaseActivity;
import com.liang.basemvp.R;
import com.liang.basemvp.adapter.GoodsListAdapter;
import com.liang.basemvp.model.bean.GoodsBean;
import com.liang.basemvp.presenter.GoodsListPresenter;
import com.liang.basemvp.presenter.impl.GoodsListPresenterImpl;
import com.liang.basemvp.recycler.wrapper.LoadMoreWrapper;
import com.liang.basemvp.ui.PtrDefaultFrameLayout;
import com.liang.basemvp.view.GoodsListView;

import java.util.HashMap;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by liang on 2017/6/23.
 */
public class PrtListActivity extends BaseActivity implements GoodsListView {
    private PtrDefaultFrameLayout ptrFrameLayout;
    private RecyclerView recyclerView;
    private GoodsListPresenter presenter;
    private int page = 0;
    private View loadMoreLayout;
    private GoodsListAdapter adapter;
    private String url = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do";


    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_ptr_list;
    }

    @Override
    public void initView(View view) {
        ptrFrameLayout = find(R.id.ptrFrameLayout);
        recyclerView = find(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new GoodsListAdapter(this);
        recyclerView.setAdapter(adapter);
        ptrFrameLayout.buildPtr(new PtrHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                boolean flag = PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int index = manager.findFirstVisibleItemPosition();
                View childAt = recyclerView.getChildAt(0);
                boolean indexTop = false;
                if (childAt == null || (index == 0 && childAt.getTop() == 0)) {
                    indexTop = true;
                }

                return indexTop && flag;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refresh();
            }
        });
        loadMoreLayout = LayoutInflater.from(this).inflate(R.layout.load_layout, recyclerView, false);
        adapter.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {

            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });

    }

    private void loadMore() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("page", page+"");
        presenter.getGoodsList(url, params);
    }

    private void refresh() {
        page = 0;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("page", page+"");
        presenter.getGoodsList(url, params);
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitleString("标题");
        presenter = new GoodsListPresenterImpl(this);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("page", page+"");
        presenter.getGoodsList(url, params);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void setGoodsList(GoodsBean bean) {
        ptrFrameLayout.refreshComplete();
//        if(result == null || result.obj ==null){
//            return;
//        }
//        if(result.obj.page==0){
//            adapter.setLoadMoreView(null);
//        }else{
//            adapter.setLoadMoreView(loadMoreLayout);
//
//        }
//        page++;
//        if(page == 0) {
//            adapter.setData(bean);
//        } else {
//            adapter.addAll(bean);
//        }
    }
}
