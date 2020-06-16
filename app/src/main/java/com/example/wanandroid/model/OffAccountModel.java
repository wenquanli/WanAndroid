package com.example.wanandroid.model;

import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.retrofit.IRetrofitData;
import com.example.wanandroid.util.RetrofitDataFactory;

import io.reactivex.Observable;

public class OffAccountModel implements OffAccountContract.IOffAccountModel {
    @Override
    public Observable<OffAccountBean> loadOffAccount() {
        IRetrofitData retrofitData = RetrofitDataFactory.getIRetrofitData();
        return retrofitData.loadOffAccount();
    }
}
