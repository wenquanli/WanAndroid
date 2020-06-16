package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
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
        mModel.loadWXArticle(chapter,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WXArticleBean bean) {
                        Log.d("WXArticlePresenter", "loading wxarticle success");
                        if (isViewAttached()) {
                            getView().loadWXArticle(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WXArticlePresenter", "loading wxarticle faild");
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

    @Override
    public void refreshWXArticle(int chapter) {
        mModel.refreshWXArticle(chapter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WXArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WXArticleBean bean) {
                        if (isViewAttached()) {
                            getView().refreshWXArticle(bean);
                        }
                        Log.d("WXArticlePresenter", "refresh wxarticle successed" + bean.getData().toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WXArticlePresenter", "refresh wxarticle failed");
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
