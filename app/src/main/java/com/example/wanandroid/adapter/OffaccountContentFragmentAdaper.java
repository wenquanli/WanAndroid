package com.example.wanandroid.adapter;

import android.os.Bundle;
import android.view.View;

import com.example.wanandroid.fragments.OffaccountContentFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OffaccountContentFragmentAdaper extends FragmentPagerAdapter {
    private List<String> names;

    public OffaccountContentFragmentAdaper(FragmentManager childFragmentManager) {
        super(childFragmentManager);
        this.names = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        OffaccountContentFragment offaccountContentFragment = new OffaccountContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", this.names.get(position));
        offaccountContentFragment.setArguments(bundle);
        return offaccountContentFragment;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    public void setList(List<String> names) {
        this.names.clear();
        this.names.addAll(names);
        notifyDataSetChanged();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = names.get(position);
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
