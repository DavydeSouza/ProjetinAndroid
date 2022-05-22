package com.example.prrojetindavas.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prrojetindavas.Activity.Login;
import com.example.prrojetindavas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;

public class HomeFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView nameUser;
    View view;
    String UserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
       // RecuperaInfo();
    }

   public void RecuperaInfo(){
        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(UserID != null){
        nameUser =(TextView) getActivity().findViewById(R.id.nameUser);
        DocumentReference documentReference = db.collection("Usuarios").document(UserID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null);
                nameUser.setText(value.getString("sobrenome"));
                }
            });
        }else{
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        }
    }
}