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
    public void loadArticle() {
        mModel.loadArticle(0)
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
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

