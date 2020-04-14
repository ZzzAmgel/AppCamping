package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.appcamping.Gestion.AccionesAdminActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MultimediaActivity extends AppCompatActivity {

    private Button vCerrarSesion;
    private FirebaseAuth vAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        vAuth = FirebaseAuth.getInstance();
        vCerrarSesion = findViewById(R.id.buttonCerrarSesion);


        vCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vAuth.signOut();
                startActivity(new Intent(MultimediaActivity.this, MainActivity.class));
                finish();
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
                        startActivity(new Intent(getApplicationContext(),ReservasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),IniciadoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.multimedia:
                        return true;

                }
                return false;
            }
        });
        //---------------------------NAVIGATION VIEW ----------------------------------------

    }

    public void Mapa(View view) {
        Intent mapa = new Intent(MultimediaActivity.this, ReservasActivity.class);
        startActivity(mapa);

    }

    public void Perfil(View view) {
        Intent perfil = new Intent(MultimediaActivity.this, Perfil.class);
        startActivity(perfil);

    }

    public void Enviar(View view) {
        Intent enviar = new Intent(MultimediaActivity.this, AccionesAdminActivity.class);
        startActivity(enviar);

    }

    public void MapaInteres(View view){
        Intent mapainteres = new Intent(MultimediaActivity.this, MapsInteresActivity.class);
        startActivity(mapainteres);
    }

    public void EventosLink(View view){
        Intent eventoslink = new Intent(MultimediaActivity.this, ImagesActivity.class);
        startActivity(eventoslink);
    }

    public void GaleriaLink(View view){
        Intent galerialink = new Intent(MultimediaActivity.this, ImagesActivity1.class);
        startActivity(galerialink);
    }

    public void ContactarLink(View view){
        Intent contactarlink = new Intent(MultimediaActivity.this, ContactoActivity.class);
        startActivity(contactarlink);
    }

    public void MapaReservas(View view){
        Intent mapainteres = new Intent(MultimediaActivity.this, MapsActivity.class);
        startActivity(mapainteres);
    }
}
