package com.example.appcamping.Gestion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcamping.IniciadoActivity;
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

        mFechaTempAlta = findViewById(R.id.editTempAlta);
        //mFechaTemBaja = (EditText) findViewById(R.id.editTemBaja);
        mDatabaseFechas = FirebaseDatabase.getInstance().getReference();


    }

    public void EnviarTempReservas(View view) {
        String fechaAlta = mFechaTempAlta.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.moneyicon);
        progressDialog.setMessage("Cargando la fecha introducida: \n"+ fechaAlta);
        progressDialog.show();



        if(fechaAlta.equals(null) || fechaAlta.equals("dd/mm/aaaa")){
            Toast.makeText(this, "Introuduce una fecha", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            mDatabaseFechas.child("Fechas").child("FechaTempAlta").setValue(fechaAlta);

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        }, 2000);

    }
    public void Volver(View view){
        Intent volver = new Intent(AddFechasTemporada.this, IniciadoActivity.class);
        startActivity(volver);
    }
}
