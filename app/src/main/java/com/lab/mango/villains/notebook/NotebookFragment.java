package com.lab.mango.villains.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.note.NoteActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class NotebookFragment extends Fragment implements NoteBookContract.View {

    private NoteBookContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private NotebookRecyclerViewAdapter mAdapter;
    private NoteItemListener mNoteItemListener = new NoteItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            startNotes(clickedNote);
        }
    };

    public static NotebookFragment newInstance() {
        NotebookFragment fragment = new NotebookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notebook_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NotebookRecyclerViewAdapter(getContext(), new ArrayList<Note>(0), mNoteItemListener);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        // TODO : handler is unnecessary, it's just because of preSaved test data.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.start();
            }
        }, 100);
    }

    @Override
    public void showNoteBook(List<Note> notes) {
        Log.d("MY_LOG", "showNoteBook : " + notes);
        mAdapter.replaceData(notes);
    }

    @Override
    public void startNotes(Note note) {
        Intent intent = new Intent();
        intent.putExtra(NoteActivity.EXTRA_TITLE, note.getTitle());
        intent.putExtra(NoteActivity.EXTRA_REWARD, note.getReward());
        intent.putExtra(NoteActivity.EXTRA_NOTE_ID, note.getId());

        intent.setClass(getContext(), NoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(NoteBookContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public interface NoteItemListener {
        void onNoteClick(Note clickedNote);
    }
}
