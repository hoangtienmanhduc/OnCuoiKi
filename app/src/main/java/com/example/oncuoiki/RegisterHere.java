package com.example.oncuoiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterHere extends AppCompatActivity {
    EditText edtRegisterEmail;
    EditText edtRegisterPassword;
    Button btnRegister;
    TextView tv_LoginHere;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_here);

        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tv_LoginHere = findViewById(R.id.tv_LoginHere);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view->{
            createUser();
        });
        tv_LoginHere.setOnClickListener(view -> {
            startActivity(new Intent(RegisterHere.this,LoginHere.class));
        });

    }

    private void createUser() {
        String email = edtRegisterEmail.getText().toString();
        String password = edtRegisterPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            edtRegisterEmail.setError("Email khong duoc bo trong");
            edtRegisterEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            edtRegisterPassword.setError("Password khong duoc bo trong");
            edtRegisterPassword.requestFocus();
        }
        else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterHere.this, "User register", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterHere.this,LoginHere.class));
                    }
                    else {
                        Toast.makeText(RegisterHere.this, "Register Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}