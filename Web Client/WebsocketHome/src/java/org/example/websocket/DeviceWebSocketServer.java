/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.websocket;

import java.io.StringReader;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import org.example.model.Device;
import org.jboss.logging.Logger;
/**
 *
 * @author Renzo
 */
@Named
@Singleton
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {

    
    
    
    private static DeviceSessionHandler sessionHandler;
    
    @OnOpen
    public void open(Session session) {
        System.out.println("OnOpen--- "+session.getId());
        
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("OnClose--- "+session.getId());
        sessionHandler.removeSession(session);
        
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Logger.Level.FATAL, error);
        System.out.println("OnError---");
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("OnMessage1---: "+message+" ID: "+session.getId());
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            System.out.println("OnMessage1 Entro---");
            JsonObject jsonMessage = reader.readObject();
            System.out.println("OnMessage1 Sale Reader---");
            if ("add".equals(jsonMessage.getString("action"))) {
                Device device = new Device();
                device.setName(jsonMessage.getString("name"));
                device.setDescription(jsonMessage.getString("description"));
                device.setType(jsonMessage.getString("type"));
                device.setStatus("Off");
                System.out.println("Antes de");
                sessionHandler.addDevice(device);
                System.out.println("Despues de");
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeDevice(id);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.toggleDevice(id);
            }
            
            if ("login".equals(jsonMessage.getString("action"))) {
                System.out.println("login---");
                String user = (String) jsonMessage.getString("user");
                System.out.println("ANTES DE---");
                sessionHandler.associateSession(session.getId(),user);
                System.out.println("Associated! OK---");
            }
            
            if ("answer".equals(jsonMessage.getString("action"))) {
                String user = jsonMessage.getString("user");
                int value = jsonMessage.getInt("value");
                int id = jsonMessage.getInt("id");
                sessionHandler.changeState(user,id,value);
            }
            
            System.out.println("OnMessage2---");
        }
    }
    
    
    public int num(){
        return sessionHandler.getDevices().size();
    }
    
    public int addingDeviceFromWS(String name){
        Device device = new Device();
        device.setName(name);
        device.setDescription("Comming from cellphone");
        device.setType("Electronics");
        device.setStatus("Off");
        System.out.println("Antes de");
        sessionHandler.addDevice(device);
        System.out.println("Despues de");
        return 1;
    }
    public int separateMedicine(String name, String medicine, Double price,String key){
        int n;
        try{
            n = sessionHandler.sendMedicine(name,medicine);
        }catch(Exception e){
            n = -1;
        }
        return n;
    }
            
}
