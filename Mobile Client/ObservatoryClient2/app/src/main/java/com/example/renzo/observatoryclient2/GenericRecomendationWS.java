package com.example.renzo.observatoryclient2;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

/**
 * Created by Renzo on 20/11/2015.
 */
public class GenericRecomendationWS extends AsyncTask<Void, Void, Void> {

    private static final String NAMESPACE = "http://xmlns.oracle.com/bpmn/bpmnProcess/RecommendGeneric";
    private static final String MAIN_REQUEST_URL = "http://192.168.0.18:7001/BPMServices/RecommendGeneric/RecommendGeneric_Proxy";
    private static final String SOAP_ACTION = "start";
    private static final String METHOD_NAME = "start";
    SoapObject request;
    private String active_component;

    private boolean flagComplete;
    private int status;
    String theResult;
    String manufacturers;
    String generics;
    int response_code;
    String message_code;

    public GenericRecomendationWS(String active_component) {
        this.active_component = active_component;
        flagComplete = false;
        status = 0;
        theResult = "";
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    public Void executeWS(){
        String data = null;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("active_component",active_component);

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
                manufacturers = result.get(0)+"";
                generics = result.get(1)+"";
                response_code = Integer.parseInt(result.get(2)+"");
                message_code = result.get(3)+"";
            }else{

            }
        }
        flagComplete=true;
        return null;
    }

    public int getStatus2(){
        return status;
    }

    public int getResponseCode(){
        return response_code;
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

    public String getGenerics() {
        return generics;
    }

    public void setGenerics(String generics) {
        this.generics = generics;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }
}
