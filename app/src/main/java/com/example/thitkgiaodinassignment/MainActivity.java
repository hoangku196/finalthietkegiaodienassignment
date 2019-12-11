package com.example.thitkgiaodinassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.thitkgiaodinassignment.dao.IncomeDAO;
import com.example.thitkgiaodinassignment.fragment.income.FragmentIncomeMain;
import com.example.thitkgiaodinassignment.fragment.result.FragmentResultMain;
import com.example.thitkgiaodinassignment.fragment.spend.FragmentSpendMain;
import com.example.thitkgiaodinassignment.object.income.KhoanThu;
import com.example.thitkgiaodinassignment.object.income.LoaiThu;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        setUpActionBar();
    }


    private void setUpActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý thu chi");
    }

    //Create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        switch (item.getItemId()) {
            case R.id.mn_income:
                actionBar.setTitle("Khoản thu");
                fragment = FragmentIncomeMain.newInstance(fragmentManager);
                fragmentTransaction.addToBackStack("Add Income Fragment");
                break;
            case R.id.mn_spend:
                actionBar.setTitle("Khoản chi");
                fragment = FragmentSpendMain.newInstance(fragmentManager);
                fragmentTransaction.addToBackStack("Show Income Fragment");
                break;
            case R.id.mn_result:
                actionBar.setTitle("Thống kê");
                fragment = FragmentResultMain.newInstance();
                fragmentTransaction.addToBackStack("Result Fragment");
                break;
            case R.id.mn_introduce:
                actionBar.setTitle("Giới thiệu");
                break;
            case R.id.mn_exit:
                System.exit(0);
                break;
        }
        if (fragment != null) {
            fragmentTransaction.replace(R.id.mainFrame, fragment);
            fragmentTransaction.commit();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
