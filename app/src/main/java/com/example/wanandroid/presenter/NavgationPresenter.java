package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.NavgationBean;
import com.example.wanandroid.contract.NavgationContract;
import com.example.wanandroid.model.NavgationModel;

import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin.nanchen@bytedance.com
 * Date: 2020/7/10 8:31 PM
 */
public class NavgationPresenter extends BasePresenter<NavgationContract.INavgationView> implements NavgationContract.INavgationPresenter {
    NavgationModel mModel;

    public NavgationPresenter() {
        mModel = new NavgationModel();
    }


    @Override
    public void getDetailNavgation() {
        mModel.getDetailNavgation(new OnLoadDatasListener<List<NavgationBean.DataBean>>() {
            @Override
            public void onSuccess(List<NavgationBean.DataBean> beanList) {
                getView().updateDetailNavgation(beanList);
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }
}
