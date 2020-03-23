package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class EnviarEventos extends AppCompatActivity {
    //NOTIFICACION VAR
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    private Button btnEnviarNotificacion;
    private PendingIntent pendingIntent;

    //SUBIR IMAGNES VAR
    public Uri imguri;
    Button ch,up;
    ImageView img;
    private StorageReference mStorageRef;
    //SUBIR EVENTOS ADMIN
    private EditText mEditTextTitulo;
    private EditText mEditTextMensaje;
    private EditText mFecha;
    private DatabaseReference mDatabase;
    private Button EnviarRTB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_eventos);


        //mFecha = (Date) findViewById(R.id.editFecha);


        //----------------------------------- SUBIR FOTO ONCREATE

        mStorageRef = FirebaseStorage.getInstance().getReference("fotos");
        View b = findViewById(R.id.btnSendFoto);
        b.setVisibility(View.GONE);

        ch=(Button)findViewById(R.id.btnSubirFoto);
        up=(Button)findViewById(R.id.btnSendFoto);
        img=(ImageView)findViewById(R.id.imgPreview);
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
        //----------------------------------- FIN SUBIR FOTOS ADMIN-----------------------------------
        //----------------------------------- INICIO ENVIAR DBRT   -----------------------------------

        mEditTextTitulo = (EditText) findViewById(R.id.editTitulo);
        mEditTextMensaje = (EditText) findViewById(R.id.editDescripcion);
        mFecha = (EditText) findViewById(R.id.editFecha);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        EnviarRTB = findViewById(R.id.btnEnviar);
        EnviarRTB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createNotificationChannel();
                crearNotificacion();
                String key = mDatabase.child("Eventos").push().getKey();
                String titulo = mEditTextTitulo.getText().toString();
                String descripcion = mEditTextMensaje.getText().toString();
                String fecha = mFecha.getText().toString();

                mDatabase.child("Eventos").child(key).child("Titulo").setValue(titulo);
                mDatabase.child("Eventos").child(key).child("Descripción").setValue(descripcion);
                mDatabase.child("Eventos").child(key).child("Fecha").setValue(fecha);
            }
        });


        //----------------------------------- FIN ENVIAR DBRT      -----------------------------------

    }

    //----------------------------------- FIN ON CREATE     -----------------------------------
    //----------------------------------- INICIO ENVIAR NOTIFICACIONES -----------------------------------


    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notificación";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void crearNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.rioruralicon);
        builder.setContentTitle("Nueva publicación realizada:");
        builder.setContentText("Para publicar mediante notificacion acceder a Firebase Console");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.GREEN, 1000, 1000);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

            //-----------------------------------CREAR NOTIFICACIONES FIN-----------------------------------
            //-----------------------------------INICIO SUBIR FOTOS ADMIN-----------------------------------

    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader(){
        StorageReference Ref= mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = mStorageRef.getStorage();
                        Toast.makeText(EnviarEventos.this, "La imagen se ha subido correctamente", Toast.LENGTH_SHORT).show();
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
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
        View b = findViewById(R.id.btnSendFoto);
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
    //----------------------------------- FIN SUBIR FOTO ADMIN -----------------------------------
    //----------------------------------- INICIO ENVIAR DBRT   -----------------------------------




    //----------------------------------- FIN ENVIAR DBRT      -----------------------------------
}