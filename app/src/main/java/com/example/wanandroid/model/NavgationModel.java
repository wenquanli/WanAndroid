package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.bean.NavgationBean;
import com.example.wanandroid.contract.KonwledgeHierContract;
import com.example.wanandroid.contract.NavgationContract;
import com.example.wanandroid.http.RetrofitFactory;

import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin.nanchen@bytedance.com
 * Date: 2020/7/10 8:21 PM
 */
public class NavgationModel implements NavgationContract.INavgationModel {

    @Override
    public void getDetailNavgation(OnLoadDatasListener<List<NavgationBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadNavgation(new BaseObeserver<List<NavgationBean.DataBean>>() {
            @Override
            protected void onSuccess(List<NavgationBean.DataBean> o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
