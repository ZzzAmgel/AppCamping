package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {


    private EditText vEmail;
    private EditText vPassword;
    private Button vBotonIniciar;
    private GoogleApiClient googleApiClient;
    public static final int SING_IN_CODE = 777;
    private SignInButton signInButton;

    //datos de inicio
    private String email = "";
    private String password = "";

    private FirebaseAuth vAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        vAuth = FirebaseAuth.getInstance();

        vEmail = findViewById(R.id.Correo);
        vPassword = findViewById(R.id.Password);
        vBotonIniciar = findViewById(R.id.buttonIniciar);

        vBotonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = vEmail.getText().toString();                            //PASAMOS LOS DATOS A STRING Y LOS ALMACENAMOS EN ESAS VARIABLES
                password = vPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                        IniciodeSesion();

                }
                    else{
                    Toast.makeText(LoginActivity.this, "Algo salió mal, comprueba tu conexión", Toast.LENGTH_SHORT).show();
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

