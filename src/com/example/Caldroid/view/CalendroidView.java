package com.example.Caldroid.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridView;

import com.example.Caldroid.event.OnScrolling;

/**
 * Created by eduard on 12.06.14.
 *
 */

public final class CalendroidView extends GridView {

    public final static int GRID_PADDING = 2;
    public final static int NUM_OF_COLUMNS = 7;
    public Context mContext;
    private OnScrolling scrolling;
    private int mColumnWidth;
    private int mPanding;

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


    @Override
    protected int computeVerticalScrollOffset(){
        Log.i("Scroll", " state i = " + super.computeVerticalScrollOffset());
        if (super.computeVerticalScrollOffset() == 0) scrolling.OnScrollUp();
        return super.computeVerticalScrollOffset();
    }


    public final void setScroll(final OnScrolling _scroll) {
        scrolling = _scroll;
    }


}
