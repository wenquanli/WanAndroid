package com.example.wanandroid.fragments;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.KonwledgeHireAdapter;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.KonwledgeHireBean;
import com.example.wanandroid.contract.KonwledgeHierContract;
import com.example.wanandroid.presenter.KonwledgeHirePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CommunityContentFragment extends BaseFragment<KonwledgeHierContract.IKonwledgeHireView, KonwledgeHirePresenter> implements KonwledgeHierContract.IKonwledgeHireView {

    @BindView(R.id.klswiperecyclerview)
    RecyclerView recyclerView;
    KonwledgeHireAdapter adapter;
    List<KonwledgeHireBean.DataBean> konwledgeHireList = new ArrayList<>();

    @Override
    protected KonwledgeHirePresenter createPresenter() {
        return new KonwledgeHirePresenter();
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
        mPresenter.getDetailKnowledgeHier();
    }

    private void initArticleRecycle() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new KonwledgeHireAdapter(recyclerView, konwledgeHireList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateDetailKnowledgeHier(List<KonwledgeHireBean.DataBean> beanList) {
        konwledgeHireList.clear();
        konwledgeHireList.addAll(beanList);
        adapter.notifyDataSetChanged();
    }

}
