package com.example.wanandroid.retrofit;

import com.example.wanandroid.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRetrofitData {
    @GET("banner/json")
    Observable<BannerBean> loadBanner();
}
