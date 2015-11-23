package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MedicineSearchActivity extends Activity {

    private Button searchBtn;
    private TextView nameField;
    private SeekBar searchRatioVW;
    private TextView currentKm;
    private ProgressDialog loadingBar;
    private Button apartBtn;

    private LatLng myLatLng;
    private String medicineName = new String();

    private LocationManager mLocationManager;
    private LocationListener mListener;
    public boolean locationObtained = false;
    private String provider;

    int kmRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_search);
        setup();
    }


    private void setup(){
        searchBtn = (Button)findViewById(R.id.search_btn);
        nameField = (TextView)findViewById(R.id.search_field);
        searchRatioVW = (SeekBar)findViewById(R.id.seekBar);
        currentKm = (TextView)findViewById(R.id.km_ratio);
        apartBtn = (Button)findViewById(R.id.aparts_btn);
        kmRatio = 0;

        LogicCaller.main = this;

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mListener = new MyLocationListener3();
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = mLocationManager.getBestProvider(criteria, true);
        mLocationManager.requestSingleUpdate(criteria,mListener,Looper.myLooper());
        if (mLocationManager.getLastKnownLocation(provider) == null) {
            mLocationManager.requestLocationUpdates(provider, 0, 0, mListener);
        }else{
            getLocation();
            locationObtained = true;
        }
        searchBtn.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                if (nameField.getText().length()>0 && kmRatio>=0) {
                    medicineName = nameField.getText().toString();
                    if (locationObtained) {
                        LoadingBarAsynch bar = new LoadingBarAsynch(MedicineSearchActivity.this, "Buscando medicamento...", "Por favor espere mientras el Observatorio procesa su solicitud...");
                        bar.execute();
                        getLocation();
                    }else{
                        makeDialog("No hemos obtenido su ubicación actual aún, intente nuevamente.");
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Verifique el nombre del medicamento y el radio de búsqueda.", Toast.LENGTH_LONG).show();
                }
            }
        });

        apartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SeparateMedicineAtivity.class);
                startActivity(intent);
            }
        });

        searchRatioVW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                kmRatio = progress;
                currentKm.setText(kmRatio + " KM");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }



    private class LoadingBarAsynch extends AsyncTask<Void, Integer, Void>
    {
        private Context context;
        private String title;
        private String message;
        private ProgressDialog loadingBar;
        private String wsResult;
        private int responseCode;
        private String medicines;
        private String drugstores;
        private int response_code;
        private String message_code;

        public  LoadingBarAsynch (Context context, String title, String message){
            this.context = context;
            this.title = title;
            this.message = message;
        }
        @Override
        protected void onPreExecute()
        {
            getLocation();

            this.loadingBar = ProgressDialog.show(context,title,message, false, false);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            boolean sw = true;
            int con = 0;
            SearchMedicineWS myRequest = new SearchMedicineWS(myLatLng.latitude,myLatLng.longitude,medicineName,kmRatio);
            myRequest.callingWS();
            responseCode = myRequest.getStatus2();
            drugstores = myRequest.getDrugstores();
            medicines = myRequest.getMedicines();
            response_code = myRequest.getResponse_code();
            message_code = myRequest.getMessage_code();
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
            verifyResponse(responseCode,drugstores,medicines,response_code,message_code);
        }
    }
    public void myMakeText(String text){
        Toast.makeText(getBaseContext(),text,Toast.LENGTH_LONG).show();
    }

    public void getLocation (){

        myLatLng = new LatLng(mLocationManager.getLastKnownLocation(provider).getLatitude(), mLocationManager.getLastKnownLocation(provider).getLongitude());
        mLocationManager.removeUpdates(mListener);
        //mLocationManager.removeGpsStatusListener(mListener);

    }

    public void verifyResponse(int code,String drugstores, String medicines, int response_code, String message_code){
        LogicCaller.setMyPosition(myLatLng);
        myMakeText("Response_code: "+response_code+" code: "+code);
        if (code == 0){
            if (response_code == 0) {
                LogicCaller.buildDrugstores(drugstores, medicines);
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }else if (response_code == 1){
                //no se encontró el medicamento
                makeDialog("No se encontró el medicamento en ninguna droguería.");
            }else{
                //no se encontraron droguerías cerca
                makeDialog("No se encontraron droguerías cercanas.");
            }
        }else{
            makeDialog("Algo ha salido mal con su búsqueda.\nIntentelo de nuevo mas tarde.\nCODIGO: "+code+"\n");
            //myMakeText();
        }
        /*
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);*/

    }

    public void makeNotification (String id){
        int mId = 001;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.pills)
                        .setContentTitle("Mensaje de droguería.")
                        .setContentText("Solicitud "+id+" actualizada.");
        Intent resultIntent = new Intent(this,SeparateMedicineAtivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MedicineSearchActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mId, mBuilder.build());
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    public void makeDialog (String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                message)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                            }
                        });
        builder.setTitle("Error!!");

        builder.setIcon(R.drawable.pills);
        AlertDialog alert = builder.create();
        builder.show();
    }

    public class MyLocationListener3 implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            locationObtained = true;
            //Toast.makeText(getBaseContext(),"agloooooo",Toast.LENGTH_LONG).show();
            //Log.v("ALERT", "entreeee----------------------------------");
            //myLatLng = new LatLng(loc.getLatitude(),loc.getLongitude());
            //Log.v("ALERT", "saliiiii----------------------------------");

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

}
