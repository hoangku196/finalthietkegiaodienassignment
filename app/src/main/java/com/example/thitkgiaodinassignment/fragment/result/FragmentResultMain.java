package com.example.thitkgiaodinassignment.fragment.result;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thitkgiaodinassignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResultMain extends Fragment {

    public FragmentResultMain() {
        // Required empty public constructor
    }

    public static FragmentResultMain newInstance() {

        Bundle args = new Bundle();

        FragmentResultMain fragment = new FragmentResultMain();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_main,container,false);

        return view;
    }

}
