package com.example.renzo.observatoryclient2;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Renzo on 6/11/2015.
 */
public class MyLocationListener2 implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {

        Log.v("ALERT", "entreeee----------------------------------");
        //myLatLng = new LatLng(loc.getLatitude(),loc.getLongitude());
        Log.v("ALERT", "saliiiii----------------------------------");

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider,
                                int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
