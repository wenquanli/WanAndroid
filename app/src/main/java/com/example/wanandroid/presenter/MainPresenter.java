package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.model.MainModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {
    MainContract.IMainModel mModel;

    public MainPresenter() {
        mModel = new MainModel();
    }

    @Override
    public void loadBanner() {
        mModel.loadBanner(new OnLoadDatasListener<List<BannerBean.DataBean>>() {
            @Override
            public void onSuccess(List<BannerBean.DataBean> dataBeans) {
                if (isViewAttached()) {
                    getView().loadBanner(dataBeans);
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void loadArticle(int page) {
        mModel.loadArticle(page, new OnLoadDatasListener<MainArticleBean.DataBean>() {
            @Override
            public void onSuccess(MainArticleBean.DataBean dataBean) {
                getView().onComplete();
                getView().loadArticle(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void refreshArticle() {
        mModel.refresh(new OnLoadDatasListener<MainArticleBean.DataBean>() {
            @Override
            public void onSuccess(MainArticleBean.DataBean dataBean) {
                getView().onComplete();
                getView().refreshArticle(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().onError(error);
            }
        });
    }

}

