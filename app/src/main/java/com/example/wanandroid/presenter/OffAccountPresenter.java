package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.model.OffAccountModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OffAccountPresenter extends BasePresenter<OffAccountContract.IOffAccountView> implements OffAccountContract.IOffAccountPresenter{
    OffAccountContract.IOffAccountModel mModel;

    public OffAccountPresenter() {
        this.mModel = new OffAccountModel();
    }

    @Override
    public void loadOffAccount() {
        mModel.loadOffAccount(new OnLoadDatasListener<List<OffAccountBean.DataBean>>() {
            @Override
            public void onSuccess(List<OffAccountBean.DataBean> dataBeans) {
                getView().loadOffAccount(dataBeans);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
