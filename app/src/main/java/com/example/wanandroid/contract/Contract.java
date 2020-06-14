package com.example.wanandroid.contract;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;

import io.reactivex.Observable;

public class Contract {

    public interface IMainModel{
        Observable<BannerBean> loadBanner();
        Observable<MainArticleBean> loadArticle(int num);

    }

    public interface IMainView{
        void loadBanner(BannerBean bannerBean);
        void loadArticle(MainArticleBean mainArticleBean);
    }

    public interface IMainPresenter{
        void loadBanner();
        void loadArticle();
    }
}