package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.http.RetrofitFactory;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import java.util.List;

import io.reactivex.Observable;

public class MainModel implements MainContract.IMainModel {


    @Override
    public void loadBanner(OnLoadDatasListener<List<BannerBean.DataBean>> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadBanner(new BaseObeserver<List<BannerBean.DataBean>>() {
            @Override
            protected void onSuccess(List<BannerBean.DataBean> o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }

    @Override
    public void loadArticle(int page, OnLoadDatasListener<MainArticleBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().loadMainArticle(page, new BaseObeserver<MainArticleBean.DataBean>() {
            @Override
            protected void onSuccess(MainArticleBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }

    @Override
    public void refresh(OnLoadDatasListener<MainArticleBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().refreshMainArticle(new BaseObeserver<MainArticleBean.DataBean>() {
            @Override
            protected void onSuccess(MainArticleBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
