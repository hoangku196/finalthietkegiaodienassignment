package com.example.thitkgiaodinassignment.adapter.spend;

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
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.spend.KhoanChi;

import java.util.List;

public class SpendAdapter extends BaseAdapter {
    private List<KhoanChi> listSpend;
    private TextView lblDateSpend, lblTypeSpend;
    private ImageView imgInformationSpend, imgEditSpend, imgDeleteSpend;
    private SpendDAO spendDAO;

    public SpendAdapter(List<KhoanChi> listSpend) {
        this.listSpend = listSpend;
    }

    @Override
    public int getCount() {
        return listSpend.size();
    }

    @Override
    public Object getItem(int position) {
        return listSpend.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_spend_details, parent, false);


        KhoanChi spend = (KhoanChi) getItem(position);
        findAddViewById(view, spend, parent.getContext());

        setLblDateIncome(spend);
        setLblTypeIncome(spend);


        return view;
    }

    private void findAddViewById(View view, final KhoanChi spend, final Context context) {
        spendDAO = new SpendDAO(context);

        lblDateSpend = view.findViewById(R.id.lblDateSpend);

        lblTypeSpend = view.findViewById(R.id.lblTypeSpend);

        imgInformationSpend = view.findViewById(R.id.imgInformationSpend);
        setImgInformationIncome(spend, context);

        imgEditSpend = view.findViewById(R.id.imgEditSpend);
        setImgEditIncome(spend, context);

        imgDeleteSpend = view.findViewById(R.id.imgDeleteSpend);
        setImgDeleteIncome(spend, context);
    }

    private void setLblDateIncome(KhoanChi spend) {
        lblDateSpend.setText(spend.getDate());
    }

    private void setLblTypeIncome(KhoanChi spend) {
        lblTypeSpend.setText(spend.getTenKC());
    }

    //TODO
    private void setImgEditIncome(final KhoanChi spend, final Context context) {
        imgEditSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("layout", "layout_edit_spend");
                bundle.putString("key_maKC", spend.getMaKC());
                bundle.putString("key_tenKC", spend.getTenKC());
                bundle.putString("key_maLC", spend.getLoaiChi().getMaLC());
                bundle.putString("key_tenLC", spend.getLoaiChi().getTenLC());
                bundle.putDouble("key_money", spend.getMoney());
                bundle.putString("key_date", spend.getDate());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void setImgInformationIncome(final KhoanChi spend, final Context context) {
        imgInformationSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setTitle("Thông tin khoản chi");
                dialog.setContentView(R.layout.dialog_spend_information);
                TextView lblMaKCInformation, lblTenKCInformation, lblMoneyKCInformation, lblTenLoaiChiInformation, lblDateKCInformation;
                findAllView:
                {
                    lblMaKCInformation = dialog.findViewById(R.id.lblMaKCInformation);
                    lblTenKCInformation = dialog.findViewById(R.id.lblTenKCInformation);
                    lblMoneyKCInformation = dialog.findViewById(R.id.lblMoneyKCInformation);
                    lblTenLoaiChiInformation = dialog.findViewById(R.id.lblTenLoaiChiInformation);
                    lblDateKCInformation = dialog.findViewById(R.id.lblDateKCInformation);
                }
                setText:
                {
                    lblMaKCInformation.setText(spend.getMaKC());
                    lblTenKCInformation.setText(spend.getTenKC());
                    lblMoneyKCInformation.setText(String.valueOf(spend.getMoney()));
                    lblTenLoaiChiInformation.setText(spend.getLoaiChi().getTenLC());
                    lblDateKCInformation.setText(spend.getDate());
                }
                dialog.show();
            }
        });
    }

    private void setImgDeleteIncome(final KhoanChi spend, final Context context) {
        imgDeleteSpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa khoản thu " + spend.getMaKC() + " ?");
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
                        spendDAO.deleteSpend(spend.getMaKC());
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
