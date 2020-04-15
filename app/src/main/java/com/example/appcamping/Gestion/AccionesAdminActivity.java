package com.example.appcamping.Gestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appcamping.EnviarEventos;
import com.example.appcamping.IniciadoActivity;
import com.example.appcamping.MultimediaActivity;
import com.example.appcamping.R;
import com.example.appcamping.ReservasActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccionesAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acciones_admin);

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
                        startActivity(new Intent(getApplicationContext(), MultimediaActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        //---------------------------NAVIGATION VIEW ----------------------------------------
    }

    public void CambiarFechaAdmin(View view) {
        Intent cambiarfecha = new Intent(AccionesAdminActivity.this, AddFechasTemporada.class);
        startActivity(cambiarfecha);
    }

    public void EnviarEventosAdmin(View view) {
        Intent enviareventos = new Intent(AccionesAdminActivity.this, SendEventos.class);
        startActivity(enviareventos);
    }

    public void VolverAInicio(View view) {
        Intent volverinicio = new Intent(AccionesAdminActivity.this, IniciadoActivity.class);
        startActivity(volverinicio);
    }

    public void GestionGastos(View view) {
        Intent gestiongastos = new Intent(AccionesAdminActivity.this, EnviarMostrarGastos.class);
        startActivity(gestiongastos);
    }

    public void GestionReservas(View view) {
        Intent gestionreservas = new Intent(AccionesAdminActivity.this, MostrarReservas.class);
        startActivity(gestionreservas);
    }
    public void SendImages(View view){
        Intent sendimages = new Intent(AccionesAdminActivity.this, SendImages.class);
        startActivity(sendimages);
    }
}
