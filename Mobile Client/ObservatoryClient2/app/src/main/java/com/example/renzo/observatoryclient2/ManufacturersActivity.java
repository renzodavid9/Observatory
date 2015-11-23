package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManufacturersActivity extends Activity {

    TextView act_component;
    ListView manufacturer_list;
    TextView manufacturer_view;
    TextView tot_medicines_view;
    Button show_medicines;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    List<Drugstore> manufacturers;
    int manufacturerSelected = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);
        setup();
    }

    private void setup(){
        act_component = (TextView)findViewById(R.id.manufacturer_active_component);
        manufacturer_list = (ListView)findViewById(R.id.manufacturers_list);
        manufacturer_view = (TextView)findViewById(R.id.manufacturer_name);
        tot_medicines_view = (TextView)findViewById(R.id.manufacturer_numMedicines);
        show_medicines = (Button)findViewById(R.id.showMedicines_btn);

        manufacturers = LogicCaller.manufacturersList;

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String actComp = LogicCaller.active_component;
        SpannableString nameSpannable= new SpannableString(actComp);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, actComp.length(), 0);
        builder.append(act_component.getText());
        builder.append(nameSpannable);
        act_component.setText(builder, TextView.BufferType.SPANNABLE);

        for (Drugstore manufacturer : manufacturers){
            listItems.add(manufacturer.getName());
        }
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        manufacturer_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        manufacturer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                //view.setSelected(true);
                manufacturerSelected = position;
                setManufacturerData(manufacturers.get(manufacturerSelected));
            }
        });

        show_medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (manufacturerSelected != -1) {
                    LogicCaller.laboratory = manufacturers.get(manufacturerSelected);
                    Intent intent = new Intent(getBaseContext(), GenericActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "No ha seleccionado laboratio.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(getBaseContext(),"LLEGUE",Toast.LENGTH_SHORT).show();

    }

    private void setManufacturerData(Drugstore manufacturer) {
        String manufacturerName = manufacturer.getName();
        String totMedicines = Integer.toString(manufacturer.getMedicinesSize());
        String labelName = "Laboratorio: ";
        String labelTot = "Medicamentos encontrados: ";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString nameSpannable= new SpannableString(manufacturerName);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, manufacturerName.length(), 0);
        builder.append(labelName);
        builder.append(nameSpannable);
        manufacturer_view.setText(builder, TextView.BufferType.SPANNABLE);

        builder = new SpannableStringBuilder();
        nameSpannable = new SpannableString(totMedicines);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0,totMedicines.length(),0);
        builder.append(labelTot);
        builder.append(nameSpannable);
        tot_medicines_view.setText(builder,TextView.BufferType.SPANNABLE);
    }


}
