package com.example.wanandroid.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V> {
    WeakReference<V> mViewRef;
    //防止内存泄漏
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef == null ? null : mViewRef.get();
    }

    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        mCompositeDisposable.clear();
    }
}


