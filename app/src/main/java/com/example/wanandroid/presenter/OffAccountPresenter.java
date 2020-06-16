package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.model.OffAccountModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OffAccountPresenter extends BasePresenter<OffAccountContract.IOffAccountView> implements OffAccountContract.IOffAccountPresenter{
    OffAccountContract.IOffAccountModel model;

    public OffAccountPresenter() {
        this.model = new OffAccountModel();
    }

    @Override
    public void loadOffAccount() {
        model.loadOffAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OffAccountBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(OffAccountBean bean) {
                        Log.d("OffAccountPresenter", "loading offaccount successed");
                        if (isViewAttached()) {
                            getView().loadOffAccount(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("OffAccountPresenter", "loading offaccount failed");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
