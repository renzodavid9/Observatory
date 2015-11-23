package com.example.renzo.observatoryclient2;

/**
 * Created by Renzo on 3/11/2015.
 */
public class Medicine {
    private int id;
    private String name;
    private String invimaCode;
    private int record;
    private String unit;
    private String atc;
    private String atcDescription;
    private String administration;
    private String activePrinciple;
    private String pharmForm;
    private String manufacturer;
    private int drugstore_id;
    private double commercial_price;

    public Medicine() {
        this.name = "prueba";
        this.activePrinciple = "";
        this.commercial_price = 1.00;
    }

    public Medicine(int id, String name, String invimaCode, int record, String unit, String atc, String atcDescription, String administration, String activePrinciple, String pharmForm, String manufacturer, int drugstore_id, double commercial_price) {
        this.id = id;
        this.name = name;
        this.invimaCode = invimaCode;
        this.record = record;
        this.unit = unit;
        this.atc = atc;
        this.atcDescription = atcDescription;
        this.administration = administration;
        this.activePrinciple = activePrinciple;
        this.pharmForm = pharmForm;
        this.manufacturer = manufacturer;
        this.drugstore_id = drugstore_id;
        this.commercial_price = commercial_price;
    }

    public Medicine (Medicine med){
        this.id = med.getId();
        this.name = med.getName();
        this.invimaCode = med.getInvimaCode();
        this.record = med.getRecord();
        this.unit = med.getUnit();
        this.atc = med.getAtc();
        this.atcDescription = med.getAtcDescription();
        this.administration = med.getAdministration();
        this.activePrinciple = med.getActivePrinciple();
        this.pharmForm = med.getPharmForm();
        this.manufacturer = med.getManufacturer();
        this.drugstore_id = med.getDrugstore_id();
        this.commercial_price = med.getCommercial_price();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvimaCode() {
        return invimaCode;
    }

    public void setInvimaCode(String invimaCode) {
        this.invimaCode = invimaCode;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getAtcDescription() {
        return atcDescription;
    }

    public void setAtcDescription(String atcDescription) {
        this.atcDescription = atcDescription;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getActivePrinciple() {
        return activePrinciple;
    }

    public void setActivePrinciple(String activePrinciple) {
        this.activePrinciple = activePrinciple;
    }

    public String getPharmForm() {
        return pharmForm;
    }

    public void setPharmForm(String pharmForm) {
        this.pharmForm = pharmForm;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDrugstore_id() {
        return drugstore_id;
    }

    public void setDrugstore_id(int drugstore_id) {
        this.drugstore_id = drugstore_id;
    }

    public double getCommercial_price() {
        return commercial_price;
    }

    public void setCommercial_price(double commercial_price) {
        this.commercial_price = commercial_price;
    }
}
