package com.example.Caldroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public final class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addFragmentCalendar();
    }


    private final void addFragmentCalendar() {
        CalendarFragment calendar = new CalendarFragment();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameContainer_M, calendar);
        fragmentTransaction.commit();
    }
}
