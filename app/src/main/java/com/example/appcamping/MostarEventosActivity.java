package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcamping.adapters.MensajeAdapter;
import com.example.appcamping.models.Mensaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MostarEventosActivity extends AppCompatActivity {


    private DatabaseReference mDatabaseShow;

    private MensajeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Mensaje> mMensajesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_eventos);
        mDatabaseShow = FirebaseDatabase.getInstance().getReference();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewEventos);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getMensajesFirebase();

    }

    private void getMensajesFirebase(){
        mDatabaseShow.child("Eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String Titulo = ds.child("Titulo").getValue().toString();
                        String Descripcion = ds.child("Descripción").getValue().toString();
                        String Fecha = ds.child("Fecha").getValue().toString();
                        mMensajesList.add(new Mensaje(Titulo, Descripcion, Fecha));
                    }

                    mAdapter = new MensajeAdapter(mMensajesList, R.layout.evento_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
