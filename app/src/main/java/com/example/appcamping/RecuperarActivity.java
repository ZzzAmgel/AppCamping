package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarActivity extends AppCompatActivity {

    private EditText vRecEmail;
    private Button vButtonRec;

    private String email = "";
    private FirebaseAuth vAuth;
    private ProgressDialog vLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        vRecEmail = (EditText) findViewById(R.id.editEmailRec);
        vButtonRec = (Button) findViewById(R.id.buttonRestablecer);
        vAuth = FirebaseAuth.getInstance();
        vLoading = new ProgressDialog(this);

        vButtonRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                email = vRecEmail.getText().toString();
                
                if(!email.isEmpty()){
                    vLoading.setMessage("Espere un momento...");
                    vLoading.setCanceledOnTouchOutside(false);
                    vLoading.show();
                    resetPassword();
                }
                else{
                    Toast.makeText(RecuperarActivity.this, "Correo electronico erroneo", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void VolverInicio(View view) {
        Intent volverinicio = new Intent(RecuperarActivity.this, LoginActivity.class);
        startActivity(volverinicio);
    }

    private void resetPassword(){
        vAuth.setLanguageCode("es");
        vAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(RecuperarActivity.this, "Se ha enviado un correo para cambiar la contraseña", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(RecuperarActivity.this, "No se pudo realizar esta operación", Toast.LENGTH_SHORT).show();
                }
                vLoading.dismiss();
            }
        });
    }
}
