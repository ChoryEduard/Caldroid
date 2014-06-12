package com.example.Caldroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eduard on 12.06.14.
 *
 */
public final class CalendarFragment extends Fragment {

    private CalendroidView calendroid;
    private CalendroidAdapter adapter;
    private Activity mActivity;


    @Override
    public final void onAttach(Activity _activity) {
        super.onAttach(_activity);
        mActivity = _activity;
        ((MainActivity) mActivity).setCustomActionBar(initializeActionBar());
    }


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



}
