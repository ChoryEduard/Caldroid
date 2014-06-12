package com.example.Caldroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eduard on 12.06.14.
 *
 */
public final class CalendarFragment extends Fragment {

    private CalendroidView calendroid;
    private CalendroidAdapter adapter;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null)
            return null;
        View vi = inflater.inflate(R.layout.fragment_calendar,container, false);
        if (vi != null ) {
            calendroid = (CalendroidView)vi.findViewById(R.id.calendar);
            setAdapter();
        }
        return vi;
    }


    private final void setAdapter() {
        adapter = new CalendroidAdapter(getActivity(), calendroid.getMColumnWidth());
        calendroid.setAdapter(adapter);
    }

}
