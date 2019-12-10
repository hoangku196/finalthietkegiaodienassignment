package com.example.thitkgiaodinassignment.fragment.income;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTypeIncomeAdd extends Fragment {

    private IncomeDAO incomeDAO;
    private EditText edtMaLT, edtTenLT;
    private Button btnSave, btnReset;

    public FragmentTypeIncomeAdd() {
        // Required empty public constructor
    }

    public static FragmentTypeIncomeAdd newInstance() {

        Bundle args = new Bundle();

        FragmentTypeIncomeAdd fragment = new FragmentTypeIncomeAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typeincome_add, container, false);
        incomeDAO = new IncomeDAO(getContext());
        findAllView(view);

        return view;
    }

    private void findAllView(View view) {
        edtMaLT = view.findViewById(R.id.lblMaLT);
        edtTenLT = view.findViewById(R.id.edtTenLT);
        btnSave = view.findViewById(R.id.btnSaveTypeIncome);
        setBtnSave();
        btnReset = view.findViewById(R.id.btnBackTypeIncome);
        setBtnReset();
    }

    private void setBtnReset() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaLT.setText("");
                edtTenLT.setText("");
            }
        });
    }

    private void setBtnSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLT = edtMaLT.getText().toString();
                String tenLT = edtTenLT.getText().toString();
                boolean check = incomeDAO.insertNewTypeIncome(new LoaiThu(maLT, tenLT));
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    FragmentIncomeAdd.checkRefeshSpinner = true;
                    edtMaLT.setText("");
                    edtTenLT.setText("");
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
