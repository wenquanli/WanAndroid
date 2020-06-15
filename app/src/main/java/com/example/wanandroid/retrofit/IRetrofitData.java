package com.example.wanandroid.retrofit;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetrofitData {
    @GET("banner/json")
    Observable<BannerBean> loadBanner();
    @GET("article/list/{number}/json")
    Observable<MainArticleBean> loadArticle(@Path("number") int number);
    @GET("article/list/0/json")
    Observable<MainArticleBean> refreshMainArticle();
}
