package com.example.wanandroid.contract;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;

import io.reactivex.Observable;

public class MainContract {
    public interface IBaseView {
        /**
         * <p>加载错误回调</p>
         * @param e Throwable
         */
        void onError(Throwable e);

        /**
         * <p>加载完成</p>
         */
        void onComplete();
    }

    public interface IMainModel{
        Observable<BannerBean> loadBanner();
        Observable<MainArticleBean> loadArticle(int num);
        Observable<MainArticleBean> refresh();

    }

    public interface IMainView extends IBaseView{
        void loadBanner(BannerBean bannerBean);
        void loadArticle(MainArticleBean mainArticleBean);
        void refreshArticle(MainArticleBean mainArticleBean);
    }

    public interface IMainPresenter{
        void loadBanner();
        void loadArticle(int num);
        void refreshArticle();
    }
}