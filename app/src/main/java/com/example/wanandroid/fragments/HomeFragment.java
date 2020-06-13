package com.example.wanandroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Constant.Constant;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.contract.Contract;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<Contract.IMainView, MainPresenter> implements Contract.IMainView{
    @BindView(R.id.main_banner)
    Banner mMainBanner;
    public HomeFragment() {
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        presenter.loadBanner();
        Log.d("HomeFragment","start loading");
    }


    @Override
    public void loadBanner(BannerBean bean) {


        mMainBanner.setImageLoader(new GlideImageLoader());

        if (bean.getErrorCode() == Constant.BANNER_SUCCESS) {

            //设置banner样式
            mMainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

            //获取图片路径
            List<String> images = bean.getData()
                    .stream()
                    .map(BannerBean.DataBean::getImagePath)
                    .collect(Collectors.toList());

            mMainBanner.setImages(images);

            //获取title

            List<String> titles = bean.getData()
                    .stream()
                    .map(BannerBean.DataBean::getTitle)
                    .collect(Collectors.toList());

            mMainBanner.setBannerTitles(titles);


            mMainBanner.start();
        }

    }
}
