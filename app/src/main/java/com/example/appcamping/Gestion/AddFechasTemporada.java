package com.example.appcamping.Gestion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcamping.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFechasTemporada extends AppCompatActivity {

    private EditText mFechaTempAlta;
    //private EditText mFechaTemBaja;
    private DatabaseReference mDatabaseFechas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fechas_temporada);

        mFechaTempAlta = (EditText) findViewById(R.id.editTempAlta);
        //mFechaTemBaja = (EditText) findViewById(R.id.editTemBaja);
        mDatabaseFechas = FirebaseDatabase.getInstance().getReference();


    }

    public void EnviarTempReservas(View view) {

        String fechaAlta = mFechaTempAlta.getText().toString();
        //String fechaBaja = mFechaTemBaja.getText().toString();

        mDatabaseFechas.child("Fechas").child("FechaTempAlta").setValue(fechaAlta);
        //mDatabaseFechas.child("Fechas").child("FechaTempBaja").setValue(fechaBaja);

        Toast.makeText(this, "La fecha de cambio de precios es:"+mFechaTempAlta, Toast.LENGTH_SHORT).show();
    }
}
