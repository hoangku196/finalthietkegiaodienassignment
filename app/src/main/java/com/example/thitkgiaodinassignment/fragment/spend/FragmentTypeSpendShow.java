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
import com.example.thitkgiaodinassignment.adapter.income.TypeIncomeAdapter;
import com.example.thitkgiaodinassignment.adapter.spend.TypeSpendAdapter;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.fragment.income.FragmentTypeIncomeShow;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTypeSpendShow extends Fragment {
    private ListView listView;
    private SpendDAO spendDAO;
    private List<LoaiChi> listLoaiChi;
    private TypeSpendAdapter typeSpendAdapter;

    public FragmentTypeSpendShow() {
        // Required empty public constructor
    }

    public static FragmentTypeSpendShow newInstance() {

        Bundle args = new Bundle();

        FragmentTypeSpendShow fragment = new FragmentTypeSpendShow();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typespend_show, container, false);
        listView = view.findViewById(R.id.lvTypeSpend);
        spendDAO = new SpendDAO(getContext());
        setListView();

        return view;
    }

    private void setListView() {
        listLoaiChi = spendDAO.listLoaiChi();
        typeSpendAdapter = new TypeSpendAdapter(listLoaiChi);
        listView.setAdapter(typeSpendAdapter);
        refreshLvSpend(500);
    }

    private void refreshLvSpend(int miliseconds) {
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
