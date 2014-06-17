package com.example.Caldroid.screens.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.Day;
import com.example.Caldroid.screens.activity.MainActivity;
import com.example.Caldroid.adapters.CalendroidAdapter;
import com.example.Caldroid.adapters.DayOfWeekAdapter;
import com.example.Caldroid.view.CalendroidView;
import com.example.Caldroid.view.HeaderCalendarView;


/**
 * Created by eduard on 12.06.14.
 *
 */

public final class CalendarFragment extends Fragment implements OnItemClickListener {

    private CalendroidView calendroid;
    private CalendroidAdapter adapter;
    private GridView gridNameDayOfWeek;
    private Activity mActivity;
    private HeaderCalendarView header;


    @Override
    public final void onAttach(Activity _activity) {
        super.onAttach(_activity);
        mActivity = _activity;
        ((MainActivity) mActivity).setCustomActionBar(initializeActionBar());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return null;
        View vi = inflater.inflate(R.layout.fragment_calendar,container, false);
        if (vi != null ) {
            gridNameDayOfWeek = (GridView)vi.findViewById(R.id.gridNameDayOfWeek);
            calendroid = (CalendroidView)vi.findViewById(R.id.calendar);
            header = (HeaderCalendarView)vi.findViewById(R.id.headerInfCalendar);
            initUpNameOfWeek();
            setSizeHeader();
            setAdapter();
            setCalendarListener();
            calendroid.smoothScrollToPosition((adapter.getCount()));

            //
        }
        return vi;
    }

    private final void setCalendarListener() {
        calendroid.setOnItemClickListener(this);
    }

    private final void initUpNameOfWeek() {
        gridNameDayOfWeek.setNumColumns(CalendroidView.NUM_OF_COLUMNS);
        gridNameDayOfWeek.setColumnWidth(calendroid.getMColumnWidth());
        gridNameDayOfWeek.setStretchMode(GridView.NO_STRETCH);
        gridNameDayOfWeek.setAdapter(new DayOfWeekAdapter(getActivity(), calendroid.getMColumnWidth()));
        int padding = calendroid.getPadding();
        gridNameDayOfWeek.setPadding(padding * 2, padding, padding,0);
        gridNameDayOfWeek.setHorizontalSpacing(padding);
        gridNameDayOfWeek.setVerticalSpacing(padding);
    }


    private final void setSizeHeader() {
        header.setSize(calendroid.getMColumnWidth());
    }


    private final void setAdapter() {
        adapter = new CalendroidAdapter(getActivity(), calendroid.getMColumnWidth(), header);
        calendroid.setScroll(adapter);
        calendroid.setAdapter(adapter);
        header.setOnSetCurrent(adapter);
    }


    private final View initializeActionBar() {
        LayoutInflater inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendr_action_bar, null);
        if (v != null) {
            TextView tvCurrentName = (TextView) v.findViewById(R.id.tvActionCurrent);

            tvCurrentName.setText(mActivity.getString(R.string.the_boys));
        }
        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Day day = (Day)adapter.getItem(position);
        Toast.makeText(getActivity(), "Date: " + day.Day + "/" + (day.Month + 1) + "/" +
                day.Year, Toast.LENGTH_SHORT).show();
    }
}
