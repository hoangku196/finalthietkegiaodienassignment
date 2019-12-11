package com.example.thitkgiaodinassignment.adapter.income;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.Main2Activity;
import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;

import java.util.List;

public class IncomeAdapter extends BaseAdapter {
    private List<KhoanThu> listIncome;
    private TextView lblDateIncome, lblTypeIncome;
    private ImageView imgInformationIncome, imgEditIncome, imgDeleteIncome;
    private IncomeDAO incomeDAO;

    public IncomeAdapter(List<KhoanThu> listIncome) {
        this.listIncome = listIncome;
    }

    @Override
    public int getCount() {
        return listIncome.size();
    }

    @Override
    public Object getItem(int position) {
        return listIncome.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_income_details, parent, false);


        KhoanThu income = (KhoanThu) getItem(position);
        findAddViewById(view, income, parent.getContext());

        setLblDateIncome(income);
        setLblTypeIncome(income);


        return view;
    }

    private void findAddViewById(View view, final KhoanThu income, final Context context) {
        incomeDAO = new IncomeDAO(context);

        lblDateIncome = view.findViewById(R.id.lblDateIncome);

        lblTypeIncome = view.findViewById(R.id.lblTypeIncome);

        imgInformationIncome = view.findViewById(R.id.imgInformationIncome);
        setImgInformationIncome(income, context);

        imgEditIncome = view.findViewById(R.id.imgEditIncome);
        setImgEditIncome(income, context);

        imgDeleteIncome = view.findViewById(R.id.imgDeleteIncome);
        setImgDeleteIncome(income, context);
    }

    private void setLblDateIncome(KhoanThu income) {
        lblDateIncome.setText(income.getDate());
    }

    private void setLblTypeIncome(KhoanThu income) {
        lblTypeIncome.setText(income.getTenKT());
    }

    //TODO
    private void setImgEditIncome(final KhoanThu income, final Context context) {
        imgEditIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("layout", "layout_edit_income");
                bundle.putString("key_maKT", income.getMaKT());
                bundle.putString("key_tenKT", income.getTenKT());
                bundle.putString("key_maLT", income.getLoaiThu().getMaLT());
                bundle.putString("key_tenLT", income.getLoaiThu().getTenLT());
                bundle.putDouble("key_money", income.getMoney());
                bundle.putString("key_date", income.getDate());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void setImgInformationIncome(final KhoanThu income, final Context context) {
        imgInformationIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setTitle("Thông tin khoản thu");
                dialog.setContentView(R.layout.dialog_income_information);
                TextView lblMaKTInformation, lblTenKTInformation, lblMoneyKTInformation, lblTenLoaiThuInformation, lblDateKTInformation;
                findAllView:
                {
                    lblMaKTInformation = dialog.findViewById(R.id.lblMaKTInformation);
                    lblTenKTInformation = dialog.findViewById(R.id.lblTenKTInformation);
                    lblMoneyKTInformation = dialog.findViewById(R.id.lblMoneyKTInformation);
                    lblTenLoaiThuInformation = dialog.findViewById(R.id.lblTenLoaiThuInformation);
                    lblDateKTInformation = dialog.findViewById(R.id.lblDateKTInformation);
                }
                setText:
                {
                    lblMaKTInformation.setText(income.getMaKT());
                    lblTenKTInformation.setText(income.getTenKT());
                    lblMoneyKTInformation.setText(String.valueOf(income.getMoney()));
                    lblTenLoaiThuInformation.setText(incomeDAO.searchTenLTByMaLT(income.getLoaiThu().getMaLT()));
                    lblDateKTInformation.setText(income.getDate());
                }
                dialog.show();
            }
        });
    }

    private void setImgDeleteIncome(final KhoanThu income, final Context context) {
        imgDeleteIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa khoản thu " + income.getMaKT() + " ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        incomeDAO.deleteIncome(income.getMaKT());
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
