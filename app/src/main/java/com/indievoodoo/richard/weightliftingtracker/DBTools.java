package com.indievoodoo.richard.weightliftingtracker;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Richard on 03/02/2017.
 */

public class DBTools extends SQLiteOpenHelper {



    public DBTools(Context applicationContext)
    {
        super(applicationContext , "weightlifting.db3" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        //  "CREATE TABLE Bench (WorkoutNo INTEGER PRIMARY KEY)";

        String query = "CREATE TABLE Bench (WorkoutNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, Bench1	INTEGER, Bench2	INTEGER, Bench3	INTEGER, BenchAMRAP	INTEGER, BenchWeight REAL)";

        database.execSQL(query);

        query = "CREATE TABLE Row (WorkoutNo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, Row1 INTEGER, Row2	INTEGER, Row3	INTEGER, RowAMRAP	INTEGER, RowWeight REAL)";

        database.execSQL(query);

        //testDB();
    }

    public void testDB()
    {
        try
        {
            SQLiteDatabase dbe = SQLiteDatabase.openDatabase("//data/data/com.indievoodoo.richard.weightliftingtracker/databases/weightlifting.db3", null, 0);



            Log.d("opendb", "EXIST");
        }
        catch(Exception e) {
            Log.d("opendb", "NOT EXIST");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("drop table if exists test1");
        onCreate(sqLiteDatabase);
    }
}
