package com.example.wanandroid.contract;

import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;

import java.util.List;

import io.reactivex.Observable;

public class MainContract {
    public interface IBaseView {
        /**
         * <p>加载错误回调</p>
         * @param error Throwable
         */
        void onError(String error);

        /**
         * <p>加载完成</p>
         */
        void onComplete();
    }

    public interface IMainModel{
        public void loadBanner(OnLoadDatasListener<List<BannerBean.DataBean>> onLoadDatasListener);
        public void loadArticle(int page, OnLoadDatasListener<MainArticleBean.DataBean> onLoadDatasListener);
        public void refresh(OnLoadDatasListener<MainArticleBean.DataBean> onLoadDatasListener);

    }

    public interface IMainView extends IBaseView{
        void loadBanner(List<BannerBean.DataBean> beanList);
        void loadArticle(MainArticleBean.DataBean mainArticleBean);
        void refreshArticle(MainArticleBean.DataBean mainArticleBean);
    }

    public interface IMainPresenter{
        void loadBanner();
        void loadArticle(int page);
        void refreshArticle();
    }
}