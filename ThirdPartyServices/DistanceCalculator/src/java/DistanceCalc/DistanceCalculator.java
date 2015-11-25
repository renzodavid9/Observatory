/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistanceCalc;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author renzo
 */
@WebService(serviceName = "DistanceCalculator")
public class DistanceCalculator {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DistanceBetween2Points_km")
    public Double DistanceBetween2Points_km(@WebParam(name = "latitude1") Double latitude1, @WebParam(name = "latitude2") Double latitude2, @WebParam(name = "longitude1") Double longitude1, @WebParam(name = "longitude2") Double longitude2) {
        //TODO write your implementation code here:
        double earth_radius = 6371;
        double dLat = deg2rad(latitude2-latitude1);
        double dLon = deg2rad(longitude2-longitude1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) * 
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = earth_radius * c; // Distance in km
  
        return d;
    }

    private double deg2rad (Double deg){
        return deg * (Math.PI/new Double("180"));
    }
    
}
