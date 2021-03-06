package com.lab.mango.villains.notedetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.Injection;
import com.lab.mango.villains.notedetail.viewpager.NoteDetailPagerAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class NoteDetailActivity extends androidx.appcompat.app.AppCompatActivity {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_NOTE_DETAIL_IDs = "EXTRA_NOTE_DETAIL_IDs";
    public static final String EXTRA_NOTE_DETAIL_INIT_POS = "EXTRA_NOTE_DETAIL_INIT_POS";

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String title = getIntent().getExtras().getString(EXTRA_TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(5);
        ArrayList<NoteDetailFragment> fragments = new ArrayList<>();

        final int[] noteDetailId = getIntent().getExtras().getIntArray(EXTRA_NOTE_DETAIL_IDs);
        for (int i = 0; i < noteDetailId.length; i++) {
            NoteDetailFragment fragment = NoteDetailFragment.newInstance();
            fragments.add(fragment);
            new NoteDetailPresenter(noteDetailId[i], Injection.provideNotesRepository(getApplicationContext()), fragment);
        }

        final int initPos = getIntent().getExtras().getInt(EXTRA_NOTE_DETAIL_INIT_POS);

        final NoteDetailPagerAdapter adapter = new NoteDetailPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(initPos);
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

    public interface OnBackClickListener {
        boolean onBackClick();
    }

    private OnBackClickListener onBackClickListener;

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackClickListener != null && onBackClickListener.onBackClick()) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("currentPosition", mViewPager.getCurrentItem());
        setResult(RESULT_OK, returnIntent);

        super.finish();
    }
}
