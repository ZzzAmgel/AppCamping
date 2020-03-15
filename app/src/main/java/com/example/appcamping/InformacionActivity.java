package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InformacionActivity extends AppCompatActivity {

    TextView markertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        markertxt=findViewById(R.id.marker);
        //COGEMOS LA ID PARA EL MAPA
        String title=getIntent().getStringExtra("title");
        markertxt.setText(title);

    }
}
