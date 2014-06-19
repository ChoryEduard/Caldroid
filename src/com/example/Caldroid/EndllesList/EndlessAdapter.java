package com.example.Caldroid.EndllesList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.Day;
import com.example.Caldroid.dateHelper.Week;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruSD on 6/18/2014.
 */
public class EndlessAdapter extends ArrayAdapter<Week> {

    private ArrayList<Week> itemList;
    private Context ctx;
    private int layoutId;
    private int cellWeith;
    private int currentMonth;
    private int currentYear;

    public EndlessAdapter(Context ctx, ArrayList<Week> itemList, int layoutId, int cellWeith) {
        super(ctx, layoutId, itemList);
        this.itemList = itemList;
        this.ctx = ctx;
        this.layoutId = layoutId;
        this.cellWeith = cellWeith;
        this.currentMonth = itemList.get(7).get(0).Month;
        this.currentYear = itemList.get(7).get(0).Year;
    }

    @Override
    public int getCount() {
        return itemList.size() ;
    }

    @Override
    public Week getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inflater.inflate(R.layout.row_layout, parent, false);

        RelativeLayout sunDay, monDay, tuesDay, wednesDay, thursDay, friDay, saturDay;
        sunDay = (RelativeLayout)result.findViewById(R.id.rel0);
        monDay = (RelativeLayout)result.findViewById(R.id.rel1);
        tuesDay = (RelativeLayout)result.findViewById(R.id.rel2);
        wednesDay = (RelativeLayout)result.findViewById(R.id.rel3);
        thursDay = (RelativeLayout)result.findViewById(R.id.rel4);
        friDay = (RelativeLayout)result.findViewById(R.id.rel5);
        saturDay = (RelativeLayout)result.findViewById(R.id.rel6);


        sunDay.getLayoutParams().height = cellWeith;
        sunDay.getLayoutParams().width = cellWeith;

        monDay.getLayoutParams().height = cellWeith;
        monDay.getLayoutParams().width = cellWeith;

        tuesDay.getLayoutParams().height = cellWeith;
        tuesDay.getLayoutParams().width = cellWeith;

        wednesDay.getLayoutParams().height = cellWeith;
        wednesDay.getLayoutParams().width = cellWeith;

        thursDay.getLayoutParams().height = cellWeith;
        thursDay.getLayoutParams().width = cellWeith;

        friDay.getLayoutParams().height = cellWeith;
        friDay.getLayoutParams().width = cellWeith;

        saturDay.getLayoutParams().height = cellWeith;
        saturDay.getLayoutParams().width = cellWeith;


        // We should use class holder pattern

        Week week = itemList.get(position);
        TextView sunDayText, monDayText, tuesDayText, wednesDayText, thursDayText, friDayText, saturDayText;
        sunDayText = (TextView)result.findViewById(R.id.day_text0);
        monDayText = (TextView)result.findViewById(R.id.day_text1);
        tuesDayText = (TextView)result.findViewById(R.id.day_text2);
        wednesDayText = (TextView)result.findViewById(R.id.day_text3);
        thursDayText = (TextView)result.findViewById(R.id.day_text4);
        friDayText = (TextView)result.findViewById(R.id.day_text5);
        saturDayText = (TextView)result.findViewById(R.id.day_text6);

        ImageView sunDayImage, monDayImage, tuesDayImage, wednesDayImage, thursDayImage, friDayImage, saturDayImage;
        sunDayImage = (ImageView)result.findViewById(R.id.day_image0);
        monDayImage = (ImageView)result.findViewById(R.id.day_image1);
        tuesDayImage = (ImageView)result.findViewById(R.id.day_image2);
        wednesDayImage = (ImageView)result.findViewById(R.id.day_image3);
        thursDayImage = (ImageView)result.findViewById(R.id.day_image4);
        friDayImage = (ImageView)result.findViewById(R.id.day_image5);
        saturDayImage = (ImageView)result.findViewById(R.id.day_image6);

        Day day;


        if (week.get(0)!= null){
            day = week.get(0);
            sunDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(sunDayImage);
            if(!day.isCurrentMonthDay()){
                sunDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(1)!= null){
            day = week.get(1);
            monDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(monDayImage);
            if(!day.isCurrentMonthDay()){
                monDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(2)!= null){
            day = week.get(2);
            tuesDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(tuesDayImage);
            if(!day.isCurrentMonthDay()){
                tuesDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(3)!= null){
            day = week.get(3);
            wednesDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(wednesDayImage);
            if(!day.isCurrentMonthDay()){
                wednesDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(4)!= null){
            day = week.get(4);
            thursDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(thursDayImage);
            if(!day.isCurrentMonthDay()){
                thursDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(5)!= null){
            day = week.get(5);
            friDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(friDayImage);
            if(!day.isCurrentMonthDay()){
                friDayImage.setAlpha(0.2f);
            }
        }
        if (week.get(6)!= null){
            day = week.get(6);
            saturDayText.setText(String.valueOf(day.Day));

            Picasso.with(ctx)
                    .load(day.imgURL)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(saturDayImage);
            if(!day.isCurrentMonthDay()){
                saturDayImage.setAlpha(0.2f);
            }
        }
        return result;

    }



    Transformation transformation = new Transformation() {
        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth = cellWeith;
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

    public void addToEndWithGlue(List<Week> list){
        int firstNewDay = 0;
        for ( int j = 0; j < 7; j++) {
            if (list.get(0).get(j) != null) {
                firstNewDay  = list.get(0).get(j).getWeekDay();
                break;
            }
        }
        if (firstNewDay == 0 ){
            itemList.addAll(list);
        }else {
            Week week = list.get(0);
            for (int i = firstNewDay; i < 7; i++) {
                itemList.get(itemList.size() - 1).add(i, week.get(i));
            }
            list.remove(0);
            itemList.addAll(list);
        }
    }

    public void addToStartWithGlue(List<Week> list){
        int firstOldDay = 0;
        for ( int j = 6; j >= 0; ) {
            if (list.get(list.size()-1).get(j) != null) {
                firstOldDay  = list.get(list.size()-1).get(j).getWeekDay();
                break;
            }
            j--;
        }
        if (firstOldDay == 6 ){
            for (int i = list.size()-1; i >=0 ; ) {
                itemList.add(0, list.get(i));
                i--;
            }
        }else {
            Week week = itemList.get(0);
            for (int i = firstOldDay + 1; i <= 6; i++) {

                list.get(list.size()-1).add(i, week.get(i));
            }
            itemList.remove(0);
            for (int i =list.size()-1; i >= 0;  ) {
                itemList.add(0, list.get(i));
                i--;
            }
        }
    }
    public void setNextMonthToCurrent(int lastItem){
        Week week =  itemList.get(lastItem);
        boolean isNeeadSwich = false;
        for (int i = 0; i < 7; i++){
            if(week.get(i) != null) {
                if ((week.get(i).Month > (currentMonth + 1) && week.get(i).Year == currentYear) ||
                        (week.get(i).Month < (currentMonth ) && week.get(i).Year > currentYear)) {
                    isNeeadSwich = true;
                    currentMonth = week.get(i).Month;
                    currentYear = week.get(i).Year;
                }
            }
        }
        if (isNeeadSwich){
            int startSwichIndex = -1;
            for (int i = 0; i < itemList.size(); i++){
                if(itemList.get(i).get(0) != null) {
                    if (itemList.get(i).get(0).isCurrentMonthDay()) {
                        startSwichIndex = i - 1;
                        break;
                    }
                }
            }
            int endSwichIndex = startSwichIndex;
            for (int i = startSwichIndex; i < itemList.size(); i++){
                if (itemList.get(i).get(0).Month > (currentMonth) && itemList.get(i).get(0).Year == currentYear) {
                    endSwichIndex =  i - 1;
                    break;
                }
                for (int j = 0; j < 7; j++) {
                    if(itemList.get(i).get(j) != null) {
                        if (itemList.get(i).get(j).isCurrentMonthDay()) {
                            itemList.get(i).get(j).setIsCorrentMonthDay(false);
                        }
                    }
                }
            }
            for (int i = endSwichIndex; i < itemList.size(); i++){
                for (int j = 0; j < 7; j++) {
                    if(itemList.get(i).get(j) != null) {
                        if (itemList.get(i).get(j).Month == currentMonth) {
                            itemList.get(i).get(j).setIsCorrentMonthDay(true);
                        }
                    }
                }
            }
        }

    }
}