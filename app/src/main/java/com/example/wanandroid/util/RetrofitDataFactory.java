package com.example.wanandroid.util;

import com.example.wanandroid.constant.Constant;
import com.example.wanandroid.retrofit.IRetrofitData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataFactory {
    public static IRetrofitData getIRetrofitData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(IRetrofitData.class);
    }
}
