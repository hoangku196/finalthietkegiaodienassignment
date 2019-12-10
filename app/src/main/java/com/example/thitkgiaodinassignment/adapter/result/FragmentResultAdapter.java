package com.example.thitkgiaodinassignment.adapter.result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentResultAdapter extends FragmentPagerAdapter {
    private static int NUM_PAGES = 3;

    public FragmentResultAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return null;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Thống kê khoản thu";
            case 1:
                return "Thống kê khoản chi";
            case 2:
                return "Thống kê toàn bộ";
            default:
                return null;
        }
    }
}
