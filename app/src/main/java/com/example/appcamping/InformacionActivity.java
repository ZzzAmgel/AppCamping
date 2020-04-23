package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;


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
    private int dia, mes, ano;
    private int dia1, mes1, ano1;
    private Button btnfechaentrada;
    private Button btnfechasalida;

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

        btnfechaentrada = (Button)findViewById(R.id.btnfechaentrada1);
        btnfechasalida = (Button)findViewById(R.id.btnfechasalida2);

        //------------------- ON CREATE SUBIR BDRTB --------------------

        mNombreApellidos = findViewById(R.id.editNombre);
        mNumAdultos = findViewById(R.id.editAdultos);
        mNumNinos = findViewById(R.id.editNinos);
        mFechaIn = findViewById(R.id.editFechaIn);
        mFechaFin = findViewById(R.id.editFechaSalida);
        mNumTelefono = findViewById(R.id.editTelefonoMovil);

        mDatabaseReserva = FirebaseDatabase.getInstance().getReference();
        //EnviarReserva = findViewById(R.id.btnEnviar);

        //---------------------------NAVIGATION VIEW ----------------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.reservas:
                        startActivity(new Intent(getApplicationContext(),ReservasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),IniciadoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.multimedia:
                        startActivity(new Intent(getApplicationContext(),MultimediaActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        //---------------------------NAVIGATION VIEW ----------------------------------------


    }




    //------------------- CREAR NOTIFICACIÓN PUSH --------------------

    public void Calendarioentrada1(View v){
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mFechaIn.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
                ,ano,mes,dia);
        datePickerDialog.show();


    }

    public void CalendarioSalida1(View v){
        if(v==btnfechasalida){
            final Calendar c= Calendar.getInstance();
            dia1=c.get(Calendar.DAY_OF_MONTH);
            mes1=c.get(Calendar.MONTH);
            ano1=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFechaFin.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
                    ,ano1,mes1,dia1);
            datePickerDialog.show();
        }

    }

    public void EnviarReserva(View view){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user.isEmailVerified();
        if(emailVerified == false) {
            Toast.makeText(this, "Debe verificar su dirección de correo electrónico para poder reservar", Toast.LENGTH_SHORT).show();
            Intent redireccionperfil = new Intent(InformacionActivity.this, Perfil.class);
            startActivity(redireccionperfil);
        }
        else {

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


            mDatabaseReserva.child("Reservas").child(ky).child("Titulo").setValue(nombre);
            mDatabaseReserva.child("Reservas").child(ky).child("Correo").setValue(direccioncorreo);
            mDatabaseReserva.child("Reservas").child(ky).child("NumAdultos").setValue(numadultos);
            mDatabaseReserva.child("Reservas").child(ky).child("NumNinos").setValue(numninos);
            mDatabaseReserva.child("Reservas").child(ky).child("FechaInicio").setValue(fechainicio);
            mDatabaseReserva.child("Reservas").child(ky).child("FechaFin").setValue(fechafin);
            mDatabaseReserva.child("Reservas").child(ky).child("NumeroTelefono").setValue(numerotelefono);
            mDatabaseReserva.child("Reservas").child(ky).child("Casa").setValue(casa);
                }
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
