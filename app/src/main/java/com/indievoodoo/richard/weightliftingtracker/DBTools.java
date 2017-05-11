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

public class DBTools extends SQLiteOpenHelper
{
    public DBTools(Context appContext)
    {
        super(appContext, "WeightLifting.db3", null, 11);
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

        String Oquery = "CREATE TABLE OHP (OHPWorkOutNo INTEGER PRIMARY KEY, OHP1 INTEGER, OHP2 INTEGER, OHP3 INTEGER, OHPAMRAP INTEGER, OHPWeight REAL )";

        database.execSQL(Oquery);

        String Cquery = "CREATE TABLE Chin (ChinWorkOutNo INTEGER PRIMARY KEY, Chin1 INTEGER, Chin2 INTEGER, Chin3 INTEGER, ChinAMRAP INTEGER, ChinWeight REAL )";

        database.execSQL(Cquery);

        String Dquery = "CREATE TABLE DeadLift (DeadWorkOutNo INTEGER PRIMARY KEY, DeadLift1 INTEGER,  DeadLiftAMRAP INTEGER, DeadLiftWeight REAL )";

        database.execSQL(Dquery);
        enterDefaults(database);
    }

    private void enterDefaults(SQLiteDatabase database)
    {
         //database = getWritableDatabase();
        ContentValues content = new ContentValues();

        ContentValues Bvalues = new ContentValues();
        ContentValues Rvalues = new ContentValues();
        ContentValues Svalues = new ContentValues();
        ContentValues Ovalues = new ContentValues();
        ContentValues Cvalues = new ContentValues();
        ContentValues Dvalues = new ContentValues();

        Bvalues.put("BenchWeight" , "20");
        Rvalues.put("RowWeight" , "20");
        Svalues.put("SquatWeight" , "20");
        Ovalues.put("OHPWeight" , "20");
        Cvalues.put("ChinWeight" , "20");
        Dvalues.put("DeadLiftWeight" , "40");

        database.insert("Bench" , null , Bvalues);
        database.insert("Row" , null , Rvalues);
        database.insert("Squat" , null , Svalues);
        database.insert("OHP" , null , Ovalues);
        database.insert("Chin" , null , Cvalues);
        database.insert("DeadLift" , null , Dvalues);
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

        String Oquery = "DROP TABLE IF EXISTS OHP";
        database.execSQL(Oquery);

        String Cquery = "DROP TABLE IF EXISTS Chin";
        database.execSQL(Cquery);

        String Dquery = "DROP TABLE IF EXISTS DeadLift";
        database.execSQL(Dquery);

        onCreate(database);
        //enterDefaults(database);

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

    public void setweights2(String a , String b , String c)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues Bvalues = new ContentValues();
        ContentValues Rvalues = new ContentValues();
        ContentValues Svalues = new ContentValues();

        Bvalues.put("OHPWeight" , a);
        Rvalues.put("ChinWeight" , b);
        Svalues.put("DeadLiftWeight" , c);

        database.insert("OHP" , null , Bvalues);
        database.insert("Chin" , null , Rvalues);
        database.insert("DeadLift" , null , Svalues);

        database.close();
    }

    public ArrayList<Float> getweights1()
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

    public ArrayList<Float> getweights2()
    {
        ArrayList<Float> result = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        final String BselectQuery = "SELECT * FROM OHP";
        Cursor Bcursor = database.rawQuery(BselectQuery, null);
        if(Bcursor.moveToLast())
        {
            result.add(Bcursor.getFloat(5));
        }
        Bcursor.close();

        final String RselectQuery = "SELECT * FROM Chin";
        Cursor Rcursor = database.rawQuery(RselectQuery, null);
        if(Rcursor.moveToLast())
        {
            result.add(Rcursor.getFloat(5));
        }
        Rcursor.close();

        final String SselectQuery = "SELECT * FROM DeadLift";
        Cursor Scursor = database.rawQuery(SselectQuery, null);
        if(Scursor.moveToLast())
        {
            result.add(Scursor.getFloat(3));
        }
        Scursor.close();

        database.close();
        return result;
    }

    public void addWorkOut1(HashMap<String , Integer> setDones , HashMap<String , String> Amrap , HashMap<String , Float> Weights)
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

    public void addWorkOut2(HashMap<String , Integer> setDones , HashMap<String , String> Amrap , HashMap<String , Float> Weights)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues OHPvalues = new ContentValues();
        ContentValues Cvalues = new ContentValues();
        ContentValues Dvalues = new ContentValues();

        OHPvalues.put("OHP1" , setDones.get("OHP1"));
        OHPvalues.put("OHP2" , setDones.get("OHP2"));
        OHPvalues.put("OHP3" , setDones.get("OHP3"));
        OHPvalues.put("OHPAMRAP" , Amrap.get("OHPAMRAP"));
        OHPvalues.put("OHPWeight" , Weights.get("OHP"));

        Cvalues.put("Chin1" , setDones.get("Chin1"));
        Cvalues.put("Chin2" , setDones.get("Chin"));
        Cvalues.put("Chin3" , setDones.get("Chin3"));
        Cvalues.put("ChinAMRAP" , Amrap.get("ChinAMRAP"));
        Cvalues.put("ChinWeight" , Weights.get("Chin"));

        Dvalues.put("DeadLift1" , setDones.get("DeadLift1"));
        Dvalues.put("DeadLiftAMRAP" , Amrap.get("DeadLiftAMRAP"));
        Dvalues.put("DeadLiftWeight" , Weights.get("DeadLift"));

        database.insert("OHP" , null , OHPvalues);
        database.insert("Chin" , null , Cvalues);
        database.insert("DeadLift" , null , Dvalues);

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

    public ArrayList<Graphresult> getDeadLiftForGraph(String s)
    {
        ArrayList<Graphresult> results = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        final String BselectQuery = "SELECT * FROM " + s;
        Cursor Bcursor = database.rawQuery(BselectQuery, null);
        if(Bcursor.moveToFirst())
        {
            do{
                Graphresult graphresult = new Graphresult(Bcursor.getInt(0) , Bcursor.getInt(2) , Bcursor.getFloat(3));

                results.add(graphresult);

            } while(Bcursor.moveToNext());
        }
        Bcursor.close();

        return results;
    }

}




























