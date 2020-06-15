package com.example.wanandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.OffaccountContentFragmentAdaper;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OffaccountFragment extends androidx.fragment.app.Fragment {
    @BindView(R.id.offaccount_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.offaccount_view_pages)
    ViewPager viewPager;
    private OffaccountContentFragmentAdaper adaper;
    private List<String> names;
    public OffaccountFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        names = new ArrayList<>();
        names.add("公众号1");
        names.add("公众号2");
        names.add("公众号3");
        names.add("公众号4");
        names.add("公众号5");
        names.add("公众号6");
        names.add("公众号7");
        names.add("公众号8");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offaccount,container,false);
        ButterKnife.bind(this, view);
        adaper = new OffaccountContentFragmentAdaper(getChildFragmentManager());
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);

        adaper.setList(names);
        return view;
    }
}
