package com.example.Caldroid.EndllesList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.CalendarGenerator;
import com.example.Caldroid.dateHelper.Week;
import com.example.Caldroid.screens.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruSD on 6/18/2014.
 */
public class FragmentEndlesList extends Fragment implements EndlessListView.EndlessListener {


    int mult = 1;
    private EndlessListView lv;
    private View vi;
    private Activity mActivity;
    private EndlessAdapter adp;
    private boolean isDawnScroll;


    private final int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        final Point point = new Point();
        point.x = display.getWidth();
        point.y = display.getHeight();
        columnWidth = point.x;
        return columnWidth;
    }

    @Override
    public final void onAttach(Activity _activity) {
        super.onAttach(_activity);
        mActivity = _activity;
        ((MainActivity) mActivity).setCustomActionBar(initializeActionBar());
    }

    private final View initializeActionBar() {
        LayoutInflater inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendr_action_bar, null);
        if (v != null) {
            TextView tvCurrentName = (TextView) v.findViewById(R.id.tvActionCurrent);

            tvCurrentName.setText(mActivity.getString(R.string.the_boys));
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDefaultList();
            }
        });
        return v;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vi = inflater.inflate(R.layout.fragment_endlase_list, container, false);
        lv = (EndlessListView) vi.findViewById(R.id.el);
        lv.setFastScrollEnabled(false);

        loadDefaultList();

        return vi;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView) ((mActivity).findViewById(R.id.tv_current_month))).setText(adp.getmCurrentMonthName() + " " + adp.mCurrentYear);
    }

    private void loadDefaultList() {
        adp = new EndlessAdapter(mActivity, initFirstList(), R.layout.row_layout, getScreenWidth() / 7);
        lv.setLoadingView(R.layout.loading_layout);
        lv.setAdapter(adp);
        lv.setSelectionFromTop(2, 0);

        lv.setListener(this);
    }

    private ArrayList<Week> initFirstList() {
        CalendarGenerator.toCurrentMonth(mActivity);
        ArrayList<Week> result = new ArrayList<Week>();

        result = CalendarGenerator.getInitData();
        return result;
    }

    @Override
    public void loadData(boolean isDownScroll) {
        isDawnScroll = isDownScroll;
        System.out.println("Load data");
        mult += 10;
        // We load more data here
        AddMonthTask fl = new AddMonthTask();
        fl.execute(new String[]{});
    }

    private ArrayList<Week> extendList() {

        ArrayList<Week> result;
        if (isDawnScroll) {
            result = CalendarGenerator.getNextWeekMonthList(adp.getpNexttMonth(), adp.getNexYear());
        } else {
            result = CalendarGenerator.getPrevWeekMonthList(adp.getPrevtMonth(), adp.getPrevYear());
        }
        return result;
    }

    private class AddMonthTask extends AsyncTask<String, Void, List<Week>> {

        @Override
        protected List<Week> doInBackground(String... params) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return extendList();
        }

        @Override
        protected void onPostExecute(List<Week> result) {
            super.onPostExecute(result);
            if (isDawnScroll) {
                lv.addNewDataBottom(result);
            } else {
                lv.addNewDataTop(result);
            }
        }
    }


}