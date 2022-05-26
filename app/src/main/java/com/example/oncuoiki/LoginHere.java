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

public class LoginHere extends AppCompatActivity {
    EditText etLoginEmail;
    EditText etLoginPassword;
    TextView tvRegisterHere;
    Button btn_Login;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_here);

        etLoginEmail = findViewById(R.id.edtLoginEmail);
        etLoginPassword = findViewById(R.id.edtLoginPassword);
        tvRegisterHere = findViewById(R.id.tv_RegisterHere);
        btn_Login = findViewById(R.id.btnLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_Login.setOnClickListener(view->{
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(LoginHere.this,RegisterHere.class));
        });
    }

    private void loginUser() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email cannot be emty");
            etLoginEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be emty");
            etLoginPassword.requestFocus();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginHere.this, "User Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginHere.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginHere.this, "Login Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}