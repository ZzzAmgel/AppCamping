package com.example.appcamping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth vAuth;
    DatabaseReference vDatabase;
    private EditText vNombre;
    private EditText vPassword;
    private EditText vCorreo;
    private EditText vCPassword;
    private Button vRegistrarse;
    //VARIABLES DE REGISTROS
    private  String nombre = "";
    private  String password = "";
    private  String correo = "";
    private  String cpassword = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vAuth = FirebaseAuth.getInstance();


        vDatabase = FirebaseDatabase.getInstance().getReference();
        vNombre = (EditText) findViewById(R.id.editNombre);
        vPassword = (EditText) findViewById(R.id.editPassword);
        vCorreo = (EditText) findViewById(R.id.editCorreo);
        vCPassword = (EditText) findViewById(R.id.ConfirmPassword);
        vRegistrarse = (Button) findViewById(R.id.Registrarme);

        vRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = vNombre.getText().toString();
                password = vPassword.getText().toString();
                correo = vCorreo.getText().toString();
                cpassword = vCPassword.getText().toString();


                if(!nombre.isEmpty() && !password.isEmpty() && !correo.isEmpty() && password.equals(cpassword)){
                    if(password.length() >= 6) {

                            registerUser();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "La contraseña debe tener al menos 6 dígitos y ser igual", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Alguno de los campos esta vacio o no coinciden las contraseñas", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void registerUser(){
        vAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task1) {
                if(task1.isSuccessful()){


                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre",nombre);
                    map.put("correo",correo);
                    map.put("password",password);


                    String id = vAuth.getCurrentUser().getUid();

                    vDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, MainActivity.class));

                            }
                            else{
                                Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(MainActivity.this, IniciadoActivity.class);
                                startActivity(home);
                            }



                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Algo salió mal :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void Registro(View view) {
        Intent registro = new Intent(this, IniciadoActivity.class);
        startActivity(registro);
    }
    public void IniciarSesion(View view) {
        Intent iniciarsesion = new Intent(this, LoginActivity.class);
        startActivity(iniciarsesion);
    }



    @Override
    protected void onStart() {
        super.onStart();



        if (vAuth.getCurrentUser() != null) {

                startActivity(new Intent(MainActivity.this, IniciadoActivity.class));
                finish();
            }
        }
}
