package com.example.Caldroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.Caldroid.dateHelper.CalendarGenerator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


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

    @Override
    protected void onStart() {
        super.onStart();
        CalendarGenerator.toCurrentMonth(this);
        CalendarGenerator.getMonthName();



    }
}
