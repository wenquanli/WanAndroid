package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.WXArticleBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.http.RetrofitFactory;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import io.reactivex.Observable;

public class WXArticleModel implements OffAccountContract.IWXArticleModel {

    @Override
    public void loadWXArticle(int chapter, int page, OnLoadDatasListener<WXArticleBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadWXMainArticle(chapter, page, new BaseObeserver<WXArticleBean.DataBean>() {
            @Override
            protected void onSuccess(WXArticleBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }

    @Override
    public void refreshWXArticle(int chapter, OnLoadDatasListener<WXArticleBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().refreshWXMainArticle(chapter, new BaseObeserver<WXArticleBean.DataBean>() {
            @Override
            protected void onSuccess(WXArticleBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
