package com.example.Caldroid.dateHelper;

import android.text.format.Time;

/**
 * Created by BruSD on 6/12/2014.
 */
public class Day {
    private static Time date = new Time();;
    public String imgURL;
    public int Day;
    public int Month; // 0-11
    public int Year;

    public Day(long dateInMilliseconds , String imgURL){
        this.date.set(dateInMilliseconds);
        this.imgURL = imgURL;

        this.Day = date.monthDay;
        this.Month =  date.month;
        this.Year = date.year;
    }

    public int getWeekDay(){
        return date.weekDay;
    }


}
