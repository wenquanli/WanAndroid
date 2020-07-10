package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.contract.KonwledgeHierContract;
import com.example.wanandroid.http.RetrofitFactory;

import java.util.List;

public class KonwledgeHireModel implements KonwledgeHierContract.IKonwledgeHireModel {
    @Override
    public void getDetailKnowledgeHierFromIn(OnLoadDatasListener<List<KonwledgeHireBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadKonwledgeHeir(new BaseObeserver<List<KonwledgeHireBean.DataBean>>() {
            @Override
            protected void onSuccess(List<KonwledgeHireBean.DataBean> o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
