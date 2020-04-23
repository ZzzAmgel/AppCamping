package com.example.appcamping.Gestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcamping.IniciadoActivity;
import com.example.appcamping.MultimediaActivity;
import com.example.appcamping.R;
import com.example.appcamping.ReservasActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddFechasTemporada extends AppCompatActivity {

    private int dia, mes, ano;
    private Button bfecha;
    private EditText mFechaTempAlta;
    //private EditText mFechaTemBaja;
    private DatabaseReference mDatabaseFechas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fechas_temporada);
        bfecha=(Button)findViewById(R.id.btnfechaalta);
        mFechaTempAlta = findViewById(R.id.editTempAlta);
        //mFechaTemBaja = (EditText) findViewById(R.id.editTemBaja);
        mDatabaseFechas = FirebaseDatabase.getInstance().getReference();


        //---------------------------NAVIGATION VIEW ----------------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.reservas:
                        startActivity(new Intent(getApplicationContext(), ReservasActivity.class));
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

    public void onClick(View v){
        if(v==bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFechaTempAlta.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
            ,ano,mes,dia);
            datePickerDialog.show();
        }

    }

    public void EnviarTempReservas(View view) {
        String fechaAlta = mFechaTempAlta.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
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
