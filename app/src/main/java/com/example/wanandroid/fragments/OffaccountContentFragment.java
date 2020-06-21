package com.example.wanandroid.fragments;

import android.os.Bundle;
import android.util.Log;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.WXArticleAdapter;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.WXArticleBean;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.presenter.WXArticlePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class OffaccountContentFragment extends BaseFragment<OffAccountContract.IWXArticleView, WXArticlePresenter> implements OffAccountContract.IWXArticleView{
    @BindView(R.id.wxarticle_content)
    RecyclerView recyclerView;
    @BindView(R.id.wxarticle_refresh_layout)
    SmartRefreshLayout refreshLayout;
    WXArticleAdapter adapter;
    List<WXArticleBean.DataBean.DatasBean> articleList = new ArrayList<>();
    int curPage = 0;
    private int chapterId;

    @Override
    protected WXArticlePresenter createPresenter() {
        return new WXArticlePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_offaccount_content;
    }

    @Override
    protected void init() {
        initArticleRecycle();
        initListener();
        initData();
    }

    private void initData() {
        Log.d("OffContentFrag","started init data, chapterId is" + chapterId);
        mPresenter.refreshWXArticle(chapterId);
    }

    private void initArticleRecycle() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new WXArticleAdapter(recyclerView, articleList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                curPage++;
                mPresenter.loadWXArticle(chapterId,curPage);
            }
        });
        refreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                curPage = 0;
                mPresenter.refreshWXArticle(chapterId);
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        chapterId = bundle.getInt("id");
    }


    @Override
    public void onError(String e) {
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
    public void loadWXArticle(WXArticleBean.DataBean wXArticleBeanList) {
        articleList.addAll(wXArticleBeanList.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshWXArticle(WXArticleBean.DataBean wXArticleBean) {
        articleList.clear();
        articleList.addAll(wXArticleBean.getDatas());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onPause() {
        super.onPause();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }
}
