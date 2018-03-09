package com.roommanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roommanagement.MainActivity;
import com.roommanagement.NotesDetailViewActivity;
import com.roommanagement.NotesListActivity;
import com.roommanagement.R;
import com.roommanagement.dto.DailyModel;
import com.roommanagement.dto.NotesModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android on 11-Aug-16.
 */
public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.MyHolder> {
    Context context;
    ArrayList<NotesModel> arrayList;
    private Typeface typeface, typefacebold;


    public NotesListAdapter(NotesListActivity notesListActivity, ArrayList<NotesModel> arrayList) {
        this.arrayList = arrayList;
        this.context = notesListActivity;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notesdetail_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        NotesModel m = (NotesModel) arrayList.get(position);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.mTitle.setTextColor(color);
        holder.mNotesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailnotesintent = new Intent(context, NotesDetailViewActivity.class);
                context.startActivity(detailnotesintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        private RelativeLayout mNotesContainer;

        public MyHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mNotesContainer = (RelativeLayout) itemView.findViewById(R.id.notescontainer);
        }
    }


}
