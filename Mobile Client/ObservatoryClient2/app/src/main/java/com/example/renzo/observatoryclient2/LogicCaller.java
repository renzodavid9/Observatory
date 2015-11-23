package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renzo on 3/11/2015.
 */
public class LogicCaller {

    public static Drugstore drug;
    public static LatLng myLatLng;
    public static List<Drugstore> drugstoresList;
    public static List<Drugstore> manufacturersList;
    public static Drugstore laboratory;
    public static String active_component;
    public static MedicineSearchActivity main;
    public static boolean processRunning = false;
    public static LatLng getMyPosition(){
        return myLatLng;
    }
    public static void setMyPosition(LatLng pos){
        myLatLng = pos;
    }

    public static List<Drugstore> getDrugstoresList(){
        return drugstoresList;
    }

    public static List<Drugstore> getDrugstores_test(){

        Drugstore d1 = new Drugstore("Drogueria La esquina",1, 4.6184545,-74.0710062, "Cll 31 #14 - 52");
        d1.insertMedicine(new Medicine(1,"ADVIL MAX","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,10500.00));
        d1.insertMedicine(new Medicine(1,"ADVIL NOCHE","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,11900.00));
        d1.insertMedicine(new Medicine(1,"ADVIL ULTRA","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,15500.00));

        Drugstore d2 = new Drugstore("Drogueria Suarez",2,4.6182861,-74.0722508, "Kr 16 #26 - 13");
        d2.insertMedicine(new Medicine(1,"ADVIL MAX","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,9500.00));

        Drugstore d3 = new Drugstore("Drogueria Pto Farma",3,4.6200586,-74.0696973, "Kr 13A # 32 - 85");
        d3.insertMedicine(new Medicine(1,"ADVIL MAX","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,10500.00));

        Drugstore d4 = new Drugstore("Drogueria Hospital San Ignacio 24",4,4.6298142,-74.0649015,"Kr 7 # 43 - 15");
        d4.insertMedicine(new Medicine(1,"ADVIL CHILDREN SUSPENSION ORAL PARA NIÑOS","",-1,"U","atc","atc descp","Oral","IBUPROFENO",
                                        "empaque","Pfizer S.A.S",4,14500.00));
        d4.insertMedicine(new Medicine(1, "ADVIL FASTGEL", "", -1, "U", "atc", "atc descp", "Oral", "IBUPROFENO",
                "empaque", "Pfizer S.A.S", 4, 25000.0));

        drugstoresList = new ArrayList<>();

        drugstoresList.add(d1);
        drugstoresList.add(d2);
        drugstoresList.add(d3);
        drugstoresList.add(d4);

        return drugstoresList;
    }

    public static void buildDrugstores( String drugstores, String medicines ){
        constructDrugstores(drugstores);
        if (drugstoresList.size()>0){
            constructMedicines(medicines);
            cleanDrugstores();
        }
    }

    private static void cleanDrugstores() {
        for (int i=0; i<drugstoresList.size(); i++){
            Drugstore drugstore = drugstoresList.get(i);
            if (drugstore.getMedicinesSize()<=0){
                drugstoresList.remove(i);
                i--;
            }
        }
    }

    public static void constructDrugstores(String response){
        drugstoresList = new ArrayList<>();
        response = response.substring(8);
        String [] split = response.split("drugstores=anyType");
        int start=0,end=0;
        for (String s : split){
            if (s.length()>0){
                start = s.indexOf('{');
                end = s.indexOf('}');
                s = s.substring(start+1, end);
                System.out.println("S: "+s);
                String[] properties = s.split(";");
                Drugstore drugstore = new Drugstore();
                for (String property : properties){
                    property = property.trim();
                    String[] values = property.split("=");
                    if (values.length==2){
                        String field = values[0];
                        String value = values[1];
                        if (field.equalsIgnoreCase("ID")){
                            drugstore.setCode(Integer.parseInt(value));
                        }
                        if (field.equalsIgnoreCase("NAME")){
                            drugstore.setName(value);
                        }
                        if (field.equalsIgnoreCase("SHORTNAME")){
                            drugstore.setShortname(value);
                        }
                        if (field.equalsIgnoreCase("LATITUDE")){
                            drugstore.setLat(Double.parseDouble(value));
                        }
                        if (field.equalsIgnoreCase("LONGITUDE")){
                            drugstore.setLog(Double.parseDouble(value));
                        }
                    }
                }
                drugstoresList.add(drugstore);
            }
        }
    }

    public static void constructMedicines (String response){
        response = response.substring(8);
        String [] split = response.split("medicines=anyType");
        int start=0,end=0;
        for (String s : split){
            if (s.length()>0){
                if (s.charAt(s.length()-1)=='}')s = s.substring(0, s.length()-1);
                start = s.indexOf('{');
                end = s.lastIndexOf('}');
                s = s.substring(start+1, end);
                System.out.println("Med: "+s);
                String[] properties = s.split(";");
                Medicine medicine = new Medicine();
                for (String property : properties){
                    property = property.trim();
                    String[] values = property.split("=");
                    if (values.length==2){
                        String field = values[0];
                        String value = values[1];
                        if (field.equalsIgnoreCase("NAME")){
                            medicine.setName(value);
                        }
                        if (field.equalsIgnoreCase("INVIMA_CODE")){
                            medicine.setInvimaCode( value.equalsIgnoreCase("anyType{}")?"N/A":value );
                        }
                        if (field.equalsIgnoreCase("RECORD")){
                            medicine.setRecord(value.equalsIgnoreCase("anyType{}") ? 0 : Integer.parseInt(value));
                        }
                        if (field.equalsIgnoreCase("UNIT")){
                            medicine.setUnit(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ATC")){
                            medicine.setAtc(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ATC_DESCRIPTION")){
                            medicine.setAtcDescription(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ADMINISTRATION")){
                            medicine.setAdministration(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ACTIVE_PRINCIPLE")){
                            medicine.setActivePrinciple(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("PHARMACEUTICAL_FORM")){
                            medicine.setPharmForm(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("MANUFACTURER")){
                            medicine.setManufacturer(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("DRUGSTORE_ID")){
                            medicine.setDrugstore_id(Integer.parseInt(value));
                        }
                        if (field.equalsIgnoreCase("COMMERCIAL_PRICE")){
                            medicine.setCommercial_price(Double.parseDouble(value));
                        }
                    }
                }
                for (int i=0; i<drugstoresList.size(); i++){
                    Drugstore drugstore = drugstoresList.get(i);
                    if (drugstore.getCode()==medicine.getDrugstore_id()){
                        drugstore.getMedicines().add(medicine);
                        break;
                    }
                }
            }
        }
    }

    public static void createApart(Medicine medicine,Context context,String responseCode) {
        Medicine med = new Medicine(medicine);
        List<Drugstore>drugstoresList = LogicCaller.getDrugstoresList();
        Drugstore drugstore=null;
        for (int i=0; i<drugstoresList.size(); i++){
            if (drugstoresList.get(i).getCode()==medicine.getDrugstore_id()){
                drugstore = drugstoresList.get(i);
                break;
            }
        }
        ObservatorySchema dataSource = new ObservatorySchema(context);
        med.setName(med.getName()+" "+med.getPharmForm());
        dataSource.saveApartRow(responseCode, med.getName(), medicine.getCommercial_price(), drugstore.getName(), drugstore.getDirection());
    }

    public static int getNumAparts(Context context){
        ObservatorySchema dataSource = new ObservatorySchema(context);
        return dataSource.getAllAparts().getCount();
    }

    public static List<SeparatedMedicine> getAparts(Context context){

        ObservatorySchema dataSource = new ObservatorySchema(context);
        Cursor cur = dataSource.getAllAparts();
        List<SeparatedMedicine> list = new ArrayList<>();
        while (cur.moveToNext()){
            SeparatedMedicine separated = new SeparatedMedicine(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getDouble(3),cur.getString(4),cur.getString(7));
            int status_code = cur.getInt(6);
            if (status_code==1){
                separated.setStatus_APROVED();
            }
            if (status_code==2){
                separated.setStatus_REJECTED();
            }
            if (status_code==3){
                separated.setStatus_NORESPONSE();
            }
            list.add(separated);
        }
        return list;
    }

    public static void updateRegistry(String resultCode, String assignedId) {
        ObservatorySchema dataSource = new ObservatorySchema(main);
        if (resultCode.trim().equalsIgnoreCase("2")){
            dataSource.updateRegistry(resultCode,assignedId,2);
        }else if(resultCode.trim().equalsIgnoreCase("3")){
            dataSource.updateRegistry(resultCode,assignedId,3);
        }else{
            dataSource.updateRegistry(resultCode,assignedId,1);
        }
        main.makeNotification(assignedId);
    }
    public static void deleteRegistry(int id, Context context){
        ObservatorySchema dataSource = new ObservatorySchema(context);
        dataSource.deleteRegistry(id);
    }

    public static void constructGenerics(String generics, String manufacturers, String act_comp) {
        active_component = act_comp;
        constructManufacturers(manufacturers);
        if (manufacturersList.size()>0){
            constructGenerics2(generics);
        }
    }

    private static void constructGenerics2(String response) {
        response = response.substring(8);
        String[] split = response.split("medicines=anyType");
        int start = 0, end = 0;
        for (String s : split) {
            if (s.length() > 0) {
                if (s.charAt(s.length() - 1) == '}') s = s.substring(0, s.length() - 1);
                start = s.indexOf('{');
                end = s.lastIndexOf('}');
                s = s.substring(start + 1, end);
                System.out.println("Med: " + s);
                String[] properties = s.split(";");
                Medicine medicine = new Medicine();
                for (String property : properties) {
                    property = property.trim();
                    String[] values = property.split("=");
                    if (values.length == 2) {
                        String field = values[0];
                        String value = values[1];
                        if (field.equalsIgnoreCase("NAME")) {
                            medicine.setName(value);
                        }
                        if (field.equalsIgnoreCase("INVIMA_CODE")) {
                            medicine.setInvimaCode(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("RECORD")) {
                            medicine.setRecord(value.equalsIgnoreCase("anyType{}") ? 0 : Integer.parseInt(value));
                        }
                        if (field.equalsIgnoreCase("UNIT")) {
                            medicine.setUnit(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ATC")) {
                            medicine.setAtc(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ATC_DESCRIPTION")) {
                            medicine.setAtcDescription(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ADMINISTRATION")) {
                            medicine.setAdministration(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("ACTIVE_PRINCIPLE")) {
                            medicine.setActivePrinciple(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("PHARMACEUTICAL_FORM")) {
                            medicine.setPharmForm(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                        if (field.equalsIgnoreCase("MANUFACTURER")) {
                            medicine.setManufacturer(value.equalsIgnoreCase("anyType{}") ? "N/A" : value);
                        }
                    }
                }
                for (int i = 0; i < manufacturersList.size(); i++) {
                    Drugstore drugstore = manufacturersList.get(i);
                    if (drugstore.getName().equalsIgnoreCase(medicine.getManufacturer().toUpperCase())) {
                        drugstore.getMedicines().add(medicine);
                        break;
                    }
                }
            }
        }
    }


    public static void constructManufacturers(String response){
        manufacturersList = new ArrayList<>();
        response = response.substring(8);
        String [] split = response.split("manufacturers=anyType");
        int start=0,end=0;
        for (String s : split){
            if (s.length()>0){
                start = s.indexOf('{');
                end = s.lastIndexOf('}');
                s = s.substring(start+1, end);
                //System.out.println("S: "+s);
                String[] properties = s.split(";");
                Drugstore drugstore = new Drugstore();
                for (String property : properties){
                    property = property.trim();
                    String[] values = property.split("=");
                    if (values.length==2){
                        String field = values[0];
                        String value = values[1];
                        if (field.equalsIgnoreCase("NAME")){
                            drugstore.setName(value);
                        }
                    }
                }
                manufacturersList.add(drugstore);
            }
        }
    }

}
