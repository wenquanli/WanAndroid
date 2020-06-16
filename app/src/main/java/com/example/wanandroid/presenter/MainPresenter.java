package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.contract.Contract;
import com.example.wanandroid.model.MainModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<Contract.IMainView> implements Contract.IMainPresenter {
    Contract.IMainModel mModel;

    public MainPresenter() {
        mModel = new MainModel();
    }

    @Override
    public void loadBanner() {

        mModel.loadBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BannerBean bean) {
                        Log.d("MainPresenter", "loading successed");
                        if (isViewAttached()) {
                            getView().loadBanner(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainPresenter", "loading failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadArticle(int page) {
        mModel.loadArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MainArticleBean bean) {
                        if (isViewAttached()) {
                            getView().loadArticle(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }
//如果没有这个处理步骤的话一直会卡在下拉加载进度条上
                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            getView().onComplete();
                        }
                    }
                });
    }

    @Override
    public void refreshArticle() {
        mModel.refresh()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MainArticleBean bean) {
                        if (isViewAttached()) {
                            getView().refreshArticle(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            getView().onComplete();
                        }
                    }
                });
    }
}

