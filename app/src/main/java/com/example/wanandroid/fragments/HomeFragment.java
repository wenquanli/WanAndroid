package com.example.wanandroid.fragments;

import android.util.Log;

import com.example.wanandroid.adapter.MainArticleAdapter;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.constant.Constant;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.contract.Contract;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.util.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeFragment extends BaseFragment<Contract.IMainView, MainPresenter> implements Contract.IMainView{
    @BindView(R.id.main_banner)
    Banner mMainBanner;
    @BindView(R.id.article_content)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    MainArticleAdapter adapter;
    List<MainArticleBean.DataBean.DatasBean> articleList = new ArrayList<>();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MainArticleAdapter(recyclerView, articleList);
        recyclerView.setAdapter(adapter);
    }
    private void initListener() {
        refreshLayout.setOnLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                curPage++;
                presenter.loadArticle(curPage);
            }
        });
        refreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 0;
                presenter.refreshArticle();
            }
        });
    }
    private void initData() {
        presenter.loadBanner();
        presenter.refreshArticle();
    }


    @Override
    public void loadBanner(BannerBean bean) {
        if (bean.getErrorCode() == Constant.BANNER_SUCCESS) {
            List<BannerBean.DataBean> data = bean.getData();
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
            articleList.addAll(mainArticleBean.getData().getDatas());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshArticle(MainArticleBean mainArticleBean) {
        if(mainArticleBean.getErrorCode() == 0){
            articleList.clear();
            articleList.addAll(mainArticleBean.getData().getDatas());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Throwable e) {
        //加载
        if (refreshLayout.getState() == RefreshState.Loading) {

            refreshLayout.finishLoadMore();
            curPage--;
        }

        //刷新
        if (refreshLayout.getState() == RefreshState.Refreshing) {

            refreshLayout.finishRefresh();
        }

    }

    @Override
    public void onComplete() {
        //加载
        if (refreshLayout.getState() == RefreshState.Loading) {

            refreshLayout.finishLoadMore();
        }

        //刷新
        if (refreshLayout.getState() == RefreshState.Refreshing) {

            refreshLayout.finishRefresh();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

}
