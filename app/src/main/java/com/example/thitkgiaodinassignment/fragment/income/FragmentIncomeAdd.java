package com.example.thitkgiaodinassignment.fragment.income;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIncomeAdd extends Fragment {
    public static boolean checkRefeshSpinner = false;
    private EditText edtMoneyIncome, edtDateIncome, edtMaKT, edtTenKT;
    private Spinner spTenLT;
    private Button btnSaveIncome, btnResetIncome;
    private IncomeDAO incomeDAO;
    private List<String> listTypeNameIncome;
    private ArrayAdapter<String> adapter;

    public FragmentIncomeAdd() {
        // Required empty public constructor
    }

    public static FragmentIncomeAdd newInstance() {

        Bundle args = new Bundle();

        FragmentIncomeAdd fragment = new FragmentIncomeAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_add, container, false);
        incomeDAO = new IncomeDAO(getActivity());

        addAllViewById(view, getContext());


        return view;
    }

    private void addAllViewById(View view, Context context) {
        edtMoneyIncome = view.findViewById(R.id.edtMoneyIncome);

        spTenLT = view.findViewById(R.id.spTenLT);
        showSpinner(context);

        edtDateIncome = view.findViewById(R.id.edtDateIncome);
        showDatePicker();

        edtMaKT = view.findViewById(R.id.edtMaKT);
        edtTenKT = view.findViewById(R.id.edtTenKT);

        btnSaveIncome = view.findViewById(R.id.btnSaveIncome);
        setBtnSaveIncome();

        btnResetIncome = view.findViewById(R.id.btnResetIncome);
        setBtnResetIncome();

        setCheckRefeshSpinner(context);
    }

    private void showSpinner(Context context) {
        listTypeNameIncome = incomeDAO.listNameTypeIncome();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listTypeNameIncome);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spTenLT.setAdapter(adapter);
    }

    private void setCheckRefeshSpinner(Context context) {
        if (checkRefeshSpinner) {
            resetSpinner(1000, context);
            checkRefeshSpinner = false;
        }
        resetCheckRefeshSpinner(1000, context);
    }

    private void resetCheckRefeshSpinner(int millisecond, final Context context) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setCheckRefeshSpinner(context);
            }
        };
        handler.postDelayed(runnable, millisecond);
    }

    private void resetSpinner(int millisecond, final Context context) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showSpinner(context);
            }
        };
        handler.postDelayed(runnable, millisecond);
        if (!checkRefeshSpinner) {
            handler.removeCallbacks(runnable);
        }
    }

    private void showDatePicker() {
        edtDateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, dayOfMonth);
                        edtDateIncome.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setBtnSaveIncome() {
        btnSaveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maKT = edtMaKT.getText().toString();
                    String tenKT = edtTenKT.getText().toString();
                    String date = edtDateIncome.getText().toString();
                    double money = Double.parseDouble(edtMoneyIncome.getText().toString());
                    //todo: BUG
                    LoaiThu loaiThu = new LoaiThu(incomeDAO.searchMaLTByTenLT(spTenLT.getSelectedItem().toString()), spTenLT.getSelectedItem().toString());
                    boolean check = incomeDAO.insertNewIncome(new KhoanThu(maKT, tenKT, date, money, loaiThu));
                    if (check) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        edtMoneyIncome.setText("");
                        edtDateIncome.setText("");
                        edtMaKT.setText("");
                        edtTenKT.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Điền đầy đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setBtnResetIncome() {
        btnResetIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMoneyIncome.setText("");
                edtDateIncome.setText("");
                edtMaKT.setText("");
                edtTenKT.setText("");
            }
        });
    }
}
