package com.example.thitkgiaodinassignment.adapter.spend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.Main2Activity;
import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.dao.SpendDAO;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;
import com.example.thitkgiaodinassignment.object.spend.LoaiChi;

import java.util.List;

public class TypeSpendAdapter extends BaseAdapter {
    private List<LoaiChi> listLoaiChi;
    private SpendDAO spendDAO;
    private TextView lblMaLC, lblTenLC;
    private ImageView imgEdit, imgDelete;

    public TypeSpendAdapter(List<LoaiChi> listLoaiChi) {
        this.listLoaiChi = listLoaiChi;
    }

    @Override
    public int getCount() {
        return listLoaiChi.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_typespend_details, parent, false);
        LoaiChi loaiChi = (LoaiChi) getItem(position);
        spendDAO = new SpendDAO(parent.getContext());
        findAllViewById(view, loaiChi, parent.getContext());

        return view;
    }

    private void findAllViewById(View view, LoaiChi loaiChi, Context context) {
        lblMaLC = view.findViewById(R.id.lblMaLC);
        setLblMaLT(loaiChi.getMaLC());
        lblTenLC = view.findViewById(R.id.lblTenLC);
        setLblTenLT(loaiChi.getTenLC());
        imgEdit = view.findViewById(R.id.imgEdtLC);
        setImgEdit(loaiChi, context);

        imgDelete = view.findViewById(R.id.imgDeleteLC);
        setImgDelete(loaiChi, context);
    }

    private void setImgEdit(final LoaiChi loaiChi, final Context context) {
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("layout", "layout_edit_typespend");
                bundle.putString("key_maLC", loaiChi.getMaLC());
                bundle.putString("key_tenLC", loaiChi.getTenLC());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void setImgDelete(final LoaiChi loaiChi, final Context context) {
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spendDAO.checkLoaiChi(loaiChi)) {
                    boolean check = spendDAO.deleteTypeSpend(loaiChi);
                    if (check) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Xóa các khoản chi có loại chi này trước", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLblTenLT(String tenLC) {
        lblTenLC.setText(tenLC);
    }

    private void setLblMaLT(String maLC) {
        lblMaLC.setText(maLC);
    }
}
