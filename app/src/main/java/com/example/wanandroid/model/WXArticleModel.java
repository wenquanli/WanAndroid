package com.example.wanandroid.model;

import com.example.wanandroid.bean.WXArticleBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import io.reactivex.Observable;

public class WXArticleModel implements OffAccountContract.IWXArticleModel {
    @Override
    public Observable<WXArticleBean> loadWXArticle(int chapter, int page) {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.loadWXArticle(chapter,page);
    }

    @Override
    public Observable<WXArticleBean> refreshWXArticle(int chapter) {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.refreshWXArticle(chapter);
    }
}
