package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String Note_Info = "com.example.notekeeper.Note_Info";
    private NoteInfo note;
    private NoteInfo mnote;
    private boolean misNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerCourses = (Spinner)findViewById(R.id.id_spinner_courses);

        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adaptercourses =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adaptercourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adaptercourses);

        readDisplaySetValues();

        EditText textNoteTitle = (EditText)findViewById(R.id.id_text_note_title);
        EditText textNoteText = (EditText)findViewById(R.id.id_text_note_text);

        if(misNewNote){
            displayNotes(spinnerCourses, textNoteTitle, textNoteText);
        }
    }

    private void displayNotes(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(mnote.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(mnote.getTitle());
        textNoteText.setText(mnote.getText());
    }

    private void readDisplaySetValues() {
        Intent intent = getIntent();
        mnote = intent.getParcelableExtra(Note_Info);
        misNewNote = mnote==null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
