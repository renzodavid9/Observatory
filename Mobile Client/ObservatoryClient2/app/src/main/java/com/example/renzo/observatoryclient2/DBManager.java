package com.example.renzo.observatoryclient2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Renzo on 10/11/2015.
 */
public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Observatory.db";
    public static final int DATABASE_VERSION = 1;

    public DBManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(ObservatorySchema.DELETE_APARTS_TABLE);
        db.execSQL(ObservatorySchema.CREATE_APARTS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizar la base de datos
    }

}