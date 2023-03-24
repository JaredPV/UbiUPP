package com.example.ubiupp;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAccess{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAccess instance;
    Cursor cursor = null;

    private  static final  String TABLE_BUILDING = "edificio";
    private  static  final String KEY_ID = "idEdificio";
    private  static  final String KEY_NOMBRE = "nombreEdificio";
    private  static  final String KEY_DESCRIPCION = "descripcionEdificio";
    private  static  final String KEY_UBICACION = "ubicacionEdificio";
    private  static  final String KEY_IMAGE = "imagen";

    private static final String TABLE_AULA = "lugar";
    private static final String KEY_LUGAR = "nombreLugar";

    private DataBaseAccess(Context context){
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static DataBaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    public BuildingData getBuilding(int id){


        cursor = db.query(TABLE_BUILDING, new String[] {KEY_ID, KEY_NOMBRE, KEY_DESCRIPCION,KEY_UBICACION,KEY_IMAGE}, KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }

        BuildingData buildingData = new BuildingData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return  buildingData;
    }

    public List<BuildingData> getBuildings(){
        List<BuildingData> buildingDataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_BUILDING;
        cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                BuildingData buildingData = new BuildingData();
                buildingData.setBuildingId(cursor.getString(0));
                buildingData.setBuildingName(cursor.getString(1));
                buildingData.setBuildingDescription(cursor.getString(2));
                buildingData.setBuildingLocation(cursor.getString(3));
                buildingData.setBuildingImage(cursor.getString(4));

                buildingDataList.add(buildingData);

            }while (cursor.moveToNext());
        }
        return buildingDataList;
    }

    public List<AulaData> getAula(int idEdificio){
        List<AulaData> aulaDataList = new ArrayList<>();
        cursor = db.query(TABLE_AULA, new String[]{KEY_LUGAR,KEY_ID},KEY_ID+"=?",
                new String[]{String.valueOf(idEdificio)},null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                AulaData aulaData = new AulaData();
                aulaData.setAula(cursor.getString(0));
                aulaData.setEdificio(cursor.getString(1));

                aulaDataList.add(aulaData);
            }while (cursor.moveToNext());
        }
        return  aulaDataList;
    }

    public List<AulaData> buscarAula(){
        List<AulaData> aulaDataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_AULA;
        cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                AulaData aulaData = new AulaData();
                aulaData.setAula(cursor.getString(0));
                aulaData.setEdificio(cursor.getString(1));

                aulaDataList.add(aulaData);
            }while (cursor.moveToNext());
        }
        return  aulaDataList;
    }
}
