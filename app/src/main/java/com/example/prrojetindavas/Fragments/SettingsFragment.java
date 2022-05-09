package com.example.prrojetindavas.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.prrojetindavas.Activity.Login;
import com.example.prrojetindavas.MainActivity;
import com.example.prrojetindavas.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    Button btn_logout;
    View view;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth =FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        btn_logout = (Button) view.findViewById(R.id.btn_exit);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getContext(), "Precionou", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}