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
    private static ArrayList<Day> previousCalendarListMinusOne =  new ArrayList<Day>();
    private static ArrayList<Day> nextCalendarList =  new ArrayList<Day>();
    private static ArrayList<Day> nextCalendarListPlusOne =  new ArrayList<Day>();

    private static ArrayList<Week> currentCalendar =  new ArrayList<Week>();


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
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)], true);
            day.getWeekDay();
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + " "  + String.valueOf(day.getWeekDay());
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
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)], false);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            previousCalendarList.add(day);
        }

        if (today.month == 0 ){
            month = 11;
            year = today.year - 1 ;
        }else {
            month = today.month - 1;
            year = today.year;
        }
        today.set(1, month, year);
        monthDay  = today.getActualMaximum(Time.MONTH_DAY);
        for (int i = 1; i <= monthDay; i++){
            today.set(i, month, year);
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)], false);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            previousCalendarListMinusOne.add(day);
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
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)], false);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            nextCalendarList.add(day);
        }
        if (today.month == 11 ){
            month = 0;
            year = today.year + 1 ;
        }else {
            month = today.month+ 1;
            year = today.year;
        }
        today.set(1, month, year);

        monthDay  = today.getActualMaximum(Time.MONTH_DAY);
        for (int i = 1; i <= monthDay; i++){
            today.set(i, month, year);
            Day day = new Day(today.toMillis(false), imageURLs[random.nextInt(randomID)], false);
            String printString = String.valueOf(day.Day) +" "+String.valueOf(day.Month)+" "+String.valueOf(day.Year) + day.imgURL;
            Log.v(TAG , printString );
            nextCalendarListPlusOne.add(day);
        }
        return nextCalendarList;
    }
    public static ArrayList<Day> getPrePreList(){
      return nextCalendarListPlusOne;
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



    public static ArrayList<Week> getInitData(){
        ArrayList<Day> tempList =  new ArrayList<Day>();
        currentCalendar.clear();

        tempList = getPreviousMonthList();
        tempList.addAll(getCurentMonthList());
        tempList.addAll(getNextMonthList());




        for (int i = 0 ; i < tempList.size(); ){
            Week week =  new Week();
            int fierstDay = tempList.get(i).getWeekDay();
            int indexLost = i+(6-fierstDay);
            int lastDay;
            if(indexLost <= tempList.size()-1){
                lastDay = 6;
            }else {
                lastDay = tempList.get(tempList.size()-1).getWeekDay();
            }
            for (int j = 0; j <= 6;  j++){
                if(j >= fierstDay && j <= lastDay){
                    week.add(j, tempList.get(i));
                    i++;
                } else{
                    week.add(j, null);
                }
            }
            currentCalendar.add(week);
        }
        return currentCalendar;
    }

    public static ArrayList<Week> getNextWeekMonthList(){
        ArrayList<Day> tempList =  new ArrayList<Day>();
        ArrayList<Week> result = new ArrayList<Week>();
        toNextMonth();
        tempList = getNextMonthList();

        for (int i = 0 ; i < tempList.size(); ){
            Week week =  new Week();
            int fierstDay = tempList.get(i).getWeekDay();
            int indexLost = i+(6-fierstDay);
            int lastDay;
            if(indexLost <= tempList.size()-1){
                lastDay = 6;
            }else {
                lastDay = tempList.get(tempList.size()-1).getWeekDay();
            }
            for (int j = 0; j <= 6;  j++){
                if(j >= fierstDay && j <= lastDay){
                    week.add(j, tempList.get(i));
                    i++;
                } else{
                    week.add(j, null);
                }
            }
            result.add(week);
        }
        return result;
    }

    public static ArrayList<Week> getPrevWeekMonthList() {
        ArrayList<Day> tempList = new ArrayList<Day>();
        ArrayList<Week> result = new ArrayList<Week>();
        toPreviousMonth();
        tempList = getPreviousMonthList();
        for (int i = 0 ; i < tempList.size(); ){
            Week week =  new Week();
            int fierstDay = tempList.get(i).getWeekDay();
            int indexLost = i+(6-fierstDay);
            int lastDay;
            if(indexLost <= tempList.size()-1){
                lastDay = 6;
            }else {
                lastDay = tempList.get(tempList.size()-1).getWeekDay();
            }
            for (int j = 0; j <= 6;  j++){
                if(j >= fierstDay && j <= lastDay){
                    week.add(j, tempList.get(i));
                    i++;
                } else{
                    week.add(j, null);
                }
            }
            result.add(week);
        }
        return result;
    }
}
