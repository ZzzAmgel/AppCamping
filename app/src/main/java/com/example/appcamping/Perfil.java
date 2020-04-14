package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Perfil extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;
    FirebaseAuth vAuth;
    private String email;
    private ProgressDialog vLoading;
    public Uri imguri;
    Button ch,up;
    ImageView img;
    TextView correovisible;

    //private Button mStoragebtn;
    //private StorageReference mStorage;
    private StorageReference mStorageRef;
    private String emailuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        View b = findViewById(R.id.btnConfirmarSubida);
        b.setVisibility(View.GONE);
        mStorageRef = FirebaseStorage.getInstance().getReference("fotos");
        vAuth = FirebaseAuth.getInstance();

        emailuser = vAuth.getCurrentUser().getEmail();
        correovisible= findViewById(R.id.textView3);
        correovisible.setText(emailuser);

        ch= findViewById(R.id.btnSubirImagen);
        up= findViewById(R.id.btnConfirmarSubida);
        img= findViewById(R.id.imgview);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filechooser();

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fileuploader();
            }
        });


    }
    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void Fileuploader(){
        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        FirebaseStorage downloadUrl = mStorageRef.getStorage();
                        Toast.makeText(Perfil.this, "La imagen se ha subido correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }

    private void Filechooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
        View b = findViewById(R.id.btnConfirmarSubida);
        b.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            imguri=data.getData();
            img.setImageURI(imguri);

        }
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

    public void ContactarLink(View view){
        Intent contactarlink = new Intent(Perfil.this, ContactoActivity.class);
        startActivity(contactarlink);
    }


}
