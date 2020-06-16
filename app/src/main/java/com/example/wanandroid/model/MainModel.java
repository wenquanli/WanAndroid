package com.example.wanandroid.model;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import io.reactivex.Observable;

public class MainModel implements MainContract.IMainModel {


    @Override
    public Observable<BannerBean> loadBanner() {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.loadBanner();
    }

    @Override
    public Observable<MainArticleBean> loadArticle(int num) {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.loadArticle(num);
    }

    @Override
    public Observable<MainArticleBean> refresh() {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.refreshMainArticle();
    }
}
