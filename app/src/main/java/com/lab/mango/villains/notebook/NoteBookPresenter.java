package com.lab.mango.villains.notebook;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.source.local.remote.NoteDataSource;
import com.lab.mango.villains.data.source.local.remote.NoteRepository;

import java.util.List;

import androidx.annotation.NonNull;

public class NoteBookPresenter implements NoteBookContract.Presenter {

    private final NoteRepository mNoteRepository;

    private final NoteBookContract.View mNoteBookView;

    NoteBookPresenter(@NonNull NoteRepository notesRepository, @NonNull NoteBookContract.View notesView) {
        mNoteRepository = notesRepository;
        mNoteBookView = notesView;

        mNoteBookView.setPresenter(this);
    }

    @Override
    public void start() {
        loadNotes();
    }

    @Override
    public void loadNotes() {

        mNoteRepository.getNotes(new NoteDataSource.LoadNoteCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                mNoteBookView.showNoteBook(notes);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
