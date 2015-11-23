package com.example.renzo.observatoryclient2;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

/**
 * Created by Renzo on 10/11/2015.
 */
public class SeparateMedicineWS extends AsyncTask<Void, Void, Void> {

    private static final String NAMESPACE = "http://xmlns.oracle.com/bpmn/bpmnProcess/SeparateMedicineSyn";
    private static final String MAIN_REQUEST_URL = "http://192.168.0.18:7001/BPMServices/SeparateMedicineSyn/SeparateMedicineSyn_Proxy";
    private static final String SOAP_ACTION = "start";
    private static final String METHOD_NAME = "start";

    SoapObject request;
    private boolean flagComplete;
    int status;
    String responseCode;

    Medicine medicine;

    public SeparateMedicineWS (Medicine medicine){
        this.medicine = medicine;
        flagComplete = false;
        status = 0;
        responseCode = "-1";
    }

    public int getStatus2(){
        return status;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        flagComplete=true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        executeWS();
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

    public Void executeWS(){

        String data = null;
        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        request.addProperty("medicine_name",medicine.getName());
        request.addProperty("medicine_price",Double.toString(medicine.getCommercial_price()));
        request.addProperty("drugstore_id",Integer.toString(medicine.getDrugstore_id()));
        request.addProperty("user_id","56");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);


        HttpTransportSE ht = getHttpTransportSE();
        try {
            ht.call(SOAP_ACTION, envelope);
        }catch (Exception e){
            status = 1;
            flagComplete=true;
            return null;
        }
        //SoapPrimitive result = null;
        Object result = null;
        try{
            result = envelope.getResponse();
        } catch (Exception e){
            status = 2;
            flagComplete=true;
            return null;
        }
        if (result == null){
            status = 3;
        }else {
            responseCode = result.toString();
        }
        flagComplete=true;
        return null;
    }

    public String getResponseCode(){
        return responseCode;
    }
}
