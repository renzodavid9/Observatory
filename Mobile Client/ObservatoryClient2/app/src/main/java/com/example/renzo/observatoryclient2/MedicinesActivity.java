package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MedicinesActivity extends Activity {

    TextView name;
    TextView direction;
    ListView listView;
    TextView med_name;
    TextView med_active_comp;
    TextView med_price;
    Button genericBtn;
    Button apartButton;


    Drugstore d;
    ArrayList<String> listItems = new ArrayList<>();
    List<Medicine> medicines;
    ArrayAdapter<String> adapter;
    int medicineSelected;
    int statusActivity;
    String responseCodeActivity;
    int responseCodeActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicines, menu);
        return true;
    }


    protected void setup(){
        d = LogicCaller.drug;
        medicineSelected = -1;
        name = (TextView) findViewById(R.id.drug_name_medicines);
        direction = (TextView) findViewById(R.id.drug_direction_medicines);
        listView = (ListView) findViewById(R.id.list_medicines);
        med_name = (TextView) findViewById(R.id.medicine_name);
        med_active_comp = (TextView) findViewById(R.id.medicine_activeComponent);
        med_price = (TextView) findViewById(R.id.medicine_price);
        apartButton = (Button) findViewById(R.id.apartBtn);
        genericBtn = (Button) findViewById(R.id.genericBtn);

        name.setText("Nombre: ");
        direction.setText("Direccion: ");

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

        medicines = d.getMedicines();
        for (Medicine medicine : medicines){
            listItems.add(medicine.getName()+'\n'+medicine.getPharmForm());
        }
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                //view.setSelected(true);
                medicineSelected = position;
                setMedicineData(medicines.get(position));
            }
        });

        apartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apartMedicine();
            }
        });

        genericBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendGeneric();
            }
        });

    }

    private void setMedicineData(Medicine m) {
        med_name.setText("Nombre: ");
        med_active_comp.setText("Componente activo: ");
        med_price.setText("Precio: ");

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String nameD = m.getName();
        if (nameD.length()>35){
            nameD = nameD.substring(0,34);
            nameD = nameD + "...";
        }
        SpannableString nameSpannable= new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(med_name.getText());
        builder.append(nameSpannable);
        med_name.setText(builder, TextView.BufferType.SPANNABLE);

        builder = new SpannableStringBuilder();
        nameD = m.getActivePrinciple();
        nameSpannable = new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(med_active_comp.getText());
        builder.append(nameSpannable);
        med_active_comp.setText(builder, TextView.BufferType.SPANNABLE);

        builder = new SpannableStringBuilder();
        nameD = m.getCommercial_price()+" COP";
        nameSpannable = new SpannableString(nameD);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, nameD.length(), 0);
        builder.append(med_price.getText());
        builder.append(nameSpannable);
        med_price.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void apartMedicine (){
        if (medicineSelected==-1){
            Toast.makeText(getBaseContext(),"No ha seleccionado ningún medicamento",Toast.LENGTH_SHORT).show();
        }else{
            confirmationDialog();
        }
    }

    private void confirmationDialog() {

        String medicineName = medicines.get(medicineSelected).getName();
        String medicinePrice = Double.toString(medicines.get(medicineSelected).getCommercial_price());
        medicineName = medicineName+".\n"+medicines.get(medicineSelected).getPharmForm()+".";
        String message = medicineName+"\n"+"Precio: "+medicinePrice+" COP";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                message)
                .setCancelable(false)
                .setPositiveButton("Apartar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                sendSolicitude();
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                //dialog.cancel();
                            }
                        });
        builder.setTitle("Apartar medicamento");

        builder.setIcon(R.drawable.pills);
        AlertDialog alert = builder.create();
        builder.show();
    }

    private void sendSolicitude() {
        LoadingBarAsynch separateMedicineProcess = new LoadingBarAsynch(MedicinesActivity.this,"Enviando solicitud...","Su solicitud se está enviando, espere un momento...");
        separateMedicineProcess.execute();
    }

    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }


    private class LoadingBarAsynch extends AsyncTask<Void, Integer, Void>
    {
        private Context context;
        private String title;
        private String message;
        private ProgressDialog loadingBar;
        private int status = -1;
        private String responseCode;

        public  LoadingBarAsynch (Context context, String title, String message){
            this.context = context;
            this.title = title;
            this.message = message;
        }
        @Override
        protected void onPreExecute()
        {
            Toast.makeText(getBaseContext(),"llamando ws",Toast.LENGTH_SHORT).show();
            this.loadingBar = ProgressDialog.show(context, title, message, false, false);

        }

        @Override
        protected Void doInBackground(Void... params)
        {
            boolean sw = true;
            int con = 0;
            Medicine med = new Medicine(medicines.get(medicineSelected));
            med.setName(med.getName()+"\n"+med.getPharmForm());
            //SeparateMedicineWS myRequest = new SeparateMedicineWS(medicines.get(medicineSelected));
            SeparateMedicineWS myRequest = new SeparateMedicineWS(med);
            myRequest.executeWS();

            status = myRequest.getStatus2();

            responseCode=myRequest.getResponseCode();

            return null;
        }

        //Update the progress
        @Override
        protected void onProgressUpdate(Integer... values)
        {
        }

        @Override
        protected void onPostExecute(Void result)
        {
            Toast.makeText(getBaseContext(),"salio llamado ws: "+responseCode,Toast.LENGTH_SHORT).show();
            statusActivity=status;
            responseCodeActivity = responseCode;
            if (status == 0){
                if (!responseCode.equalsIgnoreCase("-1")) {
                    Toast.makeText(getBaseContext(),"entro llamado ws",Toast.LENGTH_SHORT).show();
                    LogicCaller.createApart(medicines.get(medicineSelected), MedicinesActivity.this, responseCode);
                    Toast.makeText(getBaseContext(),"salio llamado BD",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    informationDialog("Solicitud enviada.", "Su solicitud ha sido enviada, se le avisará tan pronto la confirmen o rechacen.");
                }else{
                    statusActivity=-1;
                    loadingBar.dismiss();
                    informationDialog("Droguiste no conectado.", "La droguería a la cual está solicitando el medicamento no se encuentra en linea, por favor intentelo de nuevo mas tarde.");
                }
            }else{
                //responseCode = "-0";
                //LogicCaller.createApart(medicines.get(medicineSelected), MedicinesActivity.this,responseCode);
                loadingBar.dismiss();
                //statusActivity = 0;
                informationDialog("Problema con solicitud.","Ocurrio un problema enviando su solicitud, por favor intentelo de nuevo mas tarde.");
            }
        }
    }

    private void informationDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                message)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                if (statusActivity==0){
                                    SeparateMedicineCallbackWS serv = new SeparateMedicineCallbackWS(responseCodeActivity,LogicCaller.main);
                                    serv.execute();
                                    Intent intent = new Intent(getBaseContext(),SeparateMedicineAtivity.class);
                                    startActivity(intent);
                                }

                            }
                        });
        builder.setTitle(title);

        builder.setIcon(R.drawable.pills);
        AlertDialog alert = builder.create();
        builder.show();
    }

    private void recommendGeneric() {
        if (medicineSelected == -1) {
            Toast.makeText(getBaseContext(), "No ha seleccionado ningún medicamento", Toast.LENGTH_SHORT).show();
        } else {
            LoadingBarAsynch2 generateRecomendationProcess = new LoadingBarAsynch2(MedicinesActivity.this,"Enviando solicitud...","Su solicitud se está enviando, espere un momento...");
            generateRecomendationProcess.execute();
        }
    }






    private class LoadingBarAsynch2 extends AsyncTask<Void, Integer, Void>
    {
        private Context context;
        private String title;
        private String message;
        private ProgressDialog loadingBar;
        private int status = -1;
        private int responseCode;
        private String generics;
        private String manufacturers;

        public  LoadingBarAsynch2 (Context context, String title, String message){
            this.context = context;
            this.title = title;
            this.message = message;
        }
        @Override
        protected void onPreExecute()
        {
            this.loadingBar = ProgressDialog.show(context, title, message, false, false);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            boolean sw = true;
            int con = 0;
            GenericRecomendationWS myRequest = new GenericRecomendationWS(medicines.get(medicineSelected).getActivePrinciple());
            myRequest.executeWS();

            status = myRequest.getStatus2();

            responseCode=myRequest.getResponseCode();

            generics=myRequest.getGenerics();
            manufacturers=myRequest.getManufacturers();

            return null;
        }

        //Update the progress
        @Override
        protected void onProgressUpdate(Integer... values)
        {
        }

        @Override
        protected void onPostExecute(Void result)
        {
            loadingBar.dismiss();
            Toast.makeText(getBaseContext(),"salio llamado ws: "+responseCode+" status: "+status,Toast.LENGTH_SHORT).show();
            statusActivity=status;
            responseCodeActivity2 = responseCode;
            if (status == 0){

                if (responseCode==0) {
                    LogicCaller.constructGenerics(generics, manufacturers,medicines.get(medicineSelected).getActivePrinciple().toUpperCase());
                    Intent intent = new Intent(getBaseContext(),ManufacturersActivity.class);
                    loadingBar.dismiss();
                    startActivity(intent);
                    //informationDialog("Solicitud enviada.", "Su solicitud ha sido enviada, se le avisará tan pronto la confirmen o rechacen.");
                }else if (responseCode == 1){
                    statusActivity=-1;
                    loadingBar.dismiss();
                    informationDialog("Error!!", "El componente activo del medicamento no se encuentra registrado en el Observatorio. Intente de nuevo más tarde.");
                }else{
                    informationDialog("Error!!", "No se encontraron sugerencias para el medicamento seleccionado. Intente de nuevo más tarde.");
                }
            }else{
                loadingBar.dismiss();
                informationDialog("Problema con solicitud.","Ocurrio un problema enviando su solicitud, por favor intentelo de nuevo mas tarde.");
            }
        }
    }



}
