package com.example.cubelearner.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptGroup;

import com.example.cubelearner.AlgorithmActivity;
import com.example.cubelearner.MainActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLInput;
import java.util.ArrayList;

public class TimeTable extends SQLiteOpenHelper {

    //db infos
    private static final String dbName="CubeLearner_data";
    private static final int dbVersion = 1;

    //tables names
    private static final String timeTable = "time";
    //private static final String algorithmTable = "algorithm";

    //time table columns
    private static final String timeId = "id";
    private static final String timePuzzle = "puzzle";
    private static final String timeTime = "time";
    private static final String timePrecise = "precise";
    private static final String timeScramble = "scramble";

    //algorithm table columns
   /* private static final String algorithmId = "id";
    private static final String algorithmType = "type";
    private static final String algorithmAlgorithm = "algorithm";*/

    public TimeTable(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TIME_TABLE = "CREATE TABLE IF NOT EXISTS " + timeTable +
                "(" +
                    timeId + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    timePuzzle + " VARCHAR," +
                    timeTime + " VARCHAR," +
                    timePrecise + " LONG," +
                    timeScramble + " VARCHAR" +
                ")";
        /*String CREATE_ALGORITHM_TABLE = "CREATE TABLE IF NOT EXISTS " + algorithmTable +
                "(" +
                    algorithmId + " VARCHAR," +
                    algorithmType + " VARCHAR," +
                    algorithmAlgorithm + " VARCHAR" +
                ")";*/
        db.execSQL(CREATE_TIME_TABLE);
        //db.execSQL(CREATE_ALGORITHM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + timeTable);
            //db.execSQL("DROP TABLE IF EXISTS " + algorithmTable);
            onCreate(db);
        }
    }

    public String getLastTime(String puzzle){
        String QUERY = "SELECT * FROM " + timeTable + " WHERE puzzle='" + puzzle + "' ORDER BY " + timeId + " DESC LIMIT 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(timeTime));
        else
            return "No time left";
    }

    public String getBestTime(String puzzle){
        String QUERY = "SELECT * FROM " + timeTable + " WHERE puzzle='" + puzzle + "' ORDER BY " + timePrecise + " ASC LIMIT 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(timeTime));
        else
            return "No time left";
    }

    public void addTime(String puzzle, String time, long precise, String scramble){
        ContentValues values = new ContentValues();
        values.put(timePuzzle, puzzle);
        values.put(timeTime, time);
        values.put(timePrecise, precise);
        values.put(timeScramble, scramble);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(timeTable, null, values);
    }

    //may become useless methods
    /*
    public ArrayList<Algorithm> getAlgorithms(String type){
        String id;
        String algo;
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        String QUERY = "SELECT * FROM " + algorithmTable +
                " WHERE type ='" + type + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){
            do {
                id = cursor.getString(cursor.getColumnIndex(algorithmId));
                algo = cursor.getString(cursor.getColumnIndex(algorithmAlgorithm));
                algorithms.add(new Algorithm(id, algo));
            } while(cursor.moveToNext());
        }
        return algorithms;
    }

    public void createAlgorithmDb(String type){
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(AlgorithmActivity.getContext().get));
            SQLiteDatabase db = getWritableDatabase();
            String line = " ";
            String[] values;
            ContentValues contentValues = new ContentValues();
            while((line = br.readLine()) != null){
                values = line.split(";");
                contentValues.put(algorithmId, values[0]);
                contentValues.put(algorithmType, type);
                contentValues.put(algorithmAlgorithm, values[1]);
                db.insert(algorithmTable, null, contentValues);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
