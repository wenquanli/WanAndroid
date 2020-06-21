package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.WXArticleBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.model.WXArticleModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WXArticlePresenter extends BasePresenter<OffAccountContract.IWXArticleView> implements OffAccountContract.IWXArticlePresenter {
    OffAccountContract.IWXArticleModel mModel;

    public WXArticlePresenter() {
        this.mModel = new WXArticleModel();
    }

    @Override
    public void loadWXArticle(int chapter, int page) {
        mModel.loadWXArticle(chapter, page, new OnLoadDatasListener<WXArticleBean.DataBean>() {
            @Override
            public void onSuccess(WXArticleBean.DataBean dataBean) {
                getView().onComplete();
                getView().loadWXArticle(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().onError(error);
            }
        });
    }

    @Override
    public void refreshWXArticle(int chapter) {
        mModel.refreshWXArticle(chapter, new OnLoadDatasListener<WXArticleBean.DataBean>() {
            @Override
            public void onSuccess(WXArticleBean.DataBean dataBean) {
                getView().onComplete();
                getView().refreshWXArticle(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().onError(error);
            }
        });
    }
}
