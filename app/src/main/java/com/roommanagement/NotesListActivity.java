package com.roommanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.roommanagement.adapter.NotesListAdapter;
import com.roommanagement.dto.NotesModel;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerview;
    private LinearLayoutManager mLayoutManager;
    private NotesListAdapter adapter;
    private ArrayList<NotesModel> arrayList =new ArrayList<>(10);
    private ImageView createnotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        init();
    }

    private void init() {
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        createnotes=(ImageView)findViewById(R.id.createnotes);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new NotesListAdapter(NotesListActivity.this,arrayList);
        recyclerview.setAdapter(adapter);

        createnotes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createnotes:
                Intent creatintent =new Intent(NotesListActivity.this,CreateNotes.class);
                startActivity(creatintent);
                finish();
                break;
        }
    }
}
