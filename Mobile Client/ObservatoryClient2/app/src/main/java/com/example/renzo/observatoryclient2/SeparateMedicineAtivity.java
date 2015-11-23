package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SeparateMedicineAtivity extends Activity {

    ListView apartsMedicine;

    List<SeparatedMedicine> listAparts;
    ArrayList<SpannableStringBuilder> listItems = new ArrayList<>();
    ArrayAdapter<SpannableStringBuilder> adapter;
    TextView med_name;
    TextView med_price;
    TextView drog_name;
    TextView drog_dir;
    TextView solicitude_status;
    TextView code;
    Button delete;

    String med_name_tit;
    String med_price_tit;
    String drog_name_tit;
    String drog_dir_tit;
    String solicitude_status_tit;
    String code_tit;

    int rowSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_medicine_ativity);
        setup();
    }

    private void setup(){

        apartsMedicine = (ListView)findViewById(R.id.list_aparts);
        med_name = (TextView)findViewById(R.id.apart_medicine_name);
        med_price = (TextView)findViewById(R.id.apart_medicine_price);
        drog_name = (TextView)findViewById(R.id.apart_drugstore_name);
        drog_dir = (TextView)findViewById(R.id.apart_drugstore_direction);
        solicitude_status = (TextView)findViewById(R.id.apart_status);
        code = (TextView)findViewById(R.id.apart_code);
        delete = (Button)findViewById(R.id.aparts_btn);

        med_name_tit = med_name.getText().toString();
        med_price_tit = med_price.getText().toString();
        drog_name_tit = drog_name.getText().toString();
        drog_dir_tit = drog_dir.getText().toString();
        solicitude_status_tit = solicitude_status.getText().toString();
        code_tit = code.getText().toString();

        listAparts = LogicCaller.getAparts(SeparateMedicineAtivity.this);

        for (SeparatedMedicine separeted : listAparts ){
            listItems.add(setColor(separeted.getMedicine_name() + "\n" + separeted.getDrugstore_name() + "\n", separeted.getStatus(),separeted.getStatus_code()));
        }
        adapter=new ArrayAdapter<SpannableStringBuilder>(this,R.layout.listviewtext, listItems);
        apartsMedicine.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        apartsMedicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                rowSelected = position;
                setApartData();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowSelected==-1){
                    Toast.makeText(getBaseContext(),"Seleccione un registro.",Toast.LENGTH_SHORT).show();
                }else{
                    deleteRow();
                }
            }
        });


    }

    private void deleteRow() {
        SeparatedMedicine sep = listAparts.get(rowSelected);
        if (sep.getStatus_code()==1 || sep.getStatus_code()==2 || sep.getStatus_code()==3 ){
            LogicCaller.deleteRegistry(sep.getId(),SeparateMedicineAtivity.this);
            listItems.remove(rowSelected);
            listAparts.remove(rowSelected);
            rowSelected = -1;
            adapter = new ArrayAdapter<SpannableStringBuilder>(this, R.layout.listviewtext, listItems);
            apartsMedicine.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            resetFields();
        }else{
            Toast.makeText(getBaseContext(),"No puede eliminar solicitudes en estado PENDIENTE",Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        med_name.setText(med_name_tit);
        med_price.setText(med_price_tit);
        drog_name.setText(drog_name_tit);
        drog_dir.setText(drog_dir_tit);
        solicitude_status.setText(solicitude_status_tit);
        code.setText(code_tit);
    }

    private void setApartData() {
        SeparatedMedicine sep_med = listAparts.get(rowSelected);
        String name = sep_med.getMedicine_name();
        String name_drug = sep_med.getDrugstore_name();
/*
        if (name.length()>=30){
            name = name.substring(0,29);
            name = name + "...";
        }
        if (name_drug.length()>35){
            name_drug = name_drug.substring(0,34);
            name_drug = name_drug + "...";
        }*/

        med_name.setText(build(name,med_name_tit));
        med_price.setText(build(sep_med.getMedicine_price()+" COP",med_price_tit));
        drog_name.setText(build(name_drug,drog_name_tit));
        drog_dir.setText(build(sep_med.getDrugstore_direction(),drog_dir_tit));
        solicitude_status.setText(build(sep_med.getStatus(),solicitude_status_tit));
        int status = sep_med.getStatus_code();
        String code2 = sep_med.getSeparate_id();

        solicitude_status.setText(build2(sep_med.getStatus(), solicitude_status_tit, status + ""));
        if (status == 0 || status == 2){
            code2 = "N/A";
        }
        code.setText(build(code2, code_tit));
    }

    private SpannableStringBuilder build2(String status, String tit, String code2) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString nameSpannable2= new SpannableString(status);
        if(code2.equalsIgnoreCase("0")) {
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(255,199,6)), 0, status.length(), 0);
        }else if (code2.equalsIgnoreCase("1")){
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(0,217,54)), 0, status.length(), 0);
        }else if (code2.equalsIgnoreCase("2")){
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(210,0,0)), 0, status.length(), 0);
        }else{
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(128,64,0)), 0, status.length(), 0);
        }
        builder.append(tit);
        builder.append(nameSpannable2);
        return builder;
    }

    private SpannableStringBuilder build(String message,String title){
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString nameSpannable= new SpannableString(message);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, message.length(), 0);
        builder.append(title);
        builder.append(nameSpannable);
        return builder;
    }

    private SpannableStringBuilder setColor(String message,String status, int code2){
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString nameSpannable= new SpannableString(message);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, message.length(), 0);

        SpannableString nameSpannable2= new SpannableString(status);
        if(code2 == 0) {
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(255,199,6)), 0, status.length(), 0);
        }else if (code2 == 1){
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(0,217,54)), 0, status.length(), 0);
        }else if (code2==2){
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(210,0,0)), 0, status.length(), 0);
        }else{
            nameSpannable2.setSpan(new ForegroundColorSpan(Color.rgb(128,64,0)), 0, status.length(), 0);
        }
        builder.append(nameSpannable);
        builder.append(nameSpannable2);

        return builder;
    }
}
