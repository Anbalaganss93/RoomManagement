package com.roommanagement.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roommanagement.MainActivity;
import com.roommanagement.R;
import com.roommanagement.dto.DailyModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android on 11-Aug-16.
 */
public class DailyDetailAdapter extends RecyclerView.Adapter<DailyDetailAdapter.MyHolder> {
    Context context;
    ArrayList<DailyModel> arrayList;
    private Typeface typeface, typefacebold;

    public DailyDetailAdapter(MainActivity mainActivity, ArrayList<DailyModel> dailylist) {
        this.arrayList = dailylist;
        this.context = mainActivity;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dailydetail_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        DailyModel m = (DailyModel) arrayList.get(position);
        holder.dailydetail_date.setText(m.getDay());
        Random rnd = new Random();
//        int color = Color.argb(255, rnd.nextInt(50), rnd.nextInt(50), rnd.nextInt(50));
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.dailydetail_date.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView dailydetail_date;

        public MyHolder(View itemView) {
            super(itemView);
            dailydetail_date = (TextView) itemView.findViewById(R.id.dailydetail_date);
        }
    }


}
