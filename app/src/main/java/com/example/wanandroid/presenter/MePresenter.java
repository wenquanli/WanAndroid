package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.contract.MeContract;
import com.example.wanandroid.model.MeModel;

public class MePresenter extends BasePresenter<MeContract.IMeView> implements MeContract.IMePresenter{
    MeContract.IMeModel meModel;

    public MePresenter() {
        meModel = new MeModel();
    }

    @Override
    public void loginOut() {
        meModel.handleLoginOut(new OnLoadDatasListener<BaseBean.DataBean>() {
            @Override
            public void onSuccess(BaseBean.DataBean dataBean) {
                getView().loginOutSuccess(dataBean);
            }

            @Override
            public void onFailure(String error) {
                getView().LoginOutFail();
            }
        });
    }
}
