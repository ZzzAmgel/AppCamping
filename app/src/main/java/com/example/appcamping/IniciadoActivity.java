package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcamping.Gestion.AccionesAdminActivity;
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

        vAuth = FirebaseAuth.getInstance();
        vCerrarSesion = (Button) findViewById(R.id.buttonCerrarSesion);



        //AQUI MOSTRAMOS LA UID DEL USUARIO Y COMPROBAMOS SI ES IGUAL PARA ASÍ MOSTRAR O NO EL BOTON
        Toast.makeText(this, ""+vAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        admin = vAuth.getCurrentUser().getEmail();
        if(admin.equals("hola@hola.com")){                          //comprueba que el usuario es administrador y oculta el boton
            //View b = findViewById(R.id.buttonReservas);
            //b.setVisibility(View.GONE);
        }

        vCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               vAuth.signOut();
               startActivity(new Intent(IniciadoActivity.this, MainActivity.class));
               finish();
            }
        });
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
        Intent eventoslink = new Intent(IniciadoActivity.this, ShowData.class);
        startActivity(eventoslink);
    }

    public void GaleriaLink(View view){
        //Intent galerialink = new Intent(IniciadoActivity.this, Galeria.class);
        //startActivity(galerialink);
    }

    public void ContactarLink(View view){
        Intent contactarlink = new Intent(IniciadoActivity.this, ContactoActivity.class);
        startActivity(contactarlink);
    }
}
