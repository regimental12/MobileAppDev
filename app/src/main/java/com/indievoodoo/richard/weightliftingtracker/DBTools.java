package com.indievoodoo.richard.weightliftingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Richard on 03/02/2017.
 */

public class DBTools extends SQLiteOpenHelper
{
    public DBTools(Context appContext)
    {
        super(appContext, "WeightLifting.db3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        String query  = "CREATE TABLE Bench (benchWorkOutNo INTEGER PRIMARY KEY, Bench1 INTEGER, Bench2 INTEGER, Bench3 INTEGER, BenchAMRAP INTEGER, BenchWeight REAL )";

        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1)
    {
        String query = "DROP TABLE IF EXISTS Bench";

        database.execSQL(query);
        onCreate(database);

    }

    public void setweights(String a)
    {
        String result = " ";

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("BenchWeight" , a);

        database.insert("Bench" , null , values);

        database.close();
    }

    public float getweights()
    {
        float result = 0;
        SQLiteDatabase database = this.getReadableDatabase();

        final String selectQuery = "SELECT * FROM Bench";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToLast())
        {
            result = cursor.getFloat(5);
        }
        database.close();
        return result;
    }
}
