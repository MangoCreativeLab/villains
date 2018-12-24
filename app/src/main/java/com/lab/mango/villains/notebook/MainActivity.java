package com.lab.mango.villains.notebook;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab.mango.villains.R;
import com.lab.mango.villains.data.Injection;
import com.lab.mango.villains.data.source.NoteRepository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private Fragment mActiveFragment;

    private FragmentManager mfragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("사건 수첩");

        final Fragment notebookFragment = NotebookFragment.newInstance();
        final Fragment moneyFragment = MoneyFragment.newInstance();
        final Fragment settingFragment = SettingFragment.newInstance();

        mfragmentManager = getSupportFragmentManager();
        addFragments(notebookFragment, "notebook_fragment");
        addFragments(moneyFragment, "money_fragnoment");
        addFragments(settingFragment, "setting_fragment");

        mActiveFragment = notebookFragment;
        mfragmentManager.beginTransaction().show(mActiveFragment).commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_notebook:
                        mfragmentManager.beginTransaction().hide(mActiveFragment).show(notebookFragment).commit();
                        mActiveFragment = notebookFragment;
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("사건 수첩");
                        }
                        return true;
                    case R.id.action_money:
                        mfragmentManager.beginTransaction().hide(mActiveFragment).show(moneyFragment).commit();
                        mActiveFragment = moneyFragment;
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("수익금");
                        }
                        return true;
                    case R.id.action_setting:
                        mfragmentManager.beginTransaction().hide(mActiveFragment).show(settingFragment).commit();
                        mActiveFragment = settingFragment;
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle("사용자 설정");
                        }
                        return true;
                }
                return false;
            }
        });

        new NoteBookPresenter(Injection.provideNotesRepository(getApplicationContext()),
                (NoteBookContract.View) notebookFragment);
    }

    private void addFragments(Fragment fragment, String tag) {
        mfragmentManager.beginTransaction().add(R.id.fragment_container, fragment, tag).hide(fragment).commit();
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
