package com.example.thitkgiaodinassignment.fragment.income;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.adapter.income.TypeIncomeAdapter;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTypeIncomeShow extends Fragment {

    private ListView listView;
    private IncomeDAO incomeDAO;
    private List<LoaiThu> listLoaiThu;
    private TypeIncomeAdapter typeIncomeAdapter;

    public FragmentTypeIncomeShow() {
        // Required empty public constructor
    }

    public static FragmentTypeIncomeShow newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentTypeIncomeShow fragment = new FragmentTypeIncomeShow();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typeincome_show, container, false);
        listView = view.findViewById(R.id.lvTypeIncome);
        incomeDAO = new IncomeDAO(getContext());
        setListView();

        return view;
    }

    private void setListView() {
        listLoaiThu = incomeDAO.listLoaiThu();
        typeIncomeAdapter = new TypeIncomeAdapter(listLoaiThu);
        listView.setAdapter(typeIncomeAdapter);
        refreshLvIncome(500);
    }

    private void refreshLvIncome(int miliseconds) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setListView();
            }
        };
        handler.postDelayed(runnable, miliseconds);
    }

}
