package com.example.appcamping;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsInteresActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_interes);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng piscina = new LatLng(38.359441, -1.881548);
        mMap.addMarker(new MarkerOptions().position(piscina).title("Piscina"));
        LatLng rio = new LatLng(38.359431, -1.881551);
        mMap.addMarker(new MarkerOptions().position(rio).title("Rio"));
        LatLng pantano = new LatLng(38.359795, -1.878381);
        mMap.addMarker(new MarkerOptions().position(pantano).title("Pantano"));
        LatLng santuario = new LatLng(38.369684, -1.872874);
        mMap.addMarker(new MarkerOptions().position(santuario).title("Santuario"));
        LatLng castillo = new LatLng(38.329038, -1.984665);
        mMap.addMarker(new MarkerOptions().position(castillo).title("Castillo"));
        LatLng picomontana = new LatLng(38.357610, -1.883538);
        mMap.addMarker(new MarkerOptions().position(picomontana).title("Pico el Cañar"));
        LatLng camino = new LatLng(38.359597, -1.884757);
        mMap.addMarker(new MarkerOptions().position(camino).title("Sendero"));
        LatLng aldea = new LatLng(38.353325, -1.885782);
        mMap.addMarker(new MarkerOptions().position(aldea).title("Aldea El Cañar"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(piscina));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(piscina, 16), 8000, null);

    }
}
