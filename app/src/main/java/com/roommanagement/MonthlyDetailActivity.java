package com.roommanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.roommanagement.Utils.Util;
import com.roommanagement.adapter.DailyDetailAdapter;
import com.roommanagement.dto.DailyModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthlyDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mMonthlyViewRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DailyModel> dailylist = new ArrayList<>();
    private DailyDetailAdapter adapter;
    private RelativeLayout viewConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_detail);

        init();

        Util.getCurrentDate();
        Calendar mycal = new GregorianCalendar(Util.mCurrentyear, Util.mCurrentMonth, Util.mCurrentDay);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < daysInMonth; i++) {
            DailyModel mDailyModel = new DailyModel();
            mDailyModel.setDay(String.valueOf(i + 1));
            dailylist.add(mDailyModel);
        }

//        adapter = new DailyDetailAdapter(MonthlyDetailActivity.this, dailylist);
//        mMonthlyViewRecycler.setAdapter(adapter);
    }
    private void init() {
        mMonthlyViewRecycler = (RecyclerView) findViewById(R.id.dashboard_recyclerview);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        mMonthlyViewRecycler.setLayoutManager(mLayoutManager);
        mMonthlyViewRecycler.setItemAnimator(new DefaultItemAnimator());

        viewConverter = (RelativeLayout) findViewById(R.id.viewConverter);

        viewConverter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewConverter:
                break;
        }
    }
}
