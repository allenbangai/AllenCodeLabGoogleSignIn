package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteListActivity.this, MainActivity.class));
            }
        });

        intializeDisplayContent();
    }

    private void intializeDisplayContent() {
        final ListView listnotes = (ListView)findViewById(R.id.id_note_list);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        ArrayAdapter<NoteInfo> adapternotes =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listnotes.setAdapter(adapternotes);

        listnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoteListActivity.this, MainActivity.class);
                NoteInfo note = (NoteInfo)listnotes.getItemAtPosition(position);
                intent.putExtra(MainActivity.Note_Info, note);

                startActivity(intent);
            }
        });
    }

}
