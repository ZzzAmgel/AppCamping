package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Registro(View view) {
        Intent registro = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(registro);
    }
}
