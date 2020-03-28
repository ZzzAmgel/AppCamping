package com.example.appcamping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ContactoActivity extends AppCompatActivity {

    private Button btnLlamar;

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mEditTextTo = findViewById(R.id.edittextFor);
        mEditTextSubject = findViewById(R.id.editextFrom);
        mEditTextMessage = findViewById(R.id.edittextMensaje);

        Button buttonSend = findViewById(R.id.btnMandarMail);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        btnLlamar = findViewById(R.id.btnLlamarA);
        btnLlamar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri numtel = Uri.parse("tel:646533845");
                Intent intencionllamar = new Intent (Intent.ACTION_DIAL, numtel);
                startActivity(intencionllamar);
            }
        });

    }


    public void IrWeb(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://riorural.com/"));
        startActivity(browserIntent);
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}
