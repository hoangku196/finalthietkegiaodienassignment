package com.example.thitkgiaodinassignment.adapter.income;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.Main2Activity;
import com.example.thitkgiaodinassignment.R;
import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

import java.util.List;

public class TypeIncomeAdapter extends BaseAdapter {

    private List<LoaiThu> listLoaiThu;
    private IncomeDAO incomeDAO;
    private TextView lblMaLT, lblTenLT;
    private ImageView imgEdit, imgDelete;

    public TypeIncomeAdapter(List<LoaiThu> listLoaiThu) {
        this.listLoaiThu = listLoaiThu;
    }

    @Override
    public int getCount() {
        return listLoaiThu.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiThu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_typeincome_details, parent, false);
        LoaiThu loaiThu = (LoaiThu) getItem(position);
        incomeDAO = new IncomeDAO(parent.getContext());
        findAllViewById(view, loaiThu, parent.getContext());

        return view;
    }

    private void findAllViewById(View view, LoaiThu loaiThu, Context context) {
        lblMaLT = view.findViewById(R.id.lblMaLT);
        setLblMaLT(loaiThu.getMaLT());
        lblTenLT = view.findViewById(R.id.lblTenLT);
        setLblTenLT(loaiThu.getTenLT());
        imgEdit = view.findViewById(R.id.imgEdtLT);
        setImgEdit(loaiThu, context);

        imgDelete = view.findViewById(R.id.imgDeleteLT);
        setImgDelete(loaiThu, context);
    }

    private void setImgEdit(final LoaiThu loaiThu, final Context context) {
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("layout", "layout_edit_typeincome");
                bundle.putString("key_maLT", loaiThu.getMaLT());
                bundle.putString("key_tenLT", loaiThu.getTenLT());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void setImgDelete(final LoaiThu loaiThu, final Context context) {
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incomeDAO.checkLoaiThu(loaiThu)) {
                    boolean check = incomeDAO.deleteTypeIncome(loaiThu);
                    if (check) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Xóa các khoản thu có loại thu này trước", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLblTenLT(String tenLT) {
        lblTenLT.setText(tenLT);
    }

    private void setLblMaLT(String maLT) {
        lblMaLT.setText(maLT);
    }
}
