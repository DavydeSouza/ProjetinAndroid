package com.example.prrojetindavas.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.prrojetindavas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaymentsFragment extends Fragment {

    FloatingActionButton btnAddpayments,btnAddpayment1,btnAddpayment2;
    Animation fabOpen, fabClose, rotation, rotationback;
    boolean isOpen =false;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payments, container, false);
        btnAddpayments =(FloatingActionButton) view.findViewById(R.id.btnAddpayments);
      //  btnAddpayment1 =(FloatingActionButton) view.findViewById(R.id.btnAddpayments1);
        btnAddpayment2 =(FloatingActionButton) view.findViewById(R.id.btnAddpayments2);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_animation_open);
        fabClose= AnimationUtils.loadAnimation(getActivity(), R.anim.fab_animation_close);
        rotation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
        rotationback =AnimationUtils.loadAnimation(getActivity(),R.anim.rotationback);

        btnAddpayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animeteFab();
            }
        });
      /*  btnAddpayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "ADD Pagamentos", Toast.LENGTH_SHORT).show();
            }
        });*/
        btnAddpayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Sei la vei", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
    private void animeteFab(){
        if(isOpen){
            btnAddpayments.startAnimation(rotation);
            //btnAddpayment1.startAnimation(fabClose);
            btnAddpayment2.startAnimation(fabClose);
            //btnAddpayment1.setClickable(false);
            btnAddpayment2.setClickable(false);
            isOpen =false;
        }else{
            btnAddpayments.startAnimation(rotationback);
            //btnAddpayment1.startAnimation(fabOpen);
            btnAddpayment2.startAnimation(fabOpen);
            //btnAddpayment1.setClickable(true);
            btnAddpayment2.setClickable(true);
            //btnAddpayment1.setVisibility(View.VISIBLE);
            btnAddpayment2.setVisibility(View.VISIBLE);

            isOpen =true;
        }
    }
}