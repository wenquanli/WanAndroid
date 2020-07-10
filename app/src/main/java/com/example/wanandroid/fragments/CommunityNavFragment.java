package com.example.wanandroid.fragments;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.NavgationAdapter;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.NavgationBean;
import com.example.wanandroid.contract.NavgationContract;
import com.example.wanandroid.presenter.NavgationPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CommunityNavFragment extends BaseFragment<NavgationContract.INavgationView, NavgationPresenter> implements NavgationContract.INavgationView {

    @BindView(R.id.klswiperecyclerview)
    RecyclerView recyclerView;
    NavgationAdapter adapter;
    List<NavgationBean.DataBean> konwledgeHireList = new ArrayList<>();

    @Override
    protected NavgationPresenter createPresenter() {
        return new NavgationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community_context;
    }

    @Override
    protected void init() {
        initArticleRecycle();
        initData();
    }

    @Override
    protected void initListener() {

    }

    private void initData() {
        mPresenter.getDetailNavgation();
    }

    private void initArticleRecycle() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new NavgationAdapter(recyclerView, konwledgeHireList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateDetailNavgation(List<NavgationBean.DataBean> beanList) {
        konwledgeHireList.clear();
        konwledgeHireList.addAll(beanList);
        adapter.notifyDataSetChanged();
    }
}
