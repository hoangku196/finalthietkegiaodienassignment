package com.example.thitkgiaodinassignment.fragment.spend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeAdd;
import com.example.thitkgiaodinassignment.fragment.income.FragmentTypeIncomeAdd;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTypeSpendAdd extends Fragment {

    private SpendDAO spendDAO;
    private EditText edtMaLC, edtTenLC;
    private Button btnSave, btnReset;

    public FragmentTypeSpendAdd() {
        // Required empty public constructor
    }

    public static FragmentTypeSpendAdd newInstance() {

        Bundle args = new Bundle();

        FragmentTypeSpendAdd fragment = new FragmentTypeSpendAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typespend_add, container, false);
        spendDAO = new SpendDAO(getContext());
        findAllView(view);

        return view;
    }

    private void findAllView(View view) {
        edtMaLC = view.findViewById(R.id.edtMaLC);
        edtTenLC = view.findViewById(R.id.edtTenLC);
        btnSave = view.findViewById(R.id.btnSaveTypeSpend);
        setBtnSave();
        btnReset = view.findViewById(R.id.btnBackTypeSpend);
        setBtnReset();
    }

    private void setBtnReset() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaLC.setText("");
                edtTenLC.setText("");
            }
        });
    }

    private void setBtnSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLC = edtMaLC.getText().toString();
                String tenLC = edtTenLC.getText().toString();
                boolean check = spendDAO.insertNewTypeSpend(new LoaiChi(maLC, tenLC));
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    FragmentSpendAdd.checkRefeshSpinner = true;
                    edtMaLC.setText("");
                    edtTenLC.setText("");
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
