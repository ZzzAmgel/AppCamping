package com.example.appcamping.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcamping.R;
import com.example.appcamping.models.Mensaje;

import java.util.ArrayList;


public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private int resource;
    private ArrayList<Mensaje> mensajesList;

    public MensajeAdapter(ArrayList<Mensaje>mensajesList, int resource){
        this.mensajesList = mensajesList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        Mensaje mensaje = mensajesList.get(index);
        Mensaje mensaje2 = mensajesList.get(index);
        Mensaje mensaje3 = mensajesList.get(index);

        viewHolder.textViewTitulo.setText(mensaje.getTitulo());
        viewHolder.textViewDescripcion.setText(mensaje2.getTitulo());
        viewHolder.textViewFecha.setText(mensaje3.getTitulo());
    }

    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        private TextView textViewTitulo;
        private TextView textViewDescripcion;
        private TextView textViewFecha;

        public ViewHolder(View view){
        super(view);
        this.view=view;
        this.textViewTitulo = (TextView) view.findViewById(R.id.textViewEvento);
        this.textViewDescripcion = (TextView) view.findViewById(R.id.textViewDescripcion);
        this.textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);

        }
    }
}
