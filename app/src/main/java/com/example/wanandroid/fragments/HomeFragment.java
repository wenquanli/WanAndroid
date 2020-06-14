package com.example.wanandroid.fragments;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.wanandroid.adapter.MainArticleAdapter;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.constant.Constant;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<Contract.IMainView, MainPresenter> implements Contract.IMainView{
    @BindView(R.id.main_banner)
    Banner mMainBanner;
    @BindView(R.id.article_content)
    RecyclerView recyclerView;
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
        presenter.loadArticle();
        Log.d("HomeFragment","start loading");
    }


    @Override
    public void loadBanner(BannerBean bean) {


        mMainBanner.setImageLoader(new GlideImageLoader());

        if (bean.getErrorCode() == Constant.BANNER_SUCCESS) {

            //设置banner样式
            mMainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

            ArrayList<String> images = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            List<BannerBean.DataBean> data = bean.getData();
            for (int i = 0; i < data.size(); i++) {
                images.add(data.get(i).getImagePath());
                titles.add(data.get(i).getTitle());
            }

            mMainBanner.setImages(images);
            mMainBanner.setBannerTitles(titles);
            mMainBanner.start();
        }

    }

    @Override
    public void loadArticle(MainArticleBean mainArticleBean) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if(mainArticleBean.getErrorCode() == 0){
            MainArticleAdapter adapter = new MainArticleAdapter(recyclerView);
            adapter.setmBean(mainArticleBean);
            recyclerView.setAdapter(adapter);
        }
    }
}
