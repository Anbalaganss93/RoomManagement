package com.roommanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent creatintent = new Intent(CreateNotes.this, NotesListActivity.class);
        startActivity(creatintent);
        finish();
    }
}
