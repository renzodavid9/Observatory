package com.example.renzo.observatoryclient2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renzo on 3/11/2015.
 */
public class Drugstore {
    private int code;
    private String name;
    private double lat;
    private double log;
    private String direction;
    private String shortname;
    private List<Medicine> medicines;

    public Drugstore() {
        medicines = new ArrayList<>();
        direction = "DIRECCION";
    }

    public Drugstore(String name, int code, double lat, double log,String direction) {
        this.name = name;
        this.code = code;
        this.lat = lat;
        this.log = log;
        medicines = new ArrayList<>();
        this.direction = direction;
    }

    public int getCode() {
        return code;
    }

    public void insertMedicine(Medicine m){
        medicines.add(m);
    }

    public int getMedicinesSize(){
        return medicines.size();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }


    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
