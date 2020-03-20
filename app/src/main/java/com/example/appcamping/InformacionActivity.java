package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;


public class InformacionActivity extends AppCompatActivity {

    //NOTIFICACION VAR
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    FirebaseAuth vAuth;


    //PUBLICAR RESERVA BDRTB

    private DatabaseReference mDatabaseReserva;
    private EditText mNombreApellidos;
    private EditText mNumAdultos;
    private EditText mNumNinos;
    private EditText mFechaIn;
    private EditText mFechaFin;
    private EditText mNumTelefono;

    TextView markertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        markertxt=findViewById(R.id.marker);
        //COGEMOS LA ID PARA EL MAPA
        String title=getIntent().getStringExtra("title");
        markertxt.setText(title);
        vAuth = FirebaseAuth.getInstance();

        //------------------- ON CREATE SUBIR BDRTB --------------------

        mNombreApellidos = (EditText) findViewById(R.id.editNombre);
        mNumAdultos = (EditText) findViewById(R.id.editAdultos);
        mNumNinos = (EditText) findViewById(R.id.editNinos);
        mFechaIn = (EditText) findViewById(R.id.editFechaIn);
        mFechaFin = (EditText) findViewById(R.id.editFechaSalida);
        mNumTelefono = (EditText) findViewById(R.id.editTelefonoMovil);

        mDatabaseReserva = FirebaseDatabase.getInstance().getReference();
        //EnviarReserva = findViewById(R.id.btnEnviar);


    }


    //------------------- CREAR NOTIFICACIÓN PUSH --------------------



    public void EnviarReserva(View view){

                createNotificationChannel();
                crearNotificacion();
                String ky = mDatabaseReserva.child("Reservas").push().getKey();
                String nombre = mNombreApellidos.getText().toString();
                String numadultos = mNumAdultos.getText().toString();
                String numninos = mNumNinos.getText().toString();
                String fechainicio = mFechaIn.getText().toString();
                String fechafin = mFechaFin.getText().toString();
                String numerotelefono = mNumTelefono.getText().toString();
                String casa = markertxt.getText().toString();
                String direccioncorreo = vAuth.getCurrentUser().getEmail();


                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("Titulo").setValue(nombre);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("Correo").setValue(direccioncorreo);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("NumAdultos").setValue(numadultos);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("NumNinos").setValue(numninos);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("FechaInicio").setValue(fechainicio);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("FechaFin").setValue(fechafin);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("NumeroTelefono").setValue(numerotelefono);
                mDatabaseReserva.child("Reservas").child(ky).child("Reserva").child("Casa").setValue(casa);
            }

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
        builder.setContentTitle("Se ha realizado una reserva:");
        builder.setContentText("En breve recibirá una confirmación, sus datos son:"+mNombreApellidos+mNumTelefono+mFechaIn+mFechaFin+mNumAdultos+mNumNinos);
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.GREEN, 1000, 1000);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }


}
