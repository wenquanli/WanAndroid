package com.example.wanandroid.fragments;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.activity.LoginActivity;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BaseBean;
import com.example.wanandroid.contract.MeContract;
import com.example.wanandroid.presenter.MePresenter;
import com.example.wanandroid.util.ActivityUtil;
import com.example.wanandroid.util.SpUtils;

import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class MeFragment extends BaseFragment<MeContract.IMeView, MePresenter> implements MeContract.IMeView{
    @BindView(R.id.iv_login_out)
    ImageView ivLoginOut;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    FrameLayout flHead;
    ImageView ivHead;
    @BindView(R.id.tv_login)
    AppCompatTextView tvLogin;
    @BindView(R.id.ll_nickname)
    LinearLayout llNickname;
    @BindView(R.id.tv_username)
    AppCompatTextView tvUsername;

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
        if (SpUtils.GetConfigString("username").equals("")){
            llInfo.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
            llNickname.setVisibility(View.GONE);
            ivLoginOut.setVisibility(View.GONE);

        }else {
            llInfo.setVisibility(View.VISIBLE);
            llNickname.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
            tvUsername.setText(SpUtils.GetConfigString("username")+"");
            ivLoginOut.setVisibility(View.VISIBLE);
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
        ivLoginOut.setOnClickListener(new View.OnClickListener() {
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
}
