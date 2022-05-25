package com.example.prrojetindavas.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prrojetindavas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaymentsFragment extends Fragment {

    FloatingActionButton btnAddpayments;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payments, container, false);

        return view;
    }
}