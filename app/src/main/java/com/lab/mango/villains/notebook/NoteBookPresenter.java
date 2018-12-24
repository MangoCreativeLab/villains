package com.lab.mango.villains.notebook;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.source.DataSource;
import com.lab.mango.villains.data.source.NoteRepository;

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

        mNoteRepository.getNotes(new DataSource.LoadNoteCallback() {
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
