package com.example.thitkgiaodinassignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;
import com.example.thitkgiaodinassignment.object.spend.KhoanChi;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ActionBar actionBar;
    private Bundle bundle;
    private IncomeDAO incomeDAO;
    private SpendDAO spendDAO;
    private List<String> listTypeNameIncome;
    private List<String> listTypeNameSpend;
    private ArrayAdapter<String> adapter;

    //Income
    private TextView lblEditMaKT;
    private EditText edtEditTenKT, edtEditMoneyIncome, edtEditDateIncome;
    private Button btnEditSaveIncome, btnEditBack;
    private Spinner spEditTenLT;
    //Type Income
    private TextView lblEditMaLT;
    private EditText edtEditTenLT;
    private Button btnSaveEditTypeIncome, btnEditBackTypeIncome;
    //Spend
    private TextView lblEditMaKC;
    private EditText edtEditTenKC, edtEditMoneySpend, edtEditDateSpend;
    private Button btnEditSaveSpend, btnEditBackSpend;
    private Spinner spEditTenLC;
    //Type Spend
    private TextView lblEditMaLC;
    private EditText edtEditTenLC;
    private Button btnSaveEditTypeSpend, btnEditBackTypeSpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        incomeDAO = new IncomeDAO(this);
        spendDAO = new SpendDAO(this);
        bundle = getIntent().getExtras();

        String layout = bundle.getString("layout");
        switch (layout) {
            case "layout_edit_income":
                setContentView(R.layout.activity_edit_income);
                actionBar.setTitle("Sửa khoản thu");
                setActionLayoutEditIncome();
                break;
            case "layout_edit_typeincome":
                setContentView(R.layout.activity_edit_typeincome);
                actionBar.setTitle("Sửa loại thu");
                setActionLayoutEditTypeIncome();
                break;
            case "layout_edit_spend":
                setContentView(R.layout.activity_edit_spend);
                actionBar.setTitle("Sửa khoản chi");
                setActionLayoutEditSpend();
                break;
            case "layout_edit_typespend":
                setContentView(R.layout.activity_edit_typespend);
                actionBar.setTitle("Sửa loại chi");
                setActionLayoutEditTypeSpend();
            default:
                break;
        }

    }

    private void setActionLayoutEditTypeSpend() {
        lblEditMaLC = findViewById(R.id.lblEditMaLC);
        lblEditMaLC.setText(bundle.getString("key_maLC"));

        edtEditTenLC = findViewById(R.id.edtEditTenLC);
        edtEditTenLC.setText(bundle.getString("key_tenLC"));

        btnSaveEditTypeSpend = findViewById(R.id.btnSaveEditTypeSpend);
        setBtnSaveEditTypeSpend();

        btnEditBackTypeSpend = findViewById(R.id.btnEditBackTypeSpend);
        setBtnEditBackTypeSpend();
    }

    private void setBtnEditBackTypeSpend() {
        btnEditBackTypeSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setBtnSaveEditTypeSpend() {
        btnSaveEditTypeSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maLC = lblEditMaLC.getText().toString();
                    String tenLC = edtEditTenLC.getText().toString();
                    boolean check = spendDAO.updateTypeSpend(new LoaiChi(maLC, tenLC));
                    if (check) {
                        Toast.makeText(Main2Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(Main2Activity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setActionLayoutEditSpend() {
        lblEditMaKC = findViewById(R.id.lblEditMaKC);
        lblEditMaKC.setText(bundle.getString("key_maKC"));

        edtEditTenKC = findViewById(R.id.edtEditTenKC);
        edtEditTenKC.setText(bundle.getString("key_tenKC"));

        spEditTenLC = findViewById(R.id.spEditTenLC);
        setSpEditTenLC();

        edtEditMoneySpend = findViewById(R.id.edtEditMoneySpend);
        edtEditMoneySpend.setText(bundle.getString("key_money"));

        edtEditDateSpend = findViewById(R.id.edtEditDateSpend);
        showDateSpendPicker();
        edtEditDateSpend.setText(bundle.getString("key_date"));

        btnEditSaveSpend = findViewById(R.id.btnEditSaveSpend);
        setBtnEditSaveSpend();

        btnEditBackSpend = findViewById(R.id.btnEditBackSpend);
        setBtnEditBackSpend();

    }

    private void setBtnEditBackSpend() {
        btnEditBackSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void showDateSpendPicker() {
        edtEditDateSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, dayOfMonth);
                        edtEditDateSpend.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setBtnEditSaveSpend() {
        btnEditSaveSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maKC = lblEditMaKC.getText().toString();
                    String tenKC = edtEditTenKC.getText().toString();
                    LoaiChi loaiChi = new LoaiChi(bundle.getString("key_maLC"), spEditTenLC.getSelectedItem().toString());
                    double money = Double.parseDouble(edtEditMoneySpend.getText().toString());
                    String date = edtEditDateSpend.getText().toString();

                    boolean check = spendDAO.updateSpend(new KhoanChi(maKC, tenKC, date, money, loaiChi));
                    if (check) {
                        Toast.makeText(Main2Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main2Activity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSpEditTenLC() {
        listTypeNameSpend = spendDAO.listNameTypeSpend();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTypeNameSpend);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spEditTenLC.setAdapter(adapter);
    }

    private void setActionLayoutEditTypeIncome() {
        lblEditMaLT = findViewById(R.id.lblEditMaLT);
        lblEditMaLT.setText(bundle.getString("key_maLT"));

        edtEditTenLT = findViewById(R.id.edtEditTenLT);
        edtEditTenLT.setText(bundle.getString("key_tenLT"));

        btnSaveEditTypeIncome = findViewById(R.id.btnSaveEditTypeIncome);
        setBtnSaveEditTypeIncome();

        btnEditBackTypeIncome = findViewById(R.id.btnEditBackTypeIncome);
        setBtnEditBackTypeIncome();

    }

    private void setBtnEditBackTypeIncome() {
        btnEditBackTypeIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setBtnSaveEditTypeIncome() {
        btnSaveEditTypeIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maLT = lblEditMaLT.getText().toString();
                    String tenLT = edtEditTenLT.getText().toString();
                    boolean check = incomeDAO.updateTypeIncome(new LoaiThu(maLT, tenLT));
                    if (check) {
                        Toast.makeText(Main2Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main2Activity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setActionLayoutEditIncome() {
        lblEditMaKT = findViewById(R.id.lblEditMaKT);
        lblEditMaKT.setText(bundle.getString("key_maKT"));

        edtEditTenKT = findViewById(R.id.edtEditTenKT);
        edtEditTenKT.setText(bundle.getString("key_tenKT"));

        spEditTenLT = findViewById(R.id.spEditTenLT);
        setSpEditTenLT();

        edtEditMoneyIncome = findViewById(R.id.edtEditMoneyIncome);
        edtEditMoneyIncome.setText(bundle.getString("key_money"));

        edtEditDateIncome = findViewById(R.id.edtEditDateIncome);
        showDatePicker();
        edtEditDateIncome.setText(bundle.getString("key_date"));

        btnEditSaveIncome = findViewById(R.id.btnEditSaveIncome);
        setBtnEditSaveIncome();

        btnEditBack = findViewById(R.id.btnEditBack);
        setBtnEditBack();

    }

    private void setSpEditTenLT() {
        listTypeNameIncome = incomeDAO.listNameTypeIncome();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listTypeNameIncome);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spEditTenLT.setAdapter(adapter);
    }

    private void showDatePicker() {
        edtEditDateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, dayOfMonth);
                        edtEditDateIncome.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setBtnEditBack() {
        btnEditBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void setBtnEditSaveIncome() {
        btnEditSaveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maKT = lblEditMaKT.getText().toString();
                    String tenKT = edtEditTenKT.getText().toString();
                    LoaiThu loaiThu = new LoaiThu(bundle.getString("key_maLT"), spEditTenLT.getSelectedItem().toString());
                    double money = Double.parseDouble(edtEditMoneyIncome.getText().toString());
                    String date = edtEditDateIncome.getText().toString();

                    boolean check = incomeDAO.updateIncome(new KhoanThu(maKT, tenKT, date, money, loaiThu));
                    if (check) {
                        Toast.makeText(Main2Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Main2Activity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Main2Activity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
