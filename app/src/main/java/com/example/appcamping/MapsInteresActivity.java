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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(piscina));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(piscina, 22), 5000, null);   //animación zoom

        /*
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                if(popup == null){
                    popup=getLayoutInflater().inflate(R.layout.popupmap, null);
                }

                TextView texto=(TextView)popup.findViewById(R.id.title);
                ImageView imagen=(ImageView)popup.findViewById(R.id.icon);
                imagen.setImageResource(R.drawable.campingmap);
                texto.setText(marker.getTitle());

                //Nos hemos saltado algo: https://www.youtube.com/watch?v=h-LVVr1tpHY

                return (popup);
            }
        });  */

        // Add a marker in Sydney and move the camera

        //mMap.addMarker(new MarkerOptions().position(camping).title("Camping"));
        //mMap.addMarker(new MarkerOptions().position(rio).title("Rio"));
        //mMap.addMarker(new MarkerOptions().position(pantano).title("Pantano"));
        //mMap.addMarker(new MarkerOptions().position(santuario).title("Santuario"));
        //mMap.addMarker(new MarkerOptions().position(restaurante).title("Restaurante"));
    }
}
