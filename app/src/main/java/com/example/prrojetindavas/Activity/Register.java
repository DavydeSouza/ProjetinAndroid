package com.example.prrojetindavas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.prrojetindavas.Model.UserModel;
import com.example.prrojetindavas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText edit_nomeR;
    private EditText edit_sNomeR;
    private EditText sNomeR;
    private EditText edit_emailR;
    private EditText edit_passwordR;
    private EditText edit_CpasswordR;
    private Button btn_cregister;
    private Button btn_loginregister;
    private CheckBox check_passwordR;
    private ProgressBar loding_loginR;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        edit_nomeR = findViewById(R.id.edit_nomeR);
        edit_sNomeR = findViewById(R.id.edit_sNomeR);
        edit_emailR = findViewById(R.id.edit_emailR);
        edit_passwordR = findViewById(R.id.edit_passwordR);
        edit_CpasswordR = findViewById(R.id.edit_CpasswordR);
        check_passwordR = findViewById(R.id.check_passwordR);
        btn_cregister = findViewById(R.id.btn_cregister);
        btn_loginregister = findViewById(R.id.btn_loginregister);
        loding_loginR = findViewById(R.id.loding_loginR);

        check_passwordR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    edit_passwordR.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edit_CpasswordR.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edit_passwordR.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edit_CpasswordR.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        btn_cregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel userModel = new UserModel();

                userModel.setNome(edit_nomeR.getText().toString());
                userModel.setSobrenome(edit_sNomeR.getText().toString());
                userModel.setEmail(edit_emailR.getText().toString());
                String senha = edit_passwordR.getText().toString();
                String cSenha = edit_CpasswordR.getText().toString();

                if(!TextUtils.isEmpty(userModel.getNome()) || !TextUtils.isEmpty(userModel.getSobrenome()) || !TextUtils.isEmpty(userModel.getEmail()) ||!TextUtils.isEmpty(senha) ||!TextUtils.isEmpty(cSenha)){
                    if(senha.equals(cSenha)){
                        loding_loginR.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(userModel.getEmail(),senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    userModel.setId(mAuth.getUid());
                                    userModel.Save();
                                    abrirTelaPrincipal();
                                }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(Register.this,""+ error, Toast.LENGTH_SHORT).show();
                                }
                                loding_loginR.setVisibility(View.INVISIBLE);
                            }
                        });

                    }else{
                        Toast.makeText(Register.this,"A senhas devem ser compativeis!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_loginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}