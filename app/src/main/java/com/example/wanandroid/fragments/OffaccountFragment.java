package com.example.wanandroid.fragments;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.OffaccountContentFragmentAdaper;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.OffAccountBean;
import com.example.wanandroid.constant.Constant;
import com.example.wanandroid.contract.OffAccountContract;
import com.example.wanandroid.presenter.OffAccountPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class OffaccountFragment extends BaseFragment<OffAccountContract.IOffAccountView, OffAccountPresenter> implements OffAccountContract.IOffAccountView{
    @BindView(R.id.offaccount_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.offaccount_view_pages)
    ViewPager viewPager;
    private OffaccountContentFragmentAdaper adaper;
    private List<OffAccountBean.DataBean> names = new ArrayList<>();
    public OffaccountFragment() {
    }

    private void initData() {
        presenter.loadOffAccount();
    }

    @Override
    protected OffAccountPresenter createPresenter() {
        return new OffAccountPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_offaccount;
    }

    @Override
    protected void init() {
        adaper = new OffaccountContentFragmentAdaper(getChildFragmentManager());
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);
        initData();

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void loadOffAccount(OffAccountBean offAccountBean) {
        if (offAccountBean.getErrorCode() == Constant.BANNER_SUCCESS) {
            names = offAccountBean.getData();
            adaper.setList(names);
        }
    }
}
