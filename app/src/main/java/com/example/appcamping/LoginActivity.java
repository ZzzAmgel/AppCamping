package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText vEmail;
    private EditText vPassword;
    private Button vBotonIniciar;
    private GoogleApiClient googleApiClient;

    //datos de inicio
    private String email = "";
    private String password = "";

    private FirebaseAuth vAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth., gso).build();

        vAuth = FirebaseAuth.getInstance();

        vEmail = (EditText) findViewById(R.id.Correo);
        vPassword = (EditText) findViewById(R.id.Password);
        vBotonIniciar = (Button) findViewById(R.id.buttonIniciar);

        vBotonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = vEmail.getText().toString();                            //PASAMOS LOS DATOS A STRING Y LOS ALMACENAMOS EN ESAS VARIABLES
                password = vPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                        Toast.makeText(LoginActivity.this, "Usuario", Toast.LENGTH_SHORT).show();
                        IniciodeSesion();

                }
                    else{
                    Toast.makeText(LoginActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void Registro(View view) {
        Intent registro = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(registro);
    }
    public void RecupearPass(View view) {
        Intent recuperar = new Intent(LoginActivity.this, RecuperarActivity.class);
        startActivity(recuperar);
    }



    private  void IniciodeSesion(){
        vAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, IniciadoActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "No se ha podido iniciar sesion, comprueba los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    }

