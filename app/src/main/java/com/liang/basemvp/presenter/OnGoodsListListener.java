package com.liang.basemvp.presenter;


import com.liang.basemvp.model.bean.GoodsBean;

/**
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */
public interface OnGoodsListListener {
    /**
     * 成功时回调
     *
     * @param bean
     */
    void onSuccess(GoodsBean bean);
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();

}
