package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

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

    FirebaseAuth vAuth;
    DatabaseReference vDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        vAuth = FirebaseAuth.getInstance();
        vDatabase = FirebaseDatabase.getInstance().getReference();

        vNombre = findViewById(R.id.editNombre);
        vPassword = findViewById(R.id.editPassword);
        vCorreo = findViewById(R.id.editCorreo);
        vCPassword = findViewById(R.id.ConfirmPassword);
        vRegistrarse = findViewById(R.id.Registrarme);

        vRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = vNombre.getText().toString();
                password = vPassword.getText().toString();
                correo = vCorreo.getText().toString();
                cpassword = vCPassword.getText().toString();

                if(!nombre.isEmpty() && !password.isEmpty() && !correo.isEmpty()){
                    if(password.length() >= 6) {
                        if(vCPassword != vCPassword){
                            Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            registerUser();
                        }
                    }
                     else {
                        Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 6 dígitos y estar igual", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegistroActivity.this, "Alguno de los campos esta vacio", Toast.LENGTH_SHORT).show();
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
                                startActivity(new Intent(RegistroActivity.this, MainActivity.class));

                            }
                            else{

                                Toast.makeText(RegistroActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(RegistroActivity.this, IniciadoActivity.class);

                                startActivity(home);
                            }



                        }
                    });
                }
                else {
                    Toast.makeText(RegistroActivity.this, "Algo salió mal :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Registro(View view) {
        Intent registro = new Intent(this, IniciadoActivity.class);
        startActivity(registro);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (vAuth.getCurrentUser() != null) {
            startActivity(new Intent(RegistroActivity.this, IniciadoActivity.class));
            finish();

        }
    }
}
