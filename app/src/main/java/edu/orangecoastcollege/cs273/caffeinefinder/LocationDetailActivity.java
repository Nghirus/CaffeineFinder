package edu.orangecoastcollege.cs273.caffeinefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationDetailActivity extends AppCompatActivity implements OnMapReadyCallback{

    private TextView mDetailNameTextView;
    private TextView mDetailAddressTextView;
    private TextView mDetailPhoneTextView;
    private TextView mDetailLngLatTextView;

    private SupportMapFragment mMapFragment;
    private GoogleMap mDetailMap;
    private LatLng mLatLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        mDetailNameTextView = (TextView) findViewById(R.id.detailNameTextView);
        mDetailAddressTextView = (TextView) findViewById(R.id.detailAddressTextView);
        mDetailPhoneTextView = (TextView) findViewById(R.id.detailsPhoneTextView);
        mDetailLngLatTextView = (TextView) findViewById(R.id.detailsLngLatTextView);

        mMapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detailsMapFragment);
        mMapFragment.getMapAsync(this);
        Location selectedLocation = getIntent().getExtras().getParcelable("Selected Location");

        mDetailNameTextView.setText(selectedLocation.getName());
        mDetailAddressTextView.setText(selectedLocation.getFullAddress());
        mDetailPhoneTextView.setText(selectedLocation.getPhone());
        mDetailLngLatTextView.setText("LOCATION");
        mLatLng = new LatLng(selectedLocation.getLatitude(),selectedLocation.getLongitude());


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mDetailMap = googleMap;
        CameraPosition cameraPosition= CameraPosition.builder().target(mLatLng).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mDetailMap.moveCamera(cameraUpdate);

        mDetailMap.addMarker(new MarkerOptions().position(mLatLng).title(mDetailNameTextView.getText().toString()));
    }
}
