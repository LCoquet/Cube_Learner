package com.example.cubelearner.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cubelearner.data.Time;

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

    public ArrayList<Time> getAllTime(String puzzle){
        ArrayList<Time> times = new ArrayList<>();
        String time, scramble;
        long precise;
        String QUERY = "SELECT * FROM " + timeTable + " WHERE puzzle ='" + puzzle + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){
            do{
                time = cursor.getString(cursor.getColumnIndex(timeTime));
                precise = cursor.getLong(cursor.getColumnIndex(timePrecise));
                scramble = cursor.getString(cursor.getColumnIndex(timeScramble));
                times.add(new Time(time, precise, scramble));
            } while(cursor.moveToNext());
        }
        return times;
    }
}
