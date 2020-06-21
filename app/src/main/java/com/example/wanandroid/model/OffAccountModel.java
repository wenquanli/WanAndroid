package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.http.RetrofitFactory;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import java.util.List;

import io.reactivex.Observable;

public class OffAccountModel implements OffAccountContract.IOffAccountModel {
    @Override
    public void loadOffAccount(OnLoadDatasListener<List<OffAccountBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadOffAccount(new BaseObeserver<List<OffAccountBean.DataBean>>() {
            @Override
            protected void onSuccess(List<OffAccountBean.DataBean> o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
