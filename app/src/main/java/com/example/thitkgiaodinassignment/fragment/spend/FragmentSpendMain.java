package com.example.thitkgiaodinassignment.fragment.spend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.adapter.spend.FragmentSpendAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpendMain extends Fragment {
    private FragmentManager fm;
    private FragmentSpendAdapter fragmentSpendAdapter;

    public FragmentSpendMain(FragmentManager fm) {
        this.fm = fm;
    }

    public static FragmentSpendMain newInstance(FragmentManager fm) {



        Bundle args = new Bundle();

        FragmentSpendMain fragment = new FragmentSpendMain(fm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spend_main, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        ViewPager vpSpend = view.findViewById(R.id.vpSpend);
        fragmentSpendAdapter = new FragmentSpendAdapter(fragmentManager);
        vpSpend.setAdapter(fragmentSpendAdapter);

        return view;
    }

}
