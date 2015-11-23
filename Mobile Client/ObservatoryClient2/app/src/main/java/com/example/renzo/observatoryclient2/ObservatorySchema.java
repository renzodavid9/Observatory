package com.example.renzo.observatoryclient2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Renzo on 10/11/2015.
 */
public class ObservatorySchema {
    public static final String QUOTES_TABLE_NAME = "Aparts";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    public static class ColumnAparts{
        public static final String ID_APART = BaseColumns._ID;
        public static final String MEDICINE_NAME = "medicine";
        public static final String MEDICINE_PRICE = "price";
        public static final String DRUGSTORE_NAME = "drugstore";
        public static final String DRUGSTORE_DIRECTION = "direction";
        public static final String APART_CONSECUTIVE = "consecutive";
        public static final String APART_STATUS = "status";
        public static final String APART_STATUS_CODE = "status_code";
    }
    public static final String DELETE_APARTS_TABLE = "drop table "+QUOTES_TABLE_NAME;
    public static final String CREATE_APARTS_SCRIPT =
            "create table if not exists "+QUOTES_TABLE_NAME+"(" +
                    ColumnAparts.ID_APART+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnAparts.APART_CONSECUTIVE+" "+STRING_TYPE+" not null,"+
                    ColumnAparts.MEDICINE_NAME+" "+STRING_TYPE+" not null," +
                    ColumnAparts.MEDICINE_PRICE+" "+REAL_TYPE+" not null," +
                    ColumnAparts.DRUGSTORE_NAME+" "+STRING_TYPE+" not null," +
                    ColumnAparts.APART_STATUS+" "+STRING_TYPE+" not null," +
                    ColumnAparts.APART_STATUS_CODE+" "+INT_TYPE+" not null," +
                    ColumnAparts.DRUGSTORE_DIRECTION+" "+STRING_TYPE+" not null)";





    private DBManager openHelper;
    private SQLiteDatabase database;

    public ObservatorySchema(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new DBManager(context);
        database = openHelper.getWritableDatabase();
    }

    public void saveApartRow(String apart_consecutive,String medicine_name,double medicine_price, String drugstore_name, String drugstore_direction){

        ContentValues values = new ContentValues();

        values.put(ColumnAparts.APART_CONSECUTIVE,apart_consecutive);
        values.put(ColumnAparts.MEDICINE_NAME,medicine_name);
        values.put(ColumnAparts.MEDICINE_PRICE,medicine_price);
        values.put(ColumnAparts.DRUGSTORE_NAME,drugstore_name);
        values.put(ColumnAparts.DRUGSTORE_DIRECTION, drugstore_direction);
        values.put(ColumnAparts.APART_STATUS,"PENDIENTE POR APROBACIÓN");
        values.put(ColumnAparts.APART_STATUS_CODE,0);

        database.insert(QUOTES_TABLE_NAME,null,values);
    }

    public Cursor getAllAparts(){
        return database.rawQuery("select * from "+QUOTES_TABLE_NAME,null);
    }
    public void updateRegistry(String resultCode, String assignedId,int apart_status) {
        if (resultCode.equalsIgnoreCase("2")||resultCode.equalsIgnoreCase("3")){
            resultCode="-1";
        }
        String query = "update "+QUOTES_TABLE_NAME+" set "+ColumnAparts.APART_STATUS_CODE+" = "+apart_status+
                " where "+ColumnAparts.APART_CONSECUTIVE+" = "+assignedId;
        database.execSQL(query);
    }

    public void deleteRegistry(int id) {
        String query = "delete from "+QUOTES_TABLE_NAME+" where "+ColumnAparts.ID_APART+" = "+id;
        database.execSQL(query);
    }

}
