package com.example.wanandroid.presenter;

import android.text.TextUtils;
import android.view.View;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.OnLoadDatasListener;
import com.example.wanandroid.bean.UserBean;
import com.example.wanandroid.contract.LoginContract;
import com.example.wanandroid.model.LoginModel;
import com.example.wanandroid.util.CommonUtil;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    LoginContract.ILoginModel mModel;

    public LoginPresenter() {
        this.mModel = new LoginModel();
    }

    @Override
    public void handleLogin() {
        if (getView() == null) return;
        if (TextUtils.isEmpty(getView().getUser()) || TextUtils.isEmpty(getView().getPassword())) {
            CommonUtil.showToast("用户名或密码不能为空");
            return;
        }
        mModel.handleLogin(getView().getUser(), getView().getPassword(), new OnLoadDatasListener<UserBean.DataBean>() {
            @Override
            public void onSuccess(UserBean.DataBean dataBean) {
                getView().loginSuccess();
            }

            @Override
            public void onFailure(String error) {
                getView().loginFail();
            }
        });
    }
}
