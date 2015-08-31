package com.example.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Document;

import app.akexorcist.gdaplibrary.GoogleDirection;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        //Handle
       /* if (mMap == null) {
            GoogleMapOptions options = new GoogleMapOptions();
//            options.mapType(GoogleMap.MAP_TYPE_TERRAIN);
            options.compassEnabled(true);
            options.zoomControlsEnabled(true);
            options.zoomGesturesEnabled(true);
            options.mapToolbarEnabled(true);
            options.tiltGesturesEnabled(true);

            SupportMapFragment fragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.map_container, fragment)
                    .commit();
            mMap = fragment.getMap();
        }
        if (mMap != null)
            setUpMap();*/
        //Auto
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    GoogleDirection direction;
    private void setUpMap() {
        final LatLng start = new LatLng(21.0277644, 105.8341598);
        final LatLng end = new LatLng(21.03263639, 105.8389163);

        final MarkerOptions marker = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .position(start).title("Marker")
                .snippet("Description");


        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                mMap.addMarker(marker);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 15));
                //buoc 1
                mMap.setMyLocationEnabled(true);

                UiSettings uiSettings = mMap.getUiSettings();
                uiSettings.setCompassEnabled(true);
                uiSettings.setZoomControlsEnabled(true);
                uiSettings.setIndoorLevelPickerEnabled(true);
                uiSettings.setAllGesturesEnabled(true);
//        GoogleMapOptions options = new GoogleMapOptions();
//        options.mapType(GoogleMap.)

                //buoc 2
                uiSettings.setMyLocationButtonEnabled(true);
            }
        });

        direction = new GoogleDirection(this);
        direction.setOnDirectionResponseListener(new GoogleDirection.OnDirectionResponseListener() {
            @Override
            public void onResponse(String status, Document doc, GoogleDirection gd) {
                mMap.addPolyline(direction.getPolyline(doc,2, Color.BLUE));
            }
        });

        direction.request(start,end,GoogleDirection.MODE_DRIVING);

    }

}
