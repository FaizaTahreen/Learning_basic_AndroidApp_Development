package com.nsit.googlemapappdemo;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    Location location;
    LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkedPermission();
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
        lm = (LocationManager) (getSystemService(LOCATION_SERVICE));
        Criteria criteria = new Criteria();
        String provider = lm.getBestProvider(criteria, false);
        Toast.makeText(this, "My Provide : \n"+provider, Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = lm.getLastKnownLocation(provider);
        // Add a marker in Patna and move the camera
        if(location!=null)
        {
            onLocationChanged(location);
        }
        lm.requestLocationUpdates(provider,20000,100,this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat=location.getLatitude();
        double longitude=location.getLongitude();
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList=geocoder.getFromLocation(lat,longitude,1);
            if(addressList!=null)
            {
                Address address=addressList.get(0);
                StringBuilder sb=new StringBuilder();
                sb.append(address.getAdminArea()+"\n"+address.getCountryCode()+"\n"
                        +address.getLocality()+"\n"+address.getPostalCode()+"\n");
                Toast.makeText(this, ""+sb.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        LatLng curent_loc = new LatLng(lat, longitude);
        mMap.addMarker(new MarkerOptions().position(curent_loc).title("Marker in My Place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curent_loc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(45));
    }
    private boolean checkedPermission()
    {

        int internet=ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET);
        int read=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int write=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int wifi_access=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_WIFI_STATE);
        int wifi_change=ContextCompat.checkSelfPermission(this,Manifest.permission.CHANGE_WIFI_STATE);
        int loc_access=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int coarse_access=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> permissionlist=new ArrayList();

        if(internet!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.INTERNET);
        }
        if(read!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(write!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(wifi_access!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.ACCESS_WIFI_STATE);
        }
        if(wifi_change!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        if(loc_access!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(coarse_access!= PackageManager.PERMISSION_GRANTED)
        {
            permissionlist.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if(!permissionlist.isEmpty())
        {
            ActivityCompat.requestPermissions(this,permissionlist.toArray(new String[permissionlist.size()]),10111);
            return false;
        }
        return true;
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}