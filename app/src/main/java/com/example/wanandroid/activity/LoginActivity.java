package com.example.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;

import com.example.wanandroid.MainActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.contract.LoginContract;
import com.example.wanandroid.presenter.LoginPresenter;
import com.example.wanandroid.util.ActivityUtil;
import com.example.wanandroid.util.SpUtils;

public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.login_username)
    EditText etUsername;
    @BindView(R.id.login_pwd)
    EditText etPassword;
    @BindView(R.id.login_sub)
    TextView tvLogin;
    @BindView(R.id.login_goregister)
    TextView tvRegister;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.handleLogin();
            }
        });
        //todo 写注册的监听
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void loginSuccess() {
        SpUtils.SetConfigString("username",etUsername.getText().toString());
        Log.d("LoginActivity","login success");
        ActivityUtil.startActivity(MainActivity.class,true);
    }

    @Override
    public void loginFail() {

    }

    @Override
    public String getUser() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

}
