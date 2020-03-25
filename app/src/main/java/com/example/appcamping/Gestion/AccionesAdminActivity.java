package com.example.appcamping.Gestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appcamping.EnviarEventos;
import com.example.appcamping.IniciadoActivity;
import com.example.appcamping.R;
import com.example.appcamping.ReservasActivity;

public class AccionesAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acciones_admin);
    }

    public void CambiarFechaAdmin(View view) {
        Intent cambiarfecha = new Intent(AccionesAdminActivity.this, AddFechasTemporada.class);
        startActivity(cambiarfecha);
    }

    public void EnviarEventosAdmin(View view) {
        Intent enviareventos = new Intent(AccionesAdminActivity.this, EnviarEventos.class);
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
}
