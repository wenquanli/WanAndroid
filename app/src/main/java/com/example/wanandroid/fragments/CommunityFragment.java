package com.example.wanandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.CommunityContentFragmentAdaper;
import com.example.wanandroid.adapter.OffaccountContentFragmentAdaper;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CommunityFragment extends androidx.fragment.app.Fragment {
    @BindView(R.id.community_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.community_view_pages)
    ViewPager viewPager;
    private CommunityContentFragmentAdaper adaper;
    private List<String> names;
    public CommunityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        names = new ArrayList<>();
        names.add("体系");
        names.add("导航");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community,container,false);
        ButterKnife.bind(this, view);
        adaper = new CommunityContentFragmentAdaper(getChildFragmentManager());
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);

        adaper.setList(names);
        return view;


    }
}
