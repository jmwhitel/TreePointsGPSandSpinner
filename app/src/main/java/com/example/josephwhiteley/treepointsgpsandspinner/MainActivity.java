package com.example.josephwhiteley.treepointsgpsandspinner;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btnRequestLocation;
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;


    EditText etNorthing, etEasting, etElevation;
    Button btnadd, btnView;
    DatabaseHelper myDB;
    Spinner spinnerDialog;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequestLocation = (Button) findViewById(R.id.btnRequestLocation);
        final TextView textview1 = (TextView) findViewById(R.id.etNorthing);
        final TextView textview2 = (TextView) findViewById(R.id.etEasting);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                textview1.append("\n "+location.getLatitude());
                textview2.append("\n "+location.getLongitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }else{
                configurebtnRequestLocation();
            }
        }

        etElevation = (EditText) findViewById(R.id.etElevation);
        etNorthing = (EditText) findViewById(R.id.etNorthing);
        etEasting = (EditText) findViewById(R.id.etEasting);
        btnadd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String northing = etNorthing.getText().toString();
                String easting = etEasting.getText().toString();
                String elevation = etElevation.getText().toString();
                if(northing.length() !=0 && easting.length() !=0 && elevation.length() !=0) {
                    AddData(northing,easting,elevation);
                    etElevation.setText("");
                    etNorthing.setText("");
                    etEasting.setText("");
                } else{
                    Toast.makeText(MainActivity.this, "You need to enter data in the tree points fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configurebtnRequestLocation();
                return;
        }
    }

    private void configurebtnRequestLocation() {
        btnRequestLocation.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates("gps", 0, 50, locationListener);
            }
        }));

    }

    public void AddData(String Northing, String Easting, String Elevation){
        boolean booleaninsertData = myDB.addData(Northing, Easting, Elevation);

        boolean insertData;
        //if(insertData==true){
        Toast.makeText(MainActivity.this, "You have successfully entered a tree point!",Toast.LENGTH_LONG).show();
    } //else{
    //Toast.makeText(MainActivity.this, "Something went wrong :(",Toast.LENGTH_LONG).show();

}




