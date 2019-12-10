package com.example.thitkgiaodinassignment.fragment.income;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.adapter.income.IncomeAdapter;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIncomeShow extends Fragment {


    private IncomeDAO incomeDAO;
    private List<KhoanThu> listIncome;
    private IncomeAdapter incomeAdapter;
    private ListView lvIncome;


    public FragmentIncomeShow() {
        // Required empty public constructor
    }

    public static FragmentIncomeShow newInstance() {

        Bundle args = new Bundle();

        FragmentIncomeShow fragment = new FragmentIncomeShow();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_show, container, false);
        incomeDAO = new IncomeDAO(getContext());

        setLvIncome(view);

        return view;
    }

    private void setLvIncome(View view) {
        listIncome = incomeDAO.listKhoanThu();
        incomeAdapter = new IncomeAdapter(listIncome);
        lvIncome = view.findViewById(R.id.lvIncome);
        lvIncome.setAdapter(incomeAdapter);
        refreshLvIncome(500, view);
    }

    private void refreshLvIncome(int miliseconds, final View view) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setLvIncome(view);
            }
        };
        handler.postDelayed(runnable, miliseconds);
    }

}
