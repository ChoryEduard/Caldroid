package com.example.Caldroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.CalendarGenerator;
import com.example.Caldroid.dateHelper.Day;
import com.example.Caldroid.event.OnScrolling;
import com.example.Caldroid.event.OnSetCurrent;
import com.example.Caldroid.view.HeaderCalendarView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;


/**
 * Created by eduard on 12.06.14.
 * calendar adapter
 * Is class implement OnScrolling event
 * Scroll work (call) in custom Grid View
 */

public final class CalendroidAdapter extends BaseAdapter implements OnScrolling, OnSetCurrent {

    private Context mContext;
    private LayoutInflater inflater;
    private int mColumnWidth;
    private ArrayList<Day> finalCalendar = new ArrayList<Day>();
  /*  private ArrayList<Day> mPrewMonth = new ArrayList<Day>();
    private ArrayList<Day> mCorentMonth = new ArrayList<Day>();
    private ArrayList<Day> mNextMonth = new ArrayList<Day>();*/
    private int sizePrew = 0;
    private int sizeCorent = 0;
    private int sizeNext= 0;

    private HeaderCalendarView headerView;



   /* public class ItemHolder {
        public ImageView imgItem;
        public TextView txtItem;
    }*/

    public CalendroidAdapter(final Context _context, int _columnWidth, HeaderCalendarView header) {
        mContext = _context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColumnWidth = _columnWidth;
        headerView = header;
        init();
    }

    @Override
    public final int getCount() {
        return getSize();
    }

    @Override
    public final Object getItem(final int i) {
        return getDateItem(i);
    }

    @Override
    public final long getItemId(int i) {
        return 0;
    }

    @Override
    public final View getView(final int i, View view, ViewGroup viewGroup) {
        Day day;
        View layout;
        layout = inflater.inflate(R.layout.calendar_item, viewGroup,false);
        layout.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));
        final ImageView imgItem = (ImageView)layout.findViewById(R.id.imgCalItem);
        TextView txtItem = (TextView)layout.findViewById(R.id.tvDateCalItem);

        day = getDateItem(i);
        if (day != null) {
            txtItem.setText(String.valueOf(day.Day));
            imgItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(mContext).load(day.imgURL).into(imgItem);

            Transformation transformation = new Transformation() {

                @Override
                public Bitmap transform(Bitmap source) {
                    int targetWidth = imgItem.getWidth();
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if (result != source) {
                        source.recycle();
                    }
                    return result;
                }

                @Override
                public String key() {
                    return "transformation" + " desiredWidth";
                }
            };

            String mMessage_pic_url = day.imgURL;

            Picasso.with(mContext)
                    .load(mMessage_pic_url)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(imgItem);
            isTransperent(imgItem, i);
        }
        return layout;
    }


    private final int getSize() {
        return finalCalendar.size() + finalCalendar.get(0).getWeekDay();
    }


    private final void isTransperent(ImageView item, final int pos) {
        final int offset =  finalCalendar.get(0).getWeekDay();
        if (sizePrew  > pos - offset || sizePrew + sizeCorent - 2 > offset)
            setTransparent(item, false);
        /*if (mPrewMonth.size() < pos - offset || (mCorentMonth.size() +
                mPrewMonth.size()) < pos - offset)
            setTransparent(item, true);
        else setTransparent(item, false);*/

    }


    private final void setTransparent(ImageView item, boolean isAlpa) {
        if (!isAlpa)
            item.setImageAlpha(50);
        else
            item.setImageAlpha(255);
    }

    /*
    * test init function
    *
     */

    private final void init() {
        CalendarGenerator.toCurrentMonth(mContext);
        CalendarGenerator.getMonthName();
        sizePrew = CalendarGenerator.getPreviousMonthList().size();
        sizeCorent = CalendarGenerator.getCurentMonthList().size();
        sizeNext = CalendarGenerator.getNextMonthList().size();
        finalCalendar = CalendarGenerator.getCurentMonthList();
        //mPrewMonth = CalendarGenerator.getPreviousMonthList();
        for (Day day: CalendarGenerator.getNextMonthList())
                finalCalendar.add(day);
        for (Day day: CalendarGenerator.getPreviousMonthList())
            finalCalendar.add(0, day);
        //mCorentMonth = CalendarGenerator.getCurentMonthList();
        //mNextMonth = CalendarGenerator.getNextMonthList();
        //headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(mCorentMonth.get(0).Year));

    }

    private final Day getDateItem(final int pos) {
        final int offset = finalCalendar.get(0).getWeekDay();
        if (pos < offset) return null;
        return finalCalendar.get(pos - offset);

    }

    @Override
    public void OnScrollUp() {
        Log.i("Scroll", "size = " + getSize());
        CalendarGenerator.toPreviousMonth();
        sizePrew = CalendarGenerator.getPreviousMonthList().size();
        sizeCorent = CalendarGenerator.getCurentMonthList().size();
        sizeNext = CalendarGenerator.getNextMonthList().size();
        finalCalendar = CalendarGenerator.getCurentMonthList();
        //mPrewMonth = CalendarGenerator.getPreviousMonthList();
        for (Day day: CalendarGenerator.getNextMonthList())
            finalCalendar.add(day);
        for (int i = CalendarGenerator.getPreviousMonthList().size() - 1; i >= 0; i--)
            finalCalendar.add(0, CalendarGenerator.getPreviousMonthList().get(i));
        headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(finalCalendar.get(sizePrew).Year));
        notifyDataSetChanged();
    }

    @Override
    public void OnScrollDown() {
        CalendarGenerator.toNextMonth();
        sizePrew = CalendarGenerator.getPreviousMonthList().size();
        sizeCorent = CalendarGenerator.getCurentMonthList().size();
        sizeNext = CalendarGenerator.getNextMonthList().size();
        finalCalendar = CalendarGenerator.getCurentMonthList();
        //mPrewMonth = CalendarGenerator.getPreviousMonthList();
        for (Day day: CalendarGenerator.getNextMonthList())
            finalCalendar.add(day);
        for (int i = CalendarGenerator.getPreviousMonthList().size() - 1; i >= 0; i--)
            finalCalendar.add(0, CalendarGenerator.getPreviousMonthList().get(i));
    }

    @Override
    public int getSizeCalendar() {
        return getSize();
    }

    @Override
    public int getOffsetPixel() {
        return 2;
    }

    @Override
    public final void onSetCurrent() {
        CalendarGenerator.toCurrentMonth(mContext);
        sizePrew = CalendarGenerator.getPreviousMonthList().size();
        sizeCorent = CalendarGenerator.getCurentMonthList().size();
        sizeNext = CalendarGenerator.getNextMonthList().size();
        finalCalendar = CalendarGenerator.getCurentMonthList();
        //mPrewMonth = CalendarGenerator.getPreviousMonthList();
        for (Day day: CalendarGenerator.getNextMonthList())
            finalCalendar.add(day);
        for (int i = CalendarGenerator.getPreviousMonthList().size() - 1; i >= 0; i--)
            finalCalendar.add(0, CalendarGenerator.getPreviousMonthList().get(i));
        headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(finalCalendar.get(sizePrew).Year));
        notifyDataSetChanged();
    }
}

