package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcamping.Gestion.AccionesAdminActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class IniciadoActivity extends AppCompatActivity {


    private Button vCerrarSesion;
    private FirebaseAuth vAuth;
    private  String admin = "";
    private  String adminbueno = "1pVoigesVeQEFqvdbGV2Xx2xD2W2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciado);
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

        vAuth = FirebaseAuth.getInstance();
        vCerrarSesion = findViewById(R.id.buttonCerrarSesion);



        //AQUI MOSTRAMOS LA UID DEL USUARIO Y COMPROBAMOS SI ES IGUAL PARA ASÍ MOSTRAR O NO EL BOTON
        //Toast.makeText(this, ""+vAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        admin = vAuth.getCurrentUser().getEmail();
        if(admin.equals("hola@hola.com")){                          //comprueba que el usuario es administrador y oculta el boton
            //View b = findViewById(R.id.buttonReservas);
            //b.setVisibility(View.GONE);
        }

    }
    public void Mapa(View view) {
        Intent mapa = new Intent(IniciadoActivity.this, ReservasActivity.class);
        startActivity(mapa);

    }

    public void Perfil(View view) {
        Intent perfil = new Intent(IniciadoActivity.this, Perfil.class);
        startActivity(perfil);

    }

    public void Enviar(View view) {
        Intent enviar = new Intent(IniciadoActivity.this, AccionesAdminActivity.class);
        startActivity(enviar);

    }

    public void MapaInteres(View view){
        Intent mapainteres = new Intent(IniciadoActivity.this, MapsInteresActivity.class);
        startActivity(mapainteres);
    }

    public void EventosLink(View view){
        Intent eventoslink = new Intent(IniciadoActivity.this, ImagesActivity.class);
        startActivity(eventoslink);
    }

    public void GaleriaLink(View view){
        Intent galerialink = new Intent(IniciadoActivity.this, ImagesActivity1.class);
        startActivity(galerialink);
    }

    public void ContactarLink(View view){
        Intent contactarlink = new Intent(IniciadoActivity.this, ContactoActivity.class);
        startActivity(contactarlink);
    }
}
