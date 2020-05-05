package com.example.appcamping.Gestion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appcamping.IniciadoActivity;
import com.example.appcamping.MultimediaActivity;
import com.example.appcamping.R;
import com.example.appcamping.ReservasActivity;
import com.example.appcamping.models.Gastos;
import com.example.appcamping.models.Module2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class EnviarMostrarGastos extends AppCompatActivity {

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    ListView listview;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Module2 module2;
    private EditText mEditPrecio;
    private EditText mEditNombreGasto;
    private DatabaseReference mDatabaseGastos;
    private Button mEnviarGastos;
    private String mFechaGasto;
    //----------------MOSTRAR GASTOS
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mostrar_gastos);

        mFechaGasto = new Date().toString();
        mEditNombreGasto = findViewById(R.id.editNombreGasto);
        mEditPrecio = findViewById(R.id.editPrecio);
        mDatabaseGastos = FirebaseDatabase.getInstance().getReference();
        mEnviarGastos = findViewById(R.id.btnEnviarGastos);
        mEnviarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mDatabaseGastos.child("Gastos").push().getKey();
                String nombregasto = mEditNombreGasto.getText().toString();
                String precio = mEditPrecio.getText().toString();
                String fechagasto = mFechaGasto;

                if(nombregasto.isEmpty() || precio.isEmpty()){
                    Toast.makeText(EnviarMostrarGastos.this, "Algún campo esta vacio", Toast.LENGTH_SHORT).show();
                }

                mDatabaseGastos.child("Gastos").child(key).child("NombreGasto").setValue(nombregasto);
                mDatabaseGastos.child("Gastos").child(key).child("Precio").setValue(precio);
                mDatabaseGastos.child("Gastos").child(key).child("FechaGasto").setValue(fechagasto);
                createNotificationChannel();
                crearNotificacion();



            }
        });

        //-----------------MOSTRAR GASTOS--------------
        databaseReference= FirebaseDatabase.getInstance().getReference("Gastos");
        listview= findViewById(R.id.listMostrarGastos);
        arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value=dataSnapshot.getValue(Gastos.class).toString();
                arrayList.add(value);
                Collections.reverse(arrayList);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //---------------------------NAVIGATION VIEW ----------------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.reservas:
                        startActivity(new Intent(getApplicationContext(), ReservasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), IniciadoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.multimedia:
                        startActivity(new Intent(getApplicationContext(), MultimediaActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        //---------------------------NAVIGATION VIEW ----------------------------------------

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
        builder.setContentTitle("Se ha almacenado correctamente:");
        builder.setContentText("Datos del gasto:\n "+mEditNombreGasto.getText().toString()+"\n "+mEditPrecio.getText().toString() +"\n "+mFechaGasto);
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.GREEN, 1000, 1000);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }

}
