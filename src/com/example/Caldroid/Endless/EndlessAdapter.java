package com.example.Caldroid.Endless;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.Day;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruSD on 6/17/2014.
 */
public class EndlessAdapter extends ArrayAdapter<Day> {

    private List<Day> itemList;
    private Context ctx;
    private int layoutId;
    private int mColumnWidth;

    public EndlessAdapter(Context ctx, List<Day> itemList, int layoutId, int mColumnWidth ) {
        super(ctx, layoutId, itemList);
        this.itemList = itemList;
        this.ctx = ctx;
        this.layoutId = layoutId;
        this.mColumnWidth = mColumnWidth;
    }

    public List<Day> getItemList() {
        return itemList;
    }

    @Override
    public int getCount() {
        return itemList.size() + itemList.get(0).getWeekDay() ;
    }

    @Override
    public Day getItem(int position) {
        return itemList.get(position);
    }
    private final Day getDateItem(final int pos) {
        final int offset = itemList.get(0).getWeekDay();
        if (pos < offset) return null;
        return itemList.get(pos - offset);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;


            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            result = inflater.inflate(R.layout.calendar_item, parent, false);
            result.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));



        // We should use class holder pattern
        TextView tv = (TextView) result.findViewById(R.id.tvDateCalItem);
        final ImageView imgItem = (ImageView)result.findViewById(R.id.imgCalItem);
        Day day =  getDateItem(position);

//        tv.setText(String.valueOf(day.Day));
        if (day != null) {
            tv.setText(String.valueOf(day.Day));
            imgItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(ctx).load(day.imgURL).into(imgItem);

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

            Picasso.with(ctx)
                    .load(mMessage_pic_url)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(imgItem);
            isTransperent(imgItem, position);
        }
        return result;

    }


    private final void isTransperent(ImageView item, final int pos) {
        final int offset =  itemList.get(0).getWeekDay();
        if (30  > pos - offset || 30 + 30 - 2 < pos)
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


}
