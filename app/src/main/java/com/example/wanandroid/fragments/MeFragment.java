package com.example.wanandroid.fragments;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.activity.LoginActivity;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.bean.CoinBean;
import com.example.wanandroid.contract.MeContract;
import com.example.wanandroid.presenter.MePresenter;
import com.example.wanandroid.util.ActivityUtil;
import com.example.wanandroid.util.SpUtils;

import butterknife.BindView;

public class MeFragment extends BaseFragment<MeContract.IMeView, MePresenter> implements MeContract.IMeView{

    @BindView(R.id.me_name)
    TextView tvLogin;
    @BindView(R.id.btn_login_out)
    Button button_login_out;
    @BindView(R.id.me_info)
    TextView meInfo;
    @Override
    protected MePresenter createPresenter() {
        return new MePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onResume() {
        super.onResume();
        initInfo();
    }

    @Override
    protected void init() {
        initListener();

    }

    private void initInfo() {
        if(SpUtils.GetConfigString("username").equals("")) {
            tvLogin.setText("请先登录~");
        }else {
            tvLogin.setText(SpUtils.GetConfigString("username"));
            mPresenter.loadCoin();
        }
    }

    @Override
    protected void initListener() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(LoginActivity.class);
            }
        });
        button_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loginOut();
            }
        });
    }

    @Override
    public void loginOutSuccess(BaseBean.DataBean dataBean) {
        SpUtils.SetConfigString("username","");
        ActivityUtil.startActivity(LoginActivity.class,true);
    }

    @Override
    public void LoginOutFail() {

    }

    @Override
    public void setCoinInfo(CoinBean.DataBean bean) {
        String info = String.format("id ：%s   排名 ：%s",bean.getUserId(),bean.getRank());
        Log.d("MainActivity", "load coin success");
        meInfo.setText(info);
    }

    @Override
    public void loadCoinInfoFailure() {
        Log.d("MainActivity", "load coin info failed");
    }
}
