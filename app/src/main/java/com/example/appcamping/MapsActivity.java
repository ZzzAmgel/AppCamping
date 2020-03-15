package com.example.appcamping;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng casa1 = new LatLng(38.359179, -1.882551);
    LatLng casa2 = new LatLng(38.359289, -1.882479);
    LatLng casa3 = new LatLng(38.359355, -1.882278);
    LatLng casa4 = new LatLng(38.359496, -1.882436);
    LatLng casa5 = new LatLng(38.359518, -1.882864);
    LatLng casa6 = new LatLng(38.359395, -1.882908);
    LatLng casa7 = new LatLng(38.359094, -1.882898);
    LatLng casa8 = new LatLng(38.359276, -1.883080);
    LatLng casa9 = new LatLng(38.359712, -1.882990);
    ArrayList<String> tittle=new ArrayList<String>();
    private GoogleMap mMap;

    //private View popup=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //nuestro array de items
        arrayList.add(casa1);
        arrayList.add(casa2);
        arrayList.add(casa3);
        arrayList.add(casa4);
        arrayList.add(casa5);
        arrayList.add(casa6);
        arrayList.add(casa7);
        arrayList.add(casa8);
        arrayList.add(casa9);
        //array de titulos
        tittle.add("Casa 1");
        tittle.add("Casa 2");
        tittle.add("Casa 3");
        tittle.add("Casa 4");
        tittle.add("Casa 5");
        tittle.add("Casa 6");
        tittle.add("Casa 7");
        tittle.add("Casa 8");
        tittle.add("Casa 9");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        int j=0;
        mMap=googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        for(int i=0;i<arrayList.size();i++){

              //AÑADIMOS LOS MARCADORES MEDIANTE EL LOOP
                mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(tittle.get(j))));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
            j++;
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String markertittle=marker.getTitle();
                //Toast.makeText(MapsActivity.this, ""+markertittle, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MapsActivity.this, InformacionActivity.class);
                //PASAMOS EL TITULO Y LA ACTIVIDAD
                i.putExtra("title",markertittle);
                startActivity(i);

                //https://youtu.be/m6zcM6Q2qZU


                return false;
            }
        });

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casa3, 18), 5000, null);   //animación zoom
    }
}
