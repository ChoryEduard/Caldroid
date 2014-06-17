package com.example.Caldroid.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private int sizePrew = 0;
    private int sizeCorent = 0;
    private int sizeNext= 0;
    private boolean isloadImg = true;
    private boolean isEndAnimation = true;

    private HeaderCalendarView headerView;

    public CalendroidAdapter(final Context _context, int _columnWidth, HeaderCalendarView header) {

        mContext = _context;
        headerView = header;
        init();
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColumnWidth = _columnWidth;

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
        final View layout;
        layout = inflater.inflate(R.layout.calendar_item, viewGroup, false);
        layout.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));

        if (i < 30 && isEndAnimation) {
           if (layout.getVisibility() != View.VISIBLE) {
               Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.smooth);
               anim.setAnimationListener(new Animation.AnimationListener() {
                   @Override
                   public void onAnimationStart(Animation animation) {

                   }

                   @Override
                   public void onAnimationEnd(Animation animation) {
                       layout.setVisibility(View.VISIBLE);
                   }

                   @Override
                   public void onAnimationRepeat(Animation animation) {

                   }
               });
               if (i == 29) isEndAnimation = false;
               layout.startAnimation(anim);
           }
        }else {
            layout.setVisibility(View.VISIBLE);
        }

        final ImageView imgItem = (ImageView)layout.findViewById(R.id.imgCalItem);
        TextView txtItem = (TextView)layout.findViewById(R.id.tvDateCalItem);

        day = getDateItem(i);
        if (day != null) {
            txtItem.setText(String.valueOf(day.Day));
            imgItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (isloadImg) {
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
            }
            isTransperent(imgItem, i);
        }
        return layout;
    }



    private final int getSize() {
        return finalCalendar.size() + finalCalendar.get(0).getWeekDay();
    }


    private final void isTransperent(ImageView item, final int pos) {
        final int offset =  finalCalendar.get(0).getWeekDay();
        if (sizePrew  > pos - offset || sizePrew + sizeCorent - 2 < pos)
            setTransparent(item, false);
        else
            setTransparent(item, true);
    }


    private final void setTransparent(ImageView item, boolean isAlpa) {
        if (!isAlpa)
            item.setAlpha(50);
        else
            item.setAlpha(255);
    }

    /*
    * test init function
    *
     */

    private final void init() {
        CalendarGenerator.toCurrentMonth(mContext);
        sizePrew = CalendarGenerator.getPreviousMonthList().size();
        sizeCorent = CalendarGenerator.getCurentMonthList().size();
        sizeNext = CalendarGenerator.getNextMonthList().size();
        finalCalendar = CalendarGenerator.getCurentMonthList();
        for (Day day: CalendarGenerator.getNextMonthList())
            finalCalendar.add(day);
        for (int i = CalendarGenerator.getPreviousMonthList().size() - 1; i >= 0; i--)
            finalCalendar.add(0, CalendarGenerator.getPreviousMonthList().get(i));
        headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(finalCalendar.get(sizePrew).Year));
    }

    private final Day getDateItem(final int pos) {
        final int offset = finalCalendar.get(0).getWeekDay();
        if (pos < offset) return null;
        return finalCalendar.get(pos - offset);

    }

    @Override
    public void OnScrollUp() {
        isEndAnimation = true;
        Log.i("Scroll", "size = " + getSize());
        CalendarGenerator.toPreviousMonth();

        ArrayList<Day> day = CalendarGenerator.getPreviousMonthList();
        for (int i = day.size() - 1; i < 0; i++) {
            finalCalendar.add(0, day.get(i));
        }
        /*for (Day day: CalendarGenerator.getNextMonthList())
            finalCalendar.add(day);
        for (int i = CalendarGenerator.getPreviousMonthList().size() - 1; i >= 0; i--)
            finalCalendar.add(0, CalendarGenerator.getPreviousMonthList().get(i));*/
        headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(finalCalendar.get(sizePrew).Year));
        notifyDataSetChanged();
    }

    @Override
    public void OnScrollDown() {
        CalendarGenerator.toNextMonth();
        ArrayList<Day> day = CalendarGenerator.getNextMonthList();
        for (int i = 0; i < day.size(); i++) {
            finalCalendar.add(day.get(i));
        }
        headerView.setDateHeader(String.valueOf(CalendarGenerator.getMonthName()), String.valueOf(finalCalendar.get(sizePrew).Year));

        notifyDataSetChanged();
    }

    @Override
    public int getSizeCalendar() {
        return getSize();
    }

    @Override
    public int getOffsetPixel() {
        return mColumnWidth * 4;
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

