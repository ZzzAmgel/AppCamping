package com.example.appcamping.Gestion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appcamping.R;
import com.example.appcamping.adapters.MensajeAdapter;
import com.example.appcamping.models.Eventos;
import com.example.appcamping.models.Gastos;
import com.example.appcamping.models.Mensaje;
import com.example.appcamping.models.Module2;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class EnviarMostrarGastos extends AppCompatActivity {

    ListView listview;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Module2 module2;
    private EditText mEditPrecio;
    private EditText mEditNombreGasto;
    private DatabaseReference mDatabaseGastos;
    private Button mEnviarGastos;
    private String mFechaGasto;
    //----------------MOSTRAR GASTOS
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mostrar_gastos);

        mFechaGasto = new Date().toString();
        mEditNombreGasto = (EditText) findViewById(R.id.editNombreGasto);
        mEditPrecio = (EditText) findViewById(R.id.editPrecio);
        mDatabaseGastos = FirebaseDatabase.getInstance().getReference();
        mEnviarGastos = findViewById(R.id.btnEnviarGastos);
        mEnviarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mDatabaseGastos.child("Gastos").push().getKey();
                String nombregasto = mEditNombreGasto.getText().toString();
                String precio = mEditPrecio.getText().toString();
                String fechagasto = mFechaGasto;

                mDatabaseGastos.child("Gastos").child(key).child("NombreGasto").setValue(nombregasto);
                mDatabaseGastos.child("Gastos").child(key).child("Precio").setValue(precio);
                mDatabaseGastos.child("Gastos").child(key).child("FechaGasto").setValue(fechagasto);
            }
        });

        //-----------------MOSTRAR GASTOS--------------
        databaseReference= FirebaseDatabase.getInstance().getReference("Gastos");
        listview=(ListView) findViewById(R.id.listMostrarGastos);
        arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value=dataSnapshot.getValue(Gastos.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
