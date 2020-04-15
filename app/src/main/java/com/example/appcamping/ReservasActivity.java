package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservasActivity extends AppCompatActivity {

    FirebaseAuth vAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseReserva;
    private Button EnviarReserva;
    private EditText mNombreApellidos;
    private EditText mNumAdultos;
    private EditText mNumNinos;
    private EditText mFechaIn;
    private EditText mFechaFin;
    private EditText mNumTelefono;
    private EditText mDNI;
    private String mCasa;

    //COGER FECHAS

    private String valid_until;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);


        View a = findViewById(R.id.btnShowCasa);
        a.setVisibility(View.GONE);

        View c = findViewById(R.id.textParcelas);
        c.setVisibility(View.GONE);

        View I = findViewById(R.id.textParcelas);
        I.setVisibility(View.GONE);

        //---------------------------NAVIGATION VIEW ----------------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.reservas:
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



        //-----------------------------COGER FECHA

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Fechas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    valid_until = dataSnapshot.child("FechaTempAlta").getValue().toString();

                    SimpleDateFormat sdfBaja = new SimpleDateFormat("dd/MM/yyyy");
                    //SimpleDateFormat sdfAlta = new SimpleDateFormat("dd/MM/yyyy");
                    Date strTemporadaBaja = null;
                    //Date strTemporadaAlta = null;
                    try {
                        strTemporadaBaja = sdfBaja.parse(valid_until);
                        //strTemporadaAlta = sdfAlta.parse(valido_alta);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (new Date().before(strTemporadaBaja)) {
                        View j = findViewById(R.id.textInfoCasa);
                        j.setVisibility(View.GONE);



                        View l = findViewById(R.id.textInfoParcela);
                        l.setVisibility(View.VISIBLE);
                    }
                    else if (new Date().after(strTemporadaBaja)) {




                        View n = findViewById(R.id.textInfoParcela);
                        n.setVisibility(View.GONE);

                        View m = findViewById(R.id.textInfoCasa);
                        m.setVisibility(View.VISIBLE);



                        //------------------- ON CREATE SUBIR BDRTB --------------------
                    }

                    //Toast.makeText(ReservasActivity.this, ""+mFecha1, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        //-----------------------------COMPROBAR FECHAS Y OCULTAR O MOSTRAR
        //String valid_until = "24/03/2020";
        //String valido_alta = "20/03/2020";

            vAuth = FirebaseAuth.getInstance();

            mNombreApellidos = findViewById(R.id.editNombreReserva);
            mDNI = findViewById(R.id.editDNI);
            mFechaIn = findViewById(R.id.editFechaInicio);
            mFechaFin = findViewById(R.id.editFechaSalida);
            mNumAdultos = findViewById(R.id.editNumAdultos);
            mNumNinos = findViewById(R.id.editNumNinos);
            mNumTelefono = findViewById(R.id.editTelefonoMovil);

            mDatabaseReserva = FirebaseDatabase.getInstance().getReference();
            EnviarReserva = findViewById(R.id.btnEnviarDatosReserva);






        //-----------------------------FIN COMPROBAR FECHAS Y OCULTAR O MOSTRAR
    }

    public void MapaInteres(View view){
        Intent mapainteres = new Intent(ReservasActivity.this, MapsActivity.class);
        startActivity(mapainteres);
    }

    public void MostrarParcela(View view){

        View a = findViewById(R.id.btnOcultarCasa);
        a.setVisibility(View.GONE);

        View b = findViewById(R.id.btnShowCasa);
        b.setVisibility(View.VISIBLE);

        View c = findViewById(R.id.textInfoCasa);
        c.setVisibility(View.GONE);

        View d = findViewById(R.id.textInfoParcela);
        d.setVisibility(View.GONE);

        View e = findViewById(R.id.textParcelas);
        e.setVisibility(View.VISIBLE);
        String Casa1 = "Parcela";

        mCasa = Casa1;

    }

    public void MostrarCasa(View view){

        View a = findViewById(R.id.btnShowCasa);
        a.setVisibility(View.GONE);

        View b = findViewById(R.id.btnOcultarCasa);
        b.setVisibility(View.VISIBLE);

        View c = findViewById(R.id.textInfoCasa);
        c.setVisibility(View.VISIBLE);

        View d = findViewById(R.id.textInfoParcela);
        d.setVisibility(View.GONE);

        View e = findViewById(R.id.textParcelas);
        e.setVisibility(View.GONE);

        String Casa2 = "Casa";

        mCasa = Casa2;

    }

    public void EnviarReserva(View view){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user.isEmailVerified();
        if(emailVerified == false) {
            Toast.makeText(this, "Debe verificar su dirección de correo electrónico para poder reservar"+emailVerified, Toast.LENGTH_SHORT).show();
            Intent redireccionperfil = new Intent(ReservasActivity.this, Perfil.class);
            startActivity(redireccionperfil);
        }
        else {


            String clave = mDatabaseReserva.child("Reservas").push().getKey();
            String nombre = mNombreApellidos.getText().toString();
            String numadultos = mNumAdultos.getText().toString();
            String numninos = mNumNinos.getText().toString();
            String fechainicio = mFechaIn.getText().toString();
            String fechafin = mFechaFin.getText().toString();
            String numerotelefono = mNumTelefono.getText().toString();
            String direccioncorreo = vAuth.getCurrentUser().getEmail();
            String dni = mDNI.getText().toString();
            String Casa = mCasa;

            if(nombre.isEmpty() || numadultos.isEmpty() || numninos.isEmpty() || fechainicio.isEmpty() || numerotelefono.isEmpty() || dni.isEmpty()){
                Toast.makeText(this, "Alguno de los campos introducidos está vacio", Toast.LENGTH_SHORT).show();
            }
            else {

                mDatabaseReserva.child("Reservas").child(clave).child("Titulo").setValue(nombre);
                mDatabaseReserva.child("Reservas").child(clave).child("Correo").setValue(direccioncorreo);
                mDatabaseReserva.child("Reservas").child(clave).child("DNI").setValue(dni);
                mDatabaseReserva.child("Reservas").child(clave).child("NumAdultos").setValue(numadultos);
                mDatabaseReserva.child("Reservas").child(clave).child("NumNinos").setValue(numninos);
                mDatabaseReserva.child("Reservas").child(clave).child("FechaInicio").setValue(fechainicio);
                mDatabaseReserva.child("Reservas").child(clave).child("FechaFin").setValue(fechafin);
                mDatabaseReserva.child("Reservas").child(clave).child("NumeroTelefono").setValue(numerotelefono);
                mDatabaseReserva.child("Reservas").child(clave).child("Casa").setValue(Casa);

            }
        }

    }

}
