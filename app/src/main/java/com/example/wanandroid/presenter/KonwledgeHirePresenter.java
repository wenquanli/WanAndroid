package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.contract.KonwledgeHierContract;
import com.example.wanandroid.model.KonwledgeHireModel;

import java.util.List;


public class KonwledgeHirePresenter extends BasePresenter<KonwledgeHierContract.IKonwledgeHireView> implements KonwledgeHierContract.IKonwledgeHirePresenter {
    KonwledgeHierContract.IKonwledgeHireModel mModel;

    public KonwledgeHirePresenter() {
        mModel = new KonwledgeHireModel();
    }
    @Override
    public void getDetailKnowledgeHier() {
        mModel.getDetailKnowledgeHierFromIn(new OnLoadDatasListener<List<KonwledgeHireBean.DataBean>>() {
            @Override
            public void onSuccess(List<KonwledgeHireBean.DataBean> beanList) {
                getView().updateDetailKnowledgeHier(beanList);
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }
}
