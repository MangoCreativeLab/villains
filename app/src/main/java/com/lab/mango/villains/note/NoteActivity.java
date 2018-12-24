package com.lab.mango.villains.note;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.Injection;
import com.lab.mango.villains.data.source.NoteRepository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_REWARD = "EXTRA_REWARD";
    public static final String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String title = getIntent().getExtras().getString(EXTRA_TITLE);
        getSupportActionBar().setTitle(title);

        final int reward = getIntent().getExtras().getInt(EXTRA_REWARD);
        final int noteId = getIntent().getExtras().getInt(EXTRA_NOTE_ID);

        final Fragment notesFragment = NoteFragment.newInstance();
        final FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragment_container, notesFragment, "1").commit();

        new NotePresenter(noteId, reward, Injection.provideNotesRepository(getApplicationContext()),
                (NoteContract.View) notesFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
