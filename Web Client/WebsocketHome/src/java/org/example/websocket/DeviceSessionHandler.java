/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.faces.bean.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import org.example.model.Device;
import org.jboss.logging.Logger;

/**
 *
 * @author Renzo
 */
@ApplicationScoped
public class DeviceSessionHandler {
    
    private static int deviceId = 0;  
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private static Set<Device> devices = Collections.synchronizedSet(new HashSet<Device>());
    private static Map<String,String> users = Collections.synchronizedMap(new HashMap<String,String>());
    private static Map<String,String> ids = Collections.synchronizedMap(new HashMap<String,String>());
    private static Map<String, List<Integer> > lista =Collections.synchronizedMap(new HashMap<String,List<Integer> > ());
    
    static void addSession(Session session) {
        sessions.add(session);
        System.out.println("Conectados: "+sessions.size());
        for (Device device : devices){
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session,addMessage);
        }
    }

    public static void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public static List getDevices() {
        return new ArrayList<>(devices);
    }

    public static void addDevice(Device device) {
        device.setId(deviceId);
        devices.add(device);
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }

    public static void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public static void toggleDevice(int id) {
    }

    private static Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    private static JsonObject createAddMessage(Device device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
    }

    private static void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private static void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Logger.Level.FATAL,ex);
        }
    }

    public static void associateSession(String id, String user) {
        System.out.println("ID: "+id+" USER: "+user);
        users.put(user, id);
        ids.put(id, user);
        lista.put(user, Collections.synchronizedList(new ArrayList<Integer>()));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static int sendMedicine(String name, String medicine) throws InterruptedException {
        if (users.containsKey(name) ){
            String id = users.get(name);
            for (Session s : sessions){
                if (s.getId().equals(id)){
                    int id2 = lista.get(name).size();
                    Device d = new Device();
                    d.setDescription("PRUEBA");
                    d.setId(id2);
                    d.setName(medicine);
                    d.setStatus("On");
                    d.setType("Other");
                    System.out.println("SESSION: "+s);
                    sendToSession(s, createAddMessage(d));
                    lista.get(name).add(0);
                    int cont = 0;
                    while (lista.get(name).get(id2)==0 && cont <= 10){
                        Thread.sleep(10000);
                        cont++;
                        //lista.get(name).set(id2, 1);
                    }
                    return lista.get(name).get(id2);
                }
            }
        }
        return 0;
    }

    public static void changeState(String user, int id, int value){
        lista.get(user).set(id, value);
    }
    
}
