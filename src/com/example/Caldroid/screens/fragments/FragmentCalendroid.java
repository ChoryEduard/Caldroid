package com.example.Caldroid.screens.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.Caldroid.Endless.EndlessAdapter;
import com.example.Caldroid.Endless.EndlessGridView;
import com.example.Caldroid.R;
import com.example.Caldroid.adapters.AdapterCalebdroid;
import com.example.Caldroid.adapters.CalendroidAdapter;
import com.example.Caldroid.dateHelper.CalendarGenerator;
import com.example.Caldroid.dateHelper.Day;
import com.example.Caldroid.screens.activity.MainActivity;
import com.example.Caldroid.view.HeaderCalendarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruSD on 6/16/2014.
 */
public class FragmentCalendroid extends Fragment implements EndlessGridView.EndlessListener{

    private GridView gridNameDayOfWeek;
    private Activity mActivity;
    private HeaderCalendarView header;
    private View vi;
    private EndlessGridView gridView;
    private EndlessAdapter calendroidAdapter;
    private  ArrayList<Day> list;
    private  ArrayList<Day> prevList, currList, nextList, tempList;
    private  boolean loadingMore = false;

    private final static int ITEM_PER_REQUEST = 50;
    EndlessGridView lv;

    int mult = 1;
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
        return v;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vi = inflater.inflate(R.layout.fragment_calendroid,container, false);
        gridView =  (EndlessGridView)vi.findViewById(R.id.grid_view);
        initAdapter();
        gridView.setAdapter(calendroidAdapter);
        gridView.setListener(this);
        return vi;
    }
    private void initAdapter(){

        CalendarGenerator.toCurrentMonth(mActivity);
        prevList = CalendarGenerator.getPreviousMonthList();
        list = prevList;
        currList = CalendarGenerator.getCurentMonthList();
        nextList = CalendarGenerator.getNextMonthList();
        for (int i = 0; i < currList.size(); i++ ){
            list.add(currList.get(i));
        }
        for (int i = 0; i < CalendarGenerator.getNextMonthList().size(); i++ ){
            list.add(nextList.get(i));
        }
        calendroidAdapter = new EndlessAdapter(mActivity, list, R.layout.calendar_item, getScreenWidth()/7 );


    }
    private class FakeNetLoader extends AsyncTask<String, Void, List<Day>> {

        @Override
        protected List<Day> doInBackground(String... params) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return createItems(mult);
        }

        @Override
        protected void onPostExecute(List<Day> result) {
            super.onPostExecute(result);
            gridView.addNewDataToEnd(result, CalendarGenerator.getPrePreList() );
        }
    }



    private List<Day> createItems(int mult) {
        CalendarGenerator.toNextMonth();
        List<Day> result = CalendarGenerator.getNextMonthList();

        return result;
    }

    @Override
    public void loadData() {
        System.out.println("Load data");
        mult += 10;
        // We load more data here
        FakeNetLoader fl = new FakeNetLoader();
        fl.execute(new String[]{});

    }


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
//    private void initAdapter(){
//
//        CalendarGenerator.toCurrentMonth(mActivity);
//        prevList = CalendarGenerator.getPreviousMonthList();
//        list = prevList;
//        currList = CalendarGenerator.getCurentMonthList();
//        nextList = CalendarGenerator.getNextMonthList();
//        for (int i = 0; i < currList.size(); i++ ){
//            list.add(currList.get(i));
//        }
//        for (int i = 0; i < CalendarGenerator.getNextMonthList().size(); i++ ){
//            list.add(nextList.get(i));
//        }
//        calendroidAdapter = new AdapterCalebdroid(mActivity, list, getScreenWidth()/ 7 );
//
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        initAdapter();
//        gridView.setAdapter(calendroidAdapter);
////        gridView.scrollListBy(100);
//        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView _view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView _view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //what is the bottom iten that is visible
//                int lastInScreen = firstVisibleItem + visibleItemCount;
//                //is the bottom item visible & not loading more already ? Load more !
//
//                if((lastInScreen == totalItemCount) && !(loadingMore)){
//                    Thread thread =  new Thread(null, loadMoreListItems);
//                    thread.start();
//                    Log.v("Scrool"  , "1" );
//                }
//            }
//        });
//    }
//    //Runnable to load the items
//    private Runnable loadMoreListItems = new Runnable() {
//        @Override
//        public void run() {
//            //Set flag so we cant load new items 2 at the same time
//            loadingMore = true;
//            //Reset the array that holds the new items
//
//            //Simulate a delay, delete this on a production environment!
//            try { Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//            //Get 15 new listitems
//            CalendarGenerator.toNextMonth();
//            tempList = CalendarGenerator.getNextMonthList();
//            //Done! now continue on the UI thread
//            mActivity.runOnUiThread(returnRes);
//        }
//    };
//    //Since we cant update our UI from a thread this Runnable takes care of that!
//    private Runnable returnRes = new Runnable() {
//        @Override
//        public void run() {
//            //Loop thru the new items and add them to the adapter
//            if (tempList != null && tempList.size() > 0) {
//
//                calendroidAdapter.addToEnd(tempList);
//
//                calendroidAdapter.notifyDataSetChanged();
////                gridView.smoothScrollToPosition(0);
//                //Update the Application title
////            setTitle("Neverending List with " + String.valueOf(calendroidAdapter.getCount()) + " items");
//                //Tell to the adapter that changes have been made, this will cause the list to refresh
//
//                //Done loading more.
//                calendroidAdapter.removeFromStart(prevList);
//                calendroidAdapter.notifyDataSetChanged();
//                loadingMore = false;
//            }
//        }
//    };


}
