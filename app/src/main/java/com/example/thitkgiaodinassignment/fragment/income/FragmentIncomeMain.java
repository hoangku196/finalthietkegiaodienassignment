package com.example.thitkgiaodinassignment.fragment.income;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.adapter.income.FragmentIncomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIncomeMain extends Fragment {
    private FragmentManager fm;
    private FragmentIncomeAdapter fragmentIncomeAdapter;

    public FragmentIncomeMain(FragmentManager fm) {
        this.fm = fm;
    }

    public static FragmentIncomeMain newInstance(FragmentManager fm) {

        Bundle args = new Bundle();

        FragmentIncomeMain fragment = new FragmentIncomeMain(fm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_main, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        ViewPager vpIncome = view.findViewById(R.id.vpIncome);
        fragmentIncomeAdapter = new FragmentIncomeAdapter(fragmentManager);
        vpIncome.setAdapter(fragmentIncomeAdapter);

        return view;
    }
}
