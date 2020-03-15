package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Perfil extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;
    FirebaseAuth vAuth;
    private String email;
    private ProgressDialog vLoading;
    private Button mStoragebtn;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        vAuth = FirebaseAuth.getInstance();

        mStoragebtn = (Button) findViewById(R.id.btnSubirImagen);
        mStorage = FirebaseStorage.getInstance().getReference();
        mStoragebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            Uri uri = data.getData();

            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Perfil.this, "Se ha subido correctamente la foto", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    private void resetPassword(View view){
        vLoading = new ProgressDialog(this);
        vAuth.setLanguageCode("es");
        email = vAuth.getCurrentUser().getEmail();
        vAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Perfil.this, "Se ha enviado un correo para cambiar la contraseña", Toast.LENGTH_SHORT).show();
                    vLoading.setMessage("Espere un momento...");
                    vLoading.setCanceledOnTouchOutside(false);
                    vLoading.show();

                }
                else{
                    Toast.makeText(Perfil.this, "No se pudo realizar esta operación", Toast.LENGTH_SHORT).show();
                }
                vLoading.dismiss();
            }
        });
    }

    public void CambiarPass(View view){
        vLoading = new ProgressDialog(this);
        vAuth.setLanguageCode("es");
        email = vAuth.getCurrentUser().getEmail();
        vAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Perfil.this, "Se ha enviado un correo para cambiar la contraseña", Toast.LENGTH_SHORT).show();
                    vLoading.setMessage("Espere un momento...");
                    vLoading.setCanceledOnTouchOutside(false);
                    vLoading.show();

                }
                else{
                    Toast.makeText(Perfil.this, "No se pudo realizar esta operación", Toast.LENGTH_SHORT).show();
                }
                vLoading.dismiss();
            }
        });
    }

    public void verificarEmail(View view){

        /*
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, ActionCodeSettings.newBuilder().build())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Perfil.this, "Se ha enviado un correo a su dirección", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                   */
        FirebaseUser user = vAuth.getCurrentUser();

        if(user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Perfil.this, "Se ha enviado un mensaje a su dirección de correo", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String error = task.getException().getMessage();
                        Toast.makeText(Perfil.this, "Error en la verificación", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void CloseSesion(View view){
        vAuth.signOut();
        startActivity(new Intent(Perfil.this, MainActivity.class));
        finish();
    }

}
