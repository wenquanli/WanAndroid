package com.example.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;


import com.example.wanandroid.MainActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.contract.LoginContract;
import com.example.wanandroid.presenter.LoginPresenter;
import com.example.wanandroid.util.ActivityUtil;
import com.example.wanandroid.util.SpUtils;

public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    AppCompatTextView tvRegister;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
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
//        LoginActivity.startMainActivity(this);
        ActivityUtil.startActivity(MainActivity.class, true);
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
    public static void startMainActivity(Context context){
        Intent intent=new Intent(context, MainActivity.class);
        context.startActivity(intent);


    }
}
