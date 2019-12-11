package com.example.thitkgiaodinassignment.fragment.introduce;


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
public class FragmentIntroduceMain extends Fragment {


    public FragmentIntroduceMain() {
        // Required empty public constructor
    }


    public static FragmentIntroduceMain newInstance() {

        Bundle args = new Bundle();

        FragmentIntroduceMain fragment = new FragmentIntroduceMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce_main, container, false);

        return view;
    }

}
