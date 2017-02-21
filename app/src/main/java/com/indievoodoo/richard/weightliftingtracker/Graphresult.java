package com.indievoodoo.richard.weightliftingtracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Graphresult
{
    // RowWorkOutNo INTEGER PRIMARY KEY, Row1 INTEGER, Row2 INTEGER, Row3 INTEGER, RowAMRAP INTEGER, RowWeight REAL
    int workoutNo;
    int Amrap;
    float weight;

    /*public Graphresult(int wn , int a , float w)
    {
        workoutNo = wn;
        Amrap = a;
        weight = w;
    }*/
}
