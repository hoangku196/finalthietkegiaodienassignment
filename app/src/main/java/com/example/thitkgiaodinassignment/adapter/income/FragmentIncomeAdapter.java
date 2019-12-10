package com.example.thitkgiaodinassignment.adapter.income;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeAdd;
import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeShow;
import com.example.thitkgiaodinassignment.fragment.income.FragmentTypeIncomeAdd;
import com.example.thitkgiaodinassignment.fragment.income.FragmentTypeIncomeShow;

public class FragmentIncomeAdapter extends FragmentPagerAdapter {
    private static int NUM_PAGES = 4;

    public FragmentIncomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentIncomeAdd.newInstance();
            case 1:
                return FragmentTypeIncomeAdd.newInstance();
            case 2:
                return FragmentIncomeShow.newInstance();
            case 3:
                return FragmentTypeIncomeShow.newInstance();
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
        switch (position){
            case 0:
                return "Thêm khoản thu mới";
            case 1:
                return "Thêm loại thu mới";
            case 2:
                return "Các khoản thu hiện tại";
            case 3:
                return "Các loại thu hiện tại";
            default:
                return null;
        }
    }
}
