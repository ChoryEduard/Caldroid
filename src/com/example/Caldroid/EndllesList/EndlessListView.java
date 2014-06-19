package com.example.Caldroid.EndllesList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.Caldroid.dateHelper.Week;

import java.util.List;

/**
 * Created by BruSD on 6/18/2014.
 */
public class EndlessListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;
    private boolean isLoading;
    private boolean isUpdating;
    private EndlessListener listener;
    private EndlessAdapter adapter;
    private int oldVisebleItem;

    public EndlessListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context) {
        super(context);
        this.setOnScrollListener(this);
    }

    public void setListener(EndlessListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (getAdapter() == null)
            return ;

        if (getAdapter().getCount() == 0)
            return ;

        int l = visibleItemCount + firstVisibleItem;
        adapter.setNextMonthToCurrent(firstVisibleItem, visibleItemCount);

        if (l >= totalItemCount && !isLoading) {
            // It is time to add new data. We call the listener
            this.addFooterView(footer);
            isLoading = true;
            listener.loadData(true);
        }else if(firstVisibleItem == 0 && !isLoading){

            this.addHeaderView(footer);
            isLoading = true;
            listener.loadData(false);
        }

        if(oldVisebleItem < firstVisibleItem && !isUpdating ){
            isUpdating = true;
            int lastItem = this.getLastVisiblePosition() -1 ;
            scrolledDown(lastItem );
        }else if(oldVisebleItem > firstVisibleItem && !isUpdating ){
            scrolledUp();
            isUpdating = true;
        }
    }



    private void scrolledDown(int last){
        Log.v("Position", "scrolledDown");
//        adapter.setNextMonthToCurrent(last);
        isUpdating = false;
    }
    private void scrolledUp(){
        Log.v("Position", "ScroolUp");
        isUpdating = false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    public void setLoadingView(int resId) {
        LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footer = (View) inflater.inflate(resId, null);
        this.addFooterView(footer);

    }

    @Override
    public void setSelectionFromTop(int position, int y) {
        super.setSelectionFromTop(position, y);
        oldVisebleItem = position;
    }

    public void setAdapter(EndlessAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = adapter;
        this.removeFooterView(footer);
    }


    public void addNewDataBottom(List<Week> data) {

        this.removeFooterView(footer);
        adapter.addToEndWithGlue(data);
        adapter.notifyDataSetChanged();

        isLoading = false;
    }
    public void addNewDataTop(List<Week> data) {
        this.removeHeaderView(footer);
        adapter.addToStartWithGlue(data);


        int index = this.getFirstVisiblePosition() + data.size() - 1;
        View v = this.getChildAt(0);
        int top = (v == null) ? 0 : v.getTop();
        adapter.notifyDataSetChanged();

        this.setSelectionFromTop(index, top);
        isLoading = false;
    }

    public EndlessListener setListener() {
        return listener;
    }


    public static interface EndlessListener {
        public void loadData(boolean isDownScroll) ;
    }

}
