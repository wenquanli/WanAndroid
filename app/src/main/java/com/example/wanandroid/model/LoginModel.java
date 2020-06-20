package com.example.wanandroid.model;

import com.example.wanandroid.base.BaseObeserver;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.contract.LoginContract;
import com.example.wanandroid.http.RetrofitFactory;

public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void handleLogin(String username, String password, OnLoadDatasListener<UserBean.DataBean> onLoadDatasListener) {
        RetrofitFactory.getInstence().login(username, password, new BaseObeserver<UserBean.DataBean>() {
            @Override
            protected void onSuccess(UserBean.DataBean o) throws Exception {
                onLoadDatasListener.onSuccess(o);
            }

            @Override
            protected void onFailure(String error, boolean isNetworkError) throws Exception {
                onLoadDatasListener.onFailure(error);
            }
        });
    }
}
