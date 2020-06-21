package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.contract.MeContract;
import com.example.wanandroid.http.RetrofitFactory;

public class MeModel implements MeContract.IMeModel {
    @Override
    public void handleLoginOut(OnLoadDatasListener<BaseBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().loginOut(new BaseObeserver<BaseBean.DataBean>() {
            @Override
            protected void onSuccess(BaseBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
