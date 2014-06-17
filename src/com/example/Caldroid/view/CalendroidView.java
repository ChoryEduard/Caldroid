package com.example.Caldroid.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import com.example.Caldroid.event.OnScrolling;

/**
 * Created by eduard on 12.06.14.
 *
 */

public final class CalendroidView extends GridView implements View.OnTouchListener {

    public final static int GRID_PADDING = 2;
    public final static int NUM_OF_COLUMNS = 7;
    public Context mContext;
    private OnScrolling scrolling;
    private int mColumnWidth;
    private int mPanding;
    private int mOldY = 0;
    private int isScroll = 0;


    public CalendroidView(Context context) {
        super(context);
        InitilizeGridLayout(context);
    }

    public CalendroidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitilizeGridLayout(context);
    }

    public CalendroidView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        InitilizeGridLayout(context);
    }

    private final void InitilizeGridLayout(Context _context) {
        mContext = _context;
        Resources r = mContext.getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, GRID_PADDING, r.getDisplayMetrics());
        setOnTouchListener(this);
        mPanding = (int)padding;
        mColumnWidth = (int) ((getScreenWidth() - ((NUM_OF_COLUMNS + 1) * padding))  / NUM_OF_COLUMNS);

        this.setNumColumns(NUM_OF_COLUMNS);
        this.setColumnWidth(mColumnWidth);
        this.setStretchMode(GridView.NO_STRETCH);
        this.setPadding((int)(padding) * 2, (int) padding, (int) padding,0);
        this.setHorizontalSpacing((int) padding);
        this.setVerticalSpacing((int) padding);
    }


    private final int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        final Point point = new Point();
        point.x = display.getWidth();
        point.y = display.getHeight();
        columnWidth = point.x;
        return columnWidth;
    }


    public final int getMColumnWidth() {
        return mColumnWidth;
    }



    public final int getPadding() {
        return mPanding;
    }


    public final void setScroll(final OnScrolling _scroll) {
        scrolling = _scroll;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mOldY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                onOverScrolled((int)event.getY()/2, 0, true, true);
                int delta = (int)event.getY() - mOldY;
                Log.i("onMove", "delta = " + delta + " offset = " + scrolling.getOffsetPixel());
                if ( delta < 50 && delta > 40 && delta > 1) {
                    isScroll = - 1;
                    scrolling.OnScrollUp();
                } else if ( delta > -50 && delta < -40) {
                    isScroll = 1;
                    scrolling.OnScrollDown();
                }

                break;
            case MotionEvent.ACTION_UP:
              /*  if (isScroll == 1) {
                    scrolling.OnScrollDown();
                    smoothScrollToPosition((scrolling.getSizeCalendar()));
                    isScroll = 0;
                } else if (isScroll == -1) {
                    scrolling.OnScrollUp();
                    smoothScrollToPosition((scrolling.getSizeCalendar()));
                    isScroll = 0;
                }*/
                break;
        }
        return false;
    }
}
