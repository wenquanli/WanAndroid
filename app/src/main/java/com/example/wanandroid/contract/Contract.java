package com.example.wanandroid.contract;

import com.example.wanandroid.bean.BannerBean;

import io.reactivex.Observable;

public class Contract {

    public interface IMainModel{
        Observable<BannerBean> loadBanner();

    }

    public interface IMainView{
        void loadBanner(BannerBean bannerBean);
    }

    public interface IMainPresenter{
        void loadBanner();
    }
}