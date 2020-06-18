package com.example.wanandroid.fragments;

import android.util.Log;

import com.example.wanandroid.adapter.MainArticleAdapter;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.constant.Constant;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.util.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<MainContract.IMainView, MainPresenter> implements MainContract.IMainView{
    @BindView(R.id.main_banner)
    Banner mMainBanner;
    @BindView(R.id.article_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    MainArticleAdapter adapter;
    List<MainArticleBean.DataBean.DatasBean> mArticleList = new ArrayList<>();
    List<String> mBannerUrls = new ArrayList<>();
    List<String> mTitles = new ArrayList<>();
    int curPage = 0;
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
        initBanner();
        initArticleRecycle();
        initListener();
        initData();
    }
    private void initBanner() {
        mMainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mMainBanner.setImageLoader(new GlideImageLoader());
        mMainBanner.setImages(new ArrayList<String>());
        mMainBanner.setBannerTitles(new ArrayList<>());
        mMainBanner.start();
    }
    private void initArticleRecycle() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MainArticleAdapter(mRecyclerView, mArticleList);
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void initListener() {
        mRefreshLayout.setOnLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                curPage++;
                mPresenter.loadArticle(curPage);
            }
        });
        mRefreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 0;
                mPresenter.refreshArticle();
            }
        });
    }



    private void initData() {
        mPresenter.loadBanner();
        mPresenter.refreshArticle();
    }

    /**
     * 加这两行代码的原因是目前的fragment管理是replace的，所以不清理的话就会越来越多，后面要改成hide模式的
     * banner这个有bug呀，会偶尔崩，取回数据之后再操作UI后不崩了，但是会有时候加载不出图片来，看来之后的优化
     * 得考fragment管理和图片的缓存了。
     */
    //Todo fragment的管理
    @Override
    public void loadBanner(BannerBean bean) {
        if (bean.getErrorCode() == Constant.BANNER_SUCCESS) {
            List<BannerBean.DataBean> data = bean.getData();
            mBannerUrls.clear();
            mTitles.clear();
            for (int i = 0; i < data.size(); i++) {
                mBannerUrls.add(data.get(i).getImagePath());
                mTitles.add(data.get(i).getTitle());
            }
            mMainBanner.setImages(mBannerUrls);
            mMainBanner.setBannerTitles(mTitles);
            mMainBanner.start();
        }else {
            Log.d("HomeFragment","load banner failed");
        }

    }

    @Override
    public void loadArticle(MainArticleBean mainArticleBean) {

        if(mainArticleBean.getErrorCode() == 0){
            mArticleList.addAll(mainArticleBean.getData().getDatas());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshArticle(MainArticleBean mainArticleBean) {
        if(mainArticleBean.getErrorCode() == 0){
            mArticleList.clear();
            mArticleList.addAll(mainArticleBean.getData().getDatas());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Throwable e) {
        //加载
        if (mRefreshLayout.getState() == RefreshState.Loading) {

            mRefreshLayout.finishLoadMore();
            curPage--;
        }

        //刷新
        if (mRefreshLayout.getState() == RefreshState.Refreshing) {

            mRefreshLayout.finishRefresh();
        }

    }

    @Override
    public void onComplete() {
        //加载
        if (mRefreshLayout.getState() == RefreshState.Loading) {

            mRefreshLayout.finishLoadMore();
        }

        //刷新
        if (mRefreshLayout.getState() == RefreshState.Refreshing) {

            mRefreshLayout.finishRefresh();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

}
