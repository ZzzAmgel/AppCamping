package com.example.appcamping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appcamping.models.Eventos;
import com.example.appcamping.models.Module;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ShowData extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Button btnDelete;
    Module module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        databaseReference = FirebaseDatabase.getInstance().getReference("Eventos");
        listView = findViewById(R.id.listViewShow);
        btnDelete = findViewById(R.id.btnBorrarElemento);
        //module=((Module)getApplicationContext());
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(Eventos.class).toString();
                arrayList.add(value);
                Collections.reverse(arrayList);
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


        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paramAdapterView, View view, int position, long id) {
                module.setGvalue_titulo(arrayList.get(position));
                module.setGvalue_descripcion(arrayList.get(position));
                module.setGvalue_fecha(arrayList.get(position));
                module.setGvalue_url(arrayList.get(position));
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String str = module.getGvalue_titulo().substring(0, 6);
                if(str == ""){
                    Toast.makeText(ShowData.this, "No se ha seleccionado ningun elemento para eliminar", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Eventos").child(str).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            databaseReference.child(str).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(ShowData.this, "Evento Eliminado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ShowData.class);
                    startActivity(intent);
                }
            }
        });

         */

    }

}
