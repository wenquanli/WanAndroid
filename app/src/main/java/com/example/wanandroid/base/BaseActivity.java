package com.example.wanandroid.base;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    protected P mPresenter;

    protected Unbinder unbinder;

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     *
     * @param savedInstanceState bundle
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 创建Presenter
     *
     * @return p
     */
    protected abstract P createPresenter();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());

        unbinder = ButterKnife.bind(this);

        mPresenter = createPresenter();

        mPresenter.attachView((V) this);

        init(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPresenter.detachView();
    }
}
