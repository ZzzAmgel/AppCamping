package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appcamping.Gestion.AccionesAdminActivity;
import com.example.appcamping.adapters.ImageAdapter;
import com.example.appcamping.models.Upload;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IniciadoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button vCerrarSesion;
    private FirebaseAuth vAuth;
    private  String admin = "";
    private  String adminbueno = "1pVoigesVeQEFqvdbGV2Xx2xD2W2";
    private List<Upload> mUploads;
    private DatabaseReference mDatabaseRef;
    private ImageAdapter mAdapter;
    private int[] mImages = new int[]{
      R.drawable.campingelcanarportada, R.drawable.campingmap, R.drawable.imagenaereapiscina, R.drawable.mapainteractivo, R.drawable.casayflores, R.drawable.aereatotal
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciado);

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });



        mRecyclerView = findViewById(R.id.recycler_view1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                    Collections.reverse(mUploads);
                }

                mAdapter = new ImageAdapter(IniciadoActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(IniciadoActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressCircle.setVisibility(View.INVISIBLE);
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


        View y = findViewById(R.id.buttonInicio);
        y.setVisibility(View.GONE);

        //AQUI MOSTRAMOS LA UID DEL USUARIO Y COMPROBAMOS SI ES IGUAL PARA ASÍ MOSTRAR O NO EL BOTON
        //Toast.makeText(this, ""+vAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        admin = vAuth.getCurrentUser().getEmail();
        if(admin.equals("info@riorural.com") || admin.equals("elangelmiranda@gmail.com") || admin.equals("amiranda3@alu.ucam.edu")){                          //comprueba que el usuario es administrador y oculta el boton
            View b = findViewById(R.id.buttonInicio);
            b.setVisibility(View.VISIBLE);
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
