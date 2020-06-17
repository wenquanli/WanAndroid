package com.example.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {
    private Unbinder unbinder;
    protected Context mContext;

    protected P mPresenter;

    protected abstract P createPresenter();
    protected abstract int getLayoutId();
    protected abstract void init();
    protected abstract void initListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        //得到context,在后面的子类Fragment中都可以直接调用
        mContext = getActivity();
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        init();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
        unbinder.unbind();
        //销毁时，解除绑定
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }




}