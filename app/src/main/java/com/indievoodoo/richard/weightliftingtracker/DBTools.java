package com.indievoodoo.richard.weightliftingtracker;

import android.app.Application;
import android.content.Context;
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
        super(applicationContext , "weightlifting.db" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        String query = "CREATE TABLE Bench (WorkoutNo INTEGER PRIMARY KEY)";

        database.execSQL(query);

        //testDB();
    }

    public void testDB()
    {
        try
        {
            SQLiteDatabase dbe = SQLiteDatabase.openDatabase(Environment.getDataDirectory().getPath(), null, 0);
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
