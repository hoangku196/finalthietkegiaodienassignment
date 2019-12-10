package com.example.thitkgiaodinassignment.fragment.spend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.adapter.income.IncomeAdapter;
import com.example.thitkgiaodinassignment.adapter.spend.SpendAdapter;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeShow;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.spend.KhoanChi;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpendShow extends Fragment {

    private SpendDAO spendDAO;
    private List<KhoanChi> listSpend;
    private SpendAdapter spendAdapter;
    private ListView lvSpend;


    public FragmentSpendShow() {
        // Required empty public constructor
    }

    public static FragmentSpendShow newInstance() {

        Bundle args = new Bundle();

        FragmentSpendShow fragment = new FragmentSpendShow();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spend_show, container, false);
        spendDAO = new SpendDAO(getContext());

        setLvSpend(view);

        return view;
    }

    private void setLvSpend(View view) {
        listSpend = spendDAO.listKhoanChi();
        spendAdapter = new SpendAdapter(listSpend);
        lvSpend = view.findViewById(R.id.lvSpend);
        lvSpend.setAdapter(spendAdapter);
        refreshLvIncome(500, view);
    }

    private void refreshLvIncome(int miliseconds, final View view) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setLvSpend(view);
            }
        };
        handler.postDelayed(runnable, miliseconds);
    }

}
