package com.bilal.socialbuddy;


import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    Double latit;
    Double longit;

    static float zoomLevel;

    Double lat;
    Double lng;

    public TrackGPS gps;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        //Showlocs = (TextView)findViewById(R.id.showlocs);


        gps = new TrackGPS(MapsActivity.this);


        if(gps.canGetLocation()){


            lng = gps.getLongitude();
            lat = gps .getLatitude();

            Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(lng)+"\nLatitude:"+Double.toString(lat),Toast.LENGTH_SHORT).show();
        }
        else
        {

            gps.showSettingsAlert();
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng coordinate = new LatLng(lat, lng);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 17);
        mMap.animateCamera(yourLocation);

        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Your Current Location")
                .snippet("Change It"));



        //LL = new LatLng(cur_lat,cur_long);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 12));






    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {


                setUpMap();

            }
        }
    }

    private void setUpMap() {



         //This goes up to 21
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LL, (float) 16.090));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                latit =latLng.latitude;
                longit = latLng.longitude;

                //Log.v("LoginActivity", latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();



                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });



    }

    public void changeType(View view)
    {

        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    public void onZoom(View view) {
        if(view.getId() == R.id.Bzoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.Bzoomout)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }






    public void back(View view) {
        Log.v("LoginActivity", latit.toString()+"===="+longit.toString());
        Intent i = new Intent();
        i.putExtra("home_lat",latit.toString());
        i.putExtra("home_lang",longit.toString());
        setResult(RESULT_OK,i);
        finish();
    }
}

