package com.example.renzo.observatoryclient2;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;


/**
 * Created by Renzo on 4/11/2015.
 */
public class SearchMedicineWS extends AsyncTask<Void, Void, Void> {


    private static final String NAMESPACE = "http://xmlns.oracle.com/bpmn/bpmnProcess/FindMedicine";
    private static final String MAIN_REQUEST_URL = "http://192.168.0.18:7001/BPMServices/FindMedicine/FindMedicine_Proxy";
    private static final String SOAP_ACTION = "start";
    private static final String METHOD_NAME = "start";
    SoapObject request;
    private boolean flagComplete;
    private int status;
    private double lat;
    private double log;
    private String medicine;
    private double ratio_search;
    String theResult;
    String drugstores;
    String medicines;
    int response_code;
    String message_code;

    public SearchMedicineWS(double lat, double log, String medicine, double ratio_search) {
        this.lat = lat;
        this.log = log;
        this.medicine = medicine;
        this.ratio_search = ratio_search;
        flagComplete = false;
        status = 0;
        theResult = "";
    }

    public String getMessage_code() {
        return message_code;
    }

    public int getResponse_code() {
        return response_code;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getDrugstores() {
        return drugstores;
    }

    public boolean isComplete (){
        return flagComplete;
    }

    public String getTheResult() {
        return theResult;
    }

    public int getStatus2(){
        return status;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        flagComplete=true;
        //textView.setText(theResult);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        callingWS();
        return null;
    }

    private final SoapSerializationEnvelope getSoapSerializationEnvelope( SoapObject request){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);
        return envelope;
    }

    private final HttpTransportSE getHttpTransportSE() {

        HttpTransportSE ht = new HttpTransportSE(MAIN_REQUEST_URL,300000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }

    public Void callingWS(){

        String data = null;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("user_latitude",Double.toString(lat));
        request.addProperty("user_longitude",Double.toString(log));
        request.addProperty("user_ratio_search",Double.toString(ratio_search));
        request.addProperty("user_medicine_name",medicine);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);


        HttpTransportSE ht = getHttpTransportSE();
        try {
            ht.call(SOAP_ACTION, envelope);
        }catch (Exception e){
            theResult = e.toString();
            status = 1;
            flagComplete=true;
            return null;
        }
        Vector result = null;
        try{
            result = (Vector) envelope.getResponse();
        } catch (SoapFault e){
            theResult = "Exception 2";
            status = 2;
            flagComplete=true;
            return null;
        }
        if (result == null){
            theResult = "NULL";
        }else {
            theResult = "";
            for (int i=0; i<result.size(); i++){
                theResult += result.get(i) +"\n";
            }
            theResult = result.toString()+ " -- ANS";
            if (result.size()==4){
                drugstores = result.get(0)+"";
                medicines = result.get(1)+"";
                response_code = Integer.parseInt(result.get(2)+"");
                message_code = result.get(3)+"";
            }else{

            }
        }
        flagComplete=true;
        return null;

    }
}
