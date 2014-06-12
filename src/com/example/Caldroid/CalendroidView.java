package com.example.Caldroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.GridView;

/**
 * Created by eduard on 12.06.14.
 *
 */

public class CalendroidView extends GridView {
    private final static int GRID_PADDING = 7;
    private final static int NUM_OF_COLUMNS = 7;


    private int mColumnWidth;

    public Context mContext;

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
        float padding = android.util.TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, GRID_PADDING, r.getDisplayMetrics());

        mColumnWidth = (int) ((getScreenWidth() - ((NUM_OF_COLUMNS + 1) * padding))  / NUM_OF_COLUMNS);

        this.setNumColumns(NUM_OF_COLUMNS);
        this.setColumnWidth(mColumnWidth);
        this.setStretchMode(GridView.NO_STRETCH);
        this.setPadding((int)(padding), (int) padding, (int) padding,(int) padding);
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

}
