package com.bilal.socialbuddy;

import android.app.Service;

import android.content.Intent;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationService extends Service implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {



    private static final String TAG = "LoginActivity";

    Double prevLat;
    Double prevLng;

    Double CurrLat;
    Double CurrLng;

    Boolean status =false;

    Double diff_Lat;
    Double diff_Lng;



    private boolean currentlyProcessingLocation = false;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // if we are currently trying to get a location and the alarm manager has called this again,
        // no need to start processing a new location.
        if (!currentlyProcessingLocation) {
            currentlyProcessingLocation = true;
            startTracking();
        }

        return START_NOT_STICKY;
    }

    private void startTracking() {
        Log.d(TAG, "startTracking");

        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {

            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

            if (!googleApiClient.isConnected() || !googleApiClient.isConnecting()) {
                googleApiClient.connect();
            }
        } else {
            Log.e(TAG, "unable to connect to google play services.");
        }
    }

    protected void sendLocationDatabase(Location location) throws IOException {


        if (status==true)
        {


            CurrLat= location.getLatitude();
            CurrLng= location.getLongitude();















            diff_Lat = Math.abs(prevLat-CurrLat);
            diff_Lng = Math.abs(prevLng-CurrLng);

            if(diff_Lat<0.000200 && diff_Lng<0.000200)
            {
                //Log.v("LoginActivity", "Diff_Lat:"+Double.toString(diff_Lat));
                //Log.v("LoginActivity", "Diff_Long:"+Double.toString(diff_Lng));


                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(LocationService.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(CurrLat, CurrLng, 1);
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    Log.v("LoginActivity", address+city+state+country+knownName);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.v("LoginActivity", "Error");
                }



















            }

            status=false;

        }

        prevLat= location.getLatitude();
        prevLng= location.getLongitude();


        Log.v("LoginActivity", "Latitude:"+Double.toString(location.getLatitude()));
        Log.v("LoginActivity", "Longitude:"+Double.toString(location.getLongitude()));

        Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(location.getLongitude())+"\nLatitude:"+Double.toString(location.getLatitude()),Toast.LENGTH_LONG).show();

        status=true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            //Log.e(TAG, "position: " + location.getLatitude() + ", " + location.getLongitude() + " accuracy: " + location.getAccuracy());

            try {
                sendLocationDatabase(location);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void stopLocationUpdates() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000); // milliseconds
        locationRequest.setFastestInterval(1000); // the fastest rate in milliseconds at which your app can handle location updates
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed");

        stopLocationUpdates();
        stopSelf();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "GoogleApiClient connection has been suspend");
    }
}
