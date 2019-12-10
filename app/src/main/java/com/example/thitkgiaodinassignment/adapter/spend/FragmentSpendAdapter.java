package com.example.thitkgiaodinassignment.adapter.spend;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thitkgiaodinassignment.fragment.spend.FragmentSpendAdd;
import com.example.thitkgiaodinassignment.fragment.spend.FragmentSpendShow;
import com.example.thitkgiaodinassignment.fragment.spend.FragmentTypeSpendAdd;
import com.example.thitkgiaodinassignment.fragment.spend.FragmentTypeSpendShow;

public class FragmentSpendAdapter extends FragmentPagerAdapter {
    private static int NUM_PAGES = 4;

    public FragmentSpendAdapter(FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentSpendAdd.newInstance();
            case 1:
                return FragmentTypeSpendAdd.newInstance();
            case 2:
                return FragmentSpendShow.newInstance();
            case 3:
                return FragmentTypeSpendShow.newInstance();
            default:
                return new Fragment();
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
                return "Thêm khoản chi mới";
            case 1:
                return "Thêm loại chi mới";
            case 2:
                return "Các khoản chi hiện tại";
            case 3:
                return "Các loại chi hiện tại";
            default:
                return null;
        }
    }
}
