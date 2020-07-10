package com.example.wanandroid.adapter;

import com.example.wanandroid.fragments.CommunityContentFragment;
import com.example.wanandroid.fragments.CommunityNavFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CommunityContentFragmentAdaper extends FragmentPagerAdapter {
    private List<String> names;

    public CommunityContentFragmentAdaper(FragmentManager childFragmentManager) {
        super(childFragmentManager);
        this.names = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CommunityContentFragment();
                break;
            case 1:
                fragment = new CommunityNavFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return this.names.size();
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
