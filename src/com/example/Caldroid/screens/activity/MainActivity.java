package com.example.Caldroid.screens.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;


import com.example.Caldroid.EndllesList.FragmentEndlesList;
import com.example.Caldroid.R;



public final class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addFragmentCalendar();

    }


    private final void addFragmentCalendar() {
        //        CalendarFragment calendar = new CalendarFragment();
//        Fragment calendar =  new FragmentCalendroid();
        Fragment calendar =  new FragmentEndlesList();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer_M, calendar);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        CalendarGenerator.toCurrentMonth(this);
//        CalendarGenerator.getWeekBeforThisDay( CalendarGenerator.getPreviousMonthList().get(0));
//        CalendarGenerator.toPreviousMonth();
//        CalendarGenerator.getWeekBeforThisDay( CalendarGenerator.getPreviousMonthList().get(0));
//        CalendarGenerator.getPreviousMonthList();
//        CalendarGenerator.getCurentMonthList();
//        CalendarGenerator.getNextMonthList();



    }


    // if argument equ NULL actionBar defaults
    public final void setCustomActionBar(final View _customView) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            if (_customView == null)
                actionBar.setCustomView(R.layout.calendr_action_bar);
            else
                actionBar.setCustomView(_customView);
        }
    }
}
