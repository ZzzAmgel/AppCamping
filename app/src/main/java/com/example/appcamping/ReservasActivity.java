package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservasActivity extends AppCompatActivity {

    FirebaseAuth vAuth;
    private DatabaseReference mDatabaseReserva;
    private Button EnviarReserva;
    private EditText mNombreApellidos;
    private EditText mNumAdultos;
    private EditText mNumNinos;
    private EditText mFechaIn;
    private EditText mFechaFin;
    private EditText mNumTelefono;
    private EditText mDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        View a = findViewById(R.id.btnShowCasa);
        a.setVisibility(View.GONE);

        View c = findViewById(R.id.textParcelas);
        c.setVisibility(View.GONE);

        //-----------------------------COMPROBAR FECHAS Y OCULTAR O MOSTRAR
        String valid_until = "20/02/2020";
        String valido_alta = "20/03/2020";
        SimpleDateFormat sdfBaja = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfAlta = new SimpleDateFormat("dd/MM/yyyy");
        Date strTemporadaBaja = null;
        Date strTemporadaAlta = null;
        try {
            strTemporadaBaja = sdfBaja.parse(valid_until);
            strTemporadaAlta = sdfAlta.parse(valido_alta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().before(strTemporadaBaja)) {
            View j = findViewById(R.id.textInfoCasa);
            j.setVisibility(View.GONE);

            View l = findViewById(R.id.textParcelas);
            l.setVisibility(View.VISIBLE);
        }
        else if (new Date().after(strTemporadaAlta)){

            View k = findViewById(R.id.textParcelas);
            k.setVisibility(View.GONE);

            View n = findViewById(R.id.textInfoParcela);
            n.setVisibility(View.GONE);

            View m = findViewById(R.id.textInfoCasa);
            m.setVisibility(View.VISIBLE);

            //------------------- ON CREATE SUBIR BDRTB --------------------

            vAuth = FirebaseAuth.getInstance();

            mNombreApellidos = (EditText) findViewById(R.id.editNombreReserva);
            mDNI = (EditText) findViewById(R.id.editDNI);
            mFechaIn = (EditText) findViewById(R.id.editFechaInicio);
            mFechaFin = (EditText) findViewById(R.id.editFechaSalida);
            mNumAdultos = (EditText) findViewById(R.id.editNumAdultos);
            mNumNinos = (EditText) findViewById(R.id.editNumNinos);
            mNumTelefono = (EditText) findViewById(R.id.editTelefonoMovil);

            mDatabaseReserva = FirebaseDatabase.getInstance().getReference();
            EnviarReserva = findViewById(R.id.btnEnviarDatosReserva);


        }


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

    }

    public void EnviarReserva(View view){

        String clave = mDatabaseReserva.child("Reservas").push().getKey();
        String nombre = mNombreApellidos.getText().toString();
        String numadultos = mNumAdultos.getText().toString();
        String numninos = mNumNinos.getText().toString();
        String fechainicio = mFechaIn.getText().toString();
        String fechafin = mFechaFin.getText().toString();
        String numerotelefono = mNumTelefono.getText().toString();
        String direccioncorreo = vAuth.getCurrentUser().getEmail();
        String dni = mDNI.getText().toString();

        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("Titulo").setValue(nombre);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("Correo").setValue(direccioncorreo);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("DNI").setValue(dni);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("NumAdultos").setValue(numadultos);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("NumNinos").setValue(numninos);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("FechaInicio").setValue(fechainicio);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("FechaFin").setValue(fechafin);
        mDatabaseReserva.child("Reservas").child(clave).child("Reserva").child("NumeroTelefono").setValue(numerotelefono);

    }

}
