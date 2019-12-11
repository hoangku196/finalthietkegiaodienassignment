package com.example.thitkgiaodinassignment.fragment.spend;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeAdd;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;
import com.example.thitkgiaodinassignment.object.spend.KhoanChi;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpendAdd extends Fragment {
    public static boolean checkRefeshSpinner = false;
    private EditText edtMoneySpend, edtDateSpend, edtMaKC, edtTenKC;
    private Spinner spTenLC;
    private Button btnSaveSpend, btnResetSpend;
    private SpendDAO spendDAO;
    private List<String> listTypeNameSpend;
    private ArrayAdapter<String> adapter;

    public FragmentSpendAdd() {
        // Required empty public constructor
    }

    public static FragmentSpendAdd newInstance() {

        Bundle args = new Bundle();

        FragmentSpendAdd fragment = new FragmentSpendAdd();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spend_add, container, false);
        spendDAO = new SpendDAO(getActivity());

        addAllViewById(view, getContext());


        return view;
    }

    private void addAllViewById(View view, Context context) {
        edtMoneySpend = view.findViewById(R.id.edtMoneySpend);

        spTenLC = view.findViewById(R.id.spTenLC);
        showSpinner(context);

        edtDateSpend = view.findViewById(R.id.edtDateSpend);
        showDatePicker();

        edtMaKC = view.findViewById(R.id.edtMaKC);
        edtTenKC = view.findViewById(R.id.edtTenKC);

        btnSaveSpend = view.findViewById(R.id.btnSaveSpend);
        setBtnSaveIncome();

        btnResetSpend = view.findViewById(R.id.btnResetSpend);
        setBtnResetIncome();

        setCheckRefeshSpinner(context);
    }

    private void showSpinner(Context context) {
        listTypeNameSpend = spendDAO.listNameTypeSpend();
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listTypeNameSpend);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spTenLC.setAdapter(adapter);
    }

    private void setCheckRefeshSpinner(Context context) {
        if (checkRefeshSpinner) {
            resetSpinner(1000, context);
            checkRefeshSpinner = false;
        }
        resetCheckRefreshSpinner(1000, context);
    }

    private void resetCheckRefreshSpinner(int millisecond, final Context context) {
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
        edtDateSpend.setOnClickListener(new View.OnClickListener() {
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
                        edtDateSpend.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setBtnSaveIncome() {
        btnSaveSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maKC = edtMaKC.getText().toString();
                    String tenKC = edtTenKC.getText().toString();
                    String date = edtDateSpend.getText().toString();
                    double money = Double.parseDouble(edtMoneySpend.getText().toString());
                    LoaiChi loaiChi = new LoaiChi(spendDAO.searchMaLCByTenLC(spTenLC.getSelectedItem().toString()), spTenLC.getSelectedItem().toString());
                    boolean check = spendDAO.insertNewSpend(new KhoanChi(maKC, tenKC, date, money, loaiChi));
                    if (check) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        edtMoneySpend.setText("");
                        edtDateSpend.setText("");
                        edtMaKC.setText("");
                        edtTenKC.setText("");
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
        btnResetSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMoneySpend.setText("");
                edtDateSpend.setText("");
                edtMaKC.setText("");
                edtTenKC.setText("");
            }
        });
    }
}
