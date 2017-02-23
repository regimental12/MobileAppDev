package com.indievoodoo.richard.weightliftingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Richard on 03/02/2017.
 */

// TODO getweights for graphs


public class DBTools extends SQLiteOpenHelper
{
    public DBTools(Context appContext)
    {
        super(appContext, "WeightLifting.db3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        String Bquery  = "CREATE TABLE Bench (benchWorkOutNo INTEGER PRIMARY KEY, Bench1 INTEGER, Bench2 INTEGER, Bench3 INTEGER, BenchAMRAP INTEGER, BenchWeight REAL )";

        database.execSQL(Bquery);

        String Rquery  = "CREATE TABLE Row (RowWorkOutNo INTEGER PRIMARY KEY, Row1 INTEGER, Row2 INTEGER, Row3 INTEGER, RowAMRAP INTEGER, RowWeight REAL )";

        database.execSQL(Rquery);

        String Squery =  "CREATE TABLE Squat (SquatWorkOutNo INTEGER PRIMARY KEY, Squat1 INTEGER, Squat2 INTEGER, Squat3 INTEGER, SquatAMRAP INTEGER, SquatWeight REAL )";

        database.execSQL(Squery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1)
    {
        String Bquery = "DROP TABLE IF EXISTS Bench";
        database.execSQL(Bquery);

        String Rquery = "DROP TABLE IF EXISTS Row";
        database.execSQL(Rquery);

        String Squery = "DROP TABLE IF EXISTS Squat";
        database.execSQL(Squery);

        onCreate(database);

    }

    public void setweights(String a , String b , String c)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues Bvalues = new ContentValues();
        ContentValues Rvalues = new ContentValues();
        ContentValues Svalues = new ContentValues();

        Bvalues.put("BenchWeight" , a);
        Rvalues.put("RowWeight" , b);
        Svalues.put("SquatWeight" , c);

        database.insert("Bench" , null , Bvalues);
        database.insert("Row" , null , Rvalues);
        database.insert("Squat" , null , Svalues);

        database.close();
    }

    public ArrayList<Float> getweights()
    {
        ArrayList<Float> result = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        final String BselectQuery = "SELECT * FROM Bench";
        Cursor Bcursor = database.rawQuery(BselectQuery, null);
        if(Bcursor.moveToLast())
        {
            result.add(Bcursor.getFloat(5));
        }
        Bcursor.close();

        final String RselectQuery = "SELECT * FROM Row";
        Cursor Rcursor = database.rawQuery(RselectQuery, null);
        if(Rcursor.moveToLast())
        {
            result.add(Rcursor.getFloat(5));
        }
        Rcursor.close();

        final String SselectQuery = "SELECT * FROM Squat";
        Cursor Scursor = database.rawQuery(SselectQuery, null);
        if(Scursor.moveToLast())
        {
            result.add(Scursor.getFloat(5));
        }
        Scursor.close();

        database.close();
        return result;
    }

    public void addWorkOut(HashMap<String , Integer> setDones , HashMap<String , String> Amrap , HashMap<String , Float> Weights)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues Bvalues = new ContentValues();
        ContentValues Rvalues = new ContentValues();
        ContentValues Svalues = new ContentValues();

        Bvalues.put("Bench1" , setDones.get("Bench1"));
        Bvalues.put("Bench2" , setDones.get("Bench2"));
        Bvalues.put("Bench3" , setDones.get("Bench3"));
        Bvalues.put("BenchAMRAP" , Amrap.get("BenchAMRAP"));
        Bvalues.put("BenchWeight" , Weights.get("Bench"));

        Rvalues.put("Row1" , setDones.get("Row1"));
        Rvalues.put("Row2" , setDones.get("Row2"));
        Rvalues.put("Row3" , setDones.get("Row3"));
        Rvalues.put("RowAMRAP" , Amrap.get("RowAMRAP"));
        Rvalues.put("RowWeight" , Weights.get("Row"));

        Svalues.put("Squat1" , setDones.get("Squat1"));
        Svalues.put("Squat2" , setDones.get("Squat2"));
        Svalues.put("Squat3" , setDones.get("Squat3"));
        Svalues.put("SquatAMRAP" , Amrap.get("SquatAMRAP"));
        Svalues.put("SquatWeight" , Weights.get("Squat"));

        database.insert("Bench" , null , Bvalues);
        database.insert("Row" , null , Rvalues);
        database.insert("Squat" , null , Svalues);

        database.close();
    }

    public ArrayList<Graphresult> getWeightsForGraph(String s)
    {
        ArrayList<Graphresult> results = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        final String BselectQuery = "SELECT * FROM " + s;
        Cursor Bcursor = database.rawQuery(BselectQuery, null);
        if(Bcursor.moveToFirst())
        {
            do{
                Graphresult graphresult = new Graphresult(Bcursor.getInt(0) , Bcursor.getInt(4) , Bcursor.getFloat(5));

                results.add(graphresult);

            } while(Bcursor.moveToNext());

        }
        Bcursor.close();

        return results;
    }

}

// RowWorkOutNo INTEGER PRIMARY KEY, Row1 INTEGER, Row2 INTEGER, Row3 INTEGER, RowAMRAP INTEGER, RowWeight REAL




























