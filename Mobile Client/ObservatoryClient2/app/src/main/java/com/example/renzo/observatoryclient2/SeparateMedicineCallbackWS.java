package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Renzo on 11/11/2015.
 */
public class SeparateMedicineCallbackWS extends AsyncTask<Void, Void, Void> {


    private static final String NAMESPACE = "http://xmlns.oracle.com/bpmn/bpmnProcess/SeparateMedicineSyn";
    private static final String MAIN_REQUEST_URL = "http://192.168.0.18:7001/BPMServices/GetSeparateStatus/GetSeparateStatus_Proxy";
    private static final String SOAP_ACTION = "start";
    private static final String METHOD_NAME = "start";

    String assignedId;
    String resultCode;
    Activity act;

    public SeparateMedicineCallbackWS(String assignedId, Activity act) {
        this.assignedId = assignedId;
        this.act = act;
        resultCode="0";
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        finish();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        int cont=0;
        do{
            cont +=1;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeWS();

        }while(resultCode.equalsIgnoreCase("0") && cont<=17);
        if (cont==18){
            resultCode="3";
        }
        return null;
    }

    private final HttpTransportSE getHttpTransportSE() {

        HttpTransportSE ht = new HttpTransportSE(MAIN_REQUEST_URL,300000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }
    private final SoapSerializationEnvelope getSoapSerializationEnvelope( SoapObject request){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);
        return envelope;
    }

    private Void executeWS(){
        String data = null;
        String theResult="";
        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        request.addProperty("key",assignedId);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = getHttpTransportSE();
        try {
            ht.call(SOAP_ACTION, envelope);
        }catch (Exception e){
            theResult += e.toString();
            return null;
        }
        Object result = null;
        try{
            //result = (Vector) envelope.getResponse();
            result = envelope.getResponse();
        } catch (SoapFault e){
            //theResult = "Exception 2";
            theResult += e.toString();
            return null;
        }
        if (result == null){
            theResult += "NULL";
        }else {
            theResult = "";
            theResult += result.toString();
            resultCode = theResult;
        }
        return null;
    }

    private void finish() {
        LogicCaller.updateRegistry(resultCode,assignedId);
    }

}
