package com.example.thitkgiaodinassignment.fragment.result;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.ResultDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResultMain extends Fragment {

    private ResultDAO resultDAO;

    private TextView lblCountIncome, lblResultIncome, lblCountSpend, lblResultSpend, lblResult, lblMessage;

    double result = 0;

    public FragmentResultMain() {
        // Required empty public constructor
    }

    public static FragmentResultMain newInstance() {

        Bundle args = new Bundle();

        FragmentResultMain fragment = new FragmentResultMain();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_main, container, false);
        resultDAO = new ResultDAO(getContext());
        findAllViewByID(view);

        return view;
    }

    private void findAllViewByID(View view) {
        lblCountIncome = view.findViewById(R.id.lblCountIncome);
        setLblCountIncome();

        lblCountSpend = view.findViewById(R.id.lblCountSpend);
        setLblCountSpend();

        lblResultIncome = view.findViewById(R.id.lblResultIncome);
        setLblResultIncome();

        lblResultSpend = view.findViewById(R.id.lblResultSpend);
        setLblResultSpend();

        lblResult = view.findViewById(R.id.lblResult);
        setLblResult();

        lblMessage = view.findViewById(R.id.lblMessage);
        setLblMessage();

        refreshLvSpend(1000, view);
    }

    private void setLblMessage() {
        if (result > 0) {
            lblMessage.setText("Bạn đang tiết kiệm được " + result + " VND");
        } else if (result == 0) {
            lblMessage.setText("Bạn ko tiết kiệm được đồng nào");
        } else {
            lblMessage.setText("Bạn đang âm tiền " + result + " VND");
        }
    }

    private void setLblResult() {
        result = resultDAO.resultIncome() - resultDAO.resultSpend();
        lblResult.setText(String.valueOf(result));
    }

    private void setLblResultSpend() {
        lblResultSpend.setText(resultDAO.resultSpend() + ".VND");
    }

    private void setLblResultIncome() {
        lblResultIncome.setText(resultDAO.resultIncome() + ".VND");
    }

    private void setLblCountSpend() {
        lblCountSpend.setText(resultDAO.countSpend() + " Hóa đơn");
    }

    private void setLblCountIncome() {
        lblCountIncome.setText(resultDAO.countIncome() + " Khoản");
    }

    private void refreshLvSpend(int miliseconds, final View view) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                findAllViewByID(view);
            }
        };
        handler.postDelayed(runnable, miliseconds);
    }
}
