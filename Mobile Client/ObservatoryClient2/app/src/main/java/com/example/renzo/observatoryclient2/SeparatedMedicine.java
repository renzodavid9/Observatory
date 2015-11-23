package com.example.renzo.observatoryclient2;

/**
 * Created by Renzo on 10/11/2015.
 */
public class SeparatedMedicine {
    private int id;
    private String separate_id;
    private String status;
    private int status_code;
    private String medicine_name;
    private double medicine_price;
    private String drugstore_name;
    private String drugstore_direction;

    public SeparatedMedicine(int id,String separate_id,String medicine_name, double medicine_price, String drugstore_name, String drugstore_direction) {
        this.id = id;
        this.separate_id = separate_id;
        this.status = "PENDIENTE POR APROBACIÓN";
        this.status_code = 0;
        this.medicine_name = medicine_name;
        this.medicine_price = medicine_price;
        this.drugstore_name = drugstore_name;
        this.drugstore_direction = drugstore_direction;
    }

    public void setStatus_APROVED(){
        this.status = "APROBADO POR DROGUERÍA";
        this.status_code = 1;
    }
    public void setStatus_REJECTED(){
        this.status = "RECHAZADO POR DROGUERÍA";
        this.status_code = 2;
    }
    public void setStatus_NORESPONSE(){
        this.status = "SIN RESPUESTA DE DROGUERÍA";
        this.status_code = 3;
    }


    public String toString(){
        return this.medicine_name+" "+medicine_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeparate_id() {
        return separate_id;
    }

    public void setSeparate_id(String separate_id) {
        this.separate_id = separate_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public double getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(double medicine_price) {
        this.medicine_price = medicine_price;
    }

    public String getDrugstore_name() {
        return drugstore_name;
    }

    public void setDrugstore_name(String drugstore_name) {
        this.drugstore_name = drugstore_name;
    }

    public String getDrugstore_direction() {
        return drugstore_direction;
    }

    public void setDrugstore_direction(String drugstore_direction) {
        this.drugstore_direction = drugstore_direction;
    }
}
