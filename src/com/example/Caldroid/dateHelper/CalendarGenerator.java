package com.example.Caldroid.dateHelper;


import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.example.Caldroid.R;

import java.sql.DataTruncation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by BruSD on 6/12/2014.
 */
public class CalendarGenerator  {
    private static ArrayList<Day> currentCalendarList =  new ArrayList<Day>();
    private static ArrayList<Day> previousCalendarList =  new ArrayList<Day>();
    private static ArrayList<Day> nextCalendarList =  new ArrayList<Day>();

    private static Time currentMonth  =  new Time();

    private static String TAG = "CC_MOB";

    private static String[] imageURLs;



    public static ArrayList<Day> getCurentMonthList(){
        Time today =  new Time();
        currentCalendarList.clear();
        today = currentMonth;
         int randomID =imageURLs.length;
        Random random =  new Random();
        int monthDay  = today.getActualMaximum(Time.MONTH_DAY);
        int month = today.month;
        int year = today.year;

        for (int i = 1; i <= monthDay; i++){



            today.set(i, month, year);
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)]);

            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
                    Log.v(TAG , printString );

            currentCalendarList.add(day);
        }
        return currentCalendarList;
    }
    public static ArrayList<Day> getPreviousMonthList(){
        Time today =  new Time();
        previousCalendarList.clear();
        today.set(currentMonth.monthDay, currentMonth.month, currentMonth.year);
        int randomID =imageURLs.length;
        Random random =  new Random();
        int month;
        int year;
        if (today.month == 0 ){
             month = 11;
             year = today.year - 1 ;
        }else {
             month = today.month - 1;
             year = today.year;
        }
        today.set(1, month, year);

        int monthDay  = today.getActualMaximum(Time.MONTH_DAY);
        for (int i = 1; i <= monthDay; i++){
            today.set(i, month, year);
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)]);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            previousCalendarList.add(day);
        }
        return previousCalendarList;
    }

    public static ArrayList<Day> getNextMonthList(){
        Time today =  new Time();
        nextCalendarList.clear();
        today.set(currentMonth.monthDay, currentMonth.month, currentMonth.year);
        int randomID =imageURLs.length;
        Random random =  new Random();

        int month;
        int year;
        if (today.month == 11 ){
            month = 0;
            year = today.year + 1 ;
        }else {
            month = today.month+ 1;
            year = today.year;
        }
        today.set(1, month, year);

        int monthDay  = today.getActualMaximum(Time.MONTH_DAY);
        for (int i = 1; i <= monthDay; i++){
            today.set(i, month, year);
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)]);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            previousCalendarList.add(day);
        }
        return nextCalendarList;
    }

    public static void toCurrentMonth(Context context){
        imageURLs =  context.getResources().getStringArray(R.array.image_url);
        currentMonth.setToNow();

    }


    public static void toPreviousMonth(){
        int month;
        int year;
        if (currentMonth.month == 0 ){
            month = 11;
            year = currentMonth.year - 1 ;
        }else {
            month = currentMonth.month - 1;
            year = currentMonth.year;
        }
        currentMonth.set(1, month, year);

    }

    public static void toNextMonth(){
        int month;
        int year;
        if (currentMonth.month == 11 ){
            month = 0;
            year = currentMonth.year + 1 ;
        }else {
            month = currentMonth.month + 1;
            year = currentMonth.year;
        }
        currentMonth.set(1, month, year);
    }

    public static String getMonthName(){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentMonth.toMillis(false));
        String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
        Log.v(TAG , monthName );
        return monthName;

    }
}
