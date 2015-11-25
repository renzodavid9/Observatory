/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.example.websocket.DeviceWebSocketServer;

/**
 *
 * @author renzo
 */
@WebService(serviceName = "messageSender")
public class messageSender {
    
    @Inject
    private DeviceWebSocketServer server;
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addDevice")
    public Integer addDevice(@WebParam(name = "name") String name) {
        //TODO write your implementation code here:
        int answ = server.addingDeviceFromWS(name);
        return answ;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "separateMedicine")
    public Integer separateMedicine(@WebParam(name = "medicine_name") String medicine_name, @WebParam(name = "medicine_price") double medicine_price, @WebParam(name = "drugstore_id") int drugstore_id, @WebParam(name = "reservation_key") String reservation_key) {
        //TODO write your implementation code here:
        System.out.println("ENTRE A SEPARAR------");
        String userName;
        
        if (drugstore_id==1)userName = "renzo";
        else userName = "pedro";
        
        return server.separateMedicine(userName, medicine_name, medicine_price, reservation_key);
    }
    
    
    
    
    
}
