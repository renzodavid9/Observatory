package com.example.renzo.observatoryclient2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.tech.NfcBarcode;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;
    private LocationManager mLocationManager=null;
    private LocationListener mListener=null;
    private Location myLocation;
    private LatLng myLatLng;

    private String medicineName;
    private int searchRatio;

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    List<Marker> drugstoresMarkers = new ArrayList<>();
    List<Drugstore> drugstores = new ArrayList<>();

    ListView mListView;
    Button showMedicines_btn;

    int selectedDrugstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();
        setup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setup(){

        showMedicines_btn = (Button)findViewById(R.id.showMedicines_btn);
        showMedicines_btn.setClickable(false);
        showMedicines_btn.setVisibility(View.INVISIBLE);


        myLatLng = LogicCaller.getMyPosition();
        drugstores = LogicCaller.drugstoresList;
        //drugstores = LogicCaller.getDrugstores_test();
        setUpMapIfNeeded();

        drugstoresMarkers = new ArrayList<>();

        mListView = (ListView)findViewById(R.id.home_list_time);

        loadDrugstoresIntoMap(drugstores);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);



        adapter.notifyDataSetChanged();

        /*
        if (drugstores.size()<=0){
            Toast.makeText(getBaseContext(), "No se encontraron resultados para la busqueda.", Toast.LENGTH_LONG).show();
        }*/


        showMedicines_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LogicCaller.drug = drugstores.get(selectedDrugstore);
                Intent intent = new Intent(getBaseContext(),MedicinesActivity.class);
                startActivity(intent);

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                //Toast.makeText(getBaseContext(),"P: "+Integer.toString(position),Toast.LENGTH_LONG).show();
                LatLng l = drugstoresMarkers.get(position).getPosition();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 16f));
                drugstoresMarkers.get(position).showInfoWindow();
                selectedDrugstore = position;
                setDrugstoreInfo(drugstores.get(position));
                showMedicines_btn.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void loadDrugstoresIntoMap(List<Drugstore> list){
        listItems = new ArrayList<>();
        for (Drugstore d: list){
            makeDrugstoreMarker(d);
            listItems.add(d.getName());
        }
    }

    private void setUpMap() {
        makeMyMarker();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,16f));
    }

    private void makeMyMarker(){
        Bitmap icon = resizeMapIcons("person", 40, 60);
        Marker m = mMap.addMarker(new MarkerOptions().position(myLatLng).title("Yo").icon(BitmapDescriptorFactory.fromBitmap(icon)));
    }

    private void makeDrugstoreMarker(Drugstore d) {
        Bitmap icon = resizeMapIcons("pill", 70, 70);
        LatLng marker = new LatLng(d.getLat(),d.getLog());
        Marker m = mMap.addMarker(new MarkerOptions().position(marker).title(d.getName()).snippet("Medicamentos encontrados: " + d.getMedicinesSize()).icon(BitmapDescriptorFactory.fromBitmap(icon)));
        drugstoresMarkers.add(m);
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    private void setDrugstoreInfo(Drugstore d){
        TextView name = (TextView)findViewById(R.id.drug_name);
        TextView direction = (TextView)findViewById(R.id.drug_direction);
        TextView numMedicines = (TextView)findViewById(R.id.drug_numMedicines);

        name.setText("Nombre: ");
        direction.setText("Direccion: ");
        numMedicines.setText("Medicamentos encontrados: ");

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String nameD = d.getName();
        SpannableString nameSpannable= new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(name.getText());
        builder.append(nameSpannable);
        name.setText(builder, TextView.BufferType.SPANNABLE);

        builder = new SpannableStringBuilder();
        nameD = d.getDirection();
        nameSpannable = new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(direction.getText());
        builder.append(nameSpannable);
        direction.setText(builder, TextView.BufferType.SPANNABLE);

        builder = new SpannableStringBuilder();
        nameD = d.getMedicinesSize()+"";
        nameSpannable = new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(numMedicines.getText());
        builder.append(nameSpannable);
        numMedicines.setText(builder, TextView.BufferType.SPANNABLE);


        showMedicines_btn.setClickable(true);
    }
}
