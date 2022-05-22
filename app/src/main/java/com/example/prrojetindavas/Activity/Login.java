package com.example.prrojetindavas.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.prrojetindavas.MainActivity;
import com.example.prrojetindavas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText edt_email;
    private EditText edt_password;
    private CheckBox check_password;
    private Button btn_login;
    private Button btn_registrar;
    private FirebaseAuth mAuth;
    private ProgressBar loding_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        edt_email = findViewById(R.id.edit_email);
        edt_password = findViewById(R.id.edit_password);
        check_password = findViewById(R.id.check_password);
        btn_login = findViewById(R.id.btn_login);
        btn_registrar = findViewById(R.id.btn_registrar);
        loding_login = findViewById(R.id.loding_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail =edt_email.getText().toString();
                String loginPassword =edt_password.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginPassword)){
                    loding_login.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail,loginPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                abrirTelaPrincipal();
                                            }
                                        },2500);
                                    }else{
                                        String error = task.getException().getMessage();
                                        Toast.makeText(Login.this,""+ error, Toast.LENGTH_SHORT).show();
                                        loding_login.setVisibility(View.INVISIBLE);
                                    }

                                }
                            });
                }
            }
        });
        check_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser userAtual = FirebaseAuth.getInstance().getCurrentUser();
        if(userAtual != null){
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}