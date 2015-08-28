package com.example.myapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.List;

public class LocationActivity extends ActionBarActivity implements LocationListener {

    TextView textView;
    private LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        textView = (TextView) findViewById(R.id.textview);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        List<String> allProviders = manager.getAllProviders();
//        String providers = "";
//        for(int i = 0; i < allProviders.size(); i++){
//            providers += allProviders.get(i) +"\n";
//        }
//        textView.setText(providers);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,10,this);

        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null) {
            textView.setText(location.getLatitude() + ":" + location.getLongitude());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        textView.setText(location.getLatitude() +":" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int i, Bundle bundle) {
        Location location = manager.getLastKnownLocation(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "enabled: " + provider, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "disabled: " + provider, Toast.LENGTH_SHORT).show();

    }
}
