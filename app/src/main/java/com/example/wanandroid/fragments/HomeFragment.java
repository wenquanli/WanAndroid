package com.example.wanandroid.fragments;

import android.util.Log;

import com.example.wanandroid.Constant.Constant;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.contract.Contract;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

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
//            List<String> images = bean.getData()
//                    .stream()
//                    .map(BannerBean.DataBean::getImagePath)
//                    .collect(Collectors.toList());

            ArrayList<String> images = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            List<BannerBean.DataBean> data = bean.getData();
            for (int i = 0; i < data.size(); i++) {
                images.add(data.get(i).getImagePath());
                titles.add(data.get(i).getTitle());
            }

            mMainBanner.setImages(images);

            //获取title
            // TODO lambda 表达式虽然很简单，但不应该以此去浪费 SDK 版本 24 以下的用户，
            //  而且这里实际上多执行了一次循环是没必要的，Java 和 Kotlin 这里应该都有正确的 lambda 去做过率
//            List<String> titles = bean.getData()
//                    .stream()
//                    .map(BannerBean.DataBean::getTitle)
//                    .collect(Collectors.toList());

            mMainBanner.setBannerTitles(titles);


            mMainBanner.start();
        }

    }
}
