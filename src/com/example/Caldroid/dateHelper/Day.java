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
    private int dayOfWeek;
    private boolean isCurrentMonthDay;

    public Day(long dateInMilliseconds , String imgURL, boolean isCurrMonthDay){
        this.date.set(dateInMilliseconds);
        this.imgURL = imgURL;

        this.Day = date.monthDay;
        this.Month =  date.month;
        this.Year = date.year;
        this.dayOfWeek = date.weekDay;
        this.isCurrentMonthDay = isCurrMonthDay;
    }

    public int getWeekDay(){
        return dayOfWeek;
    }
    public boolean isCurrentMonthDay(){
        return isCurrentMonthDay;
    }

    public void setIsCorrentMonthDay(boolean isCurrent){
        isCurrentMonthDay = isCurrent;
    }


}
