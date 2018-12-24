package com.lab.mango.villains.data.source;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;

import java.util.List;

import androidx.annotation.NonNull;

public class NoteRepository implements DataSource {

    private static NoteRepository INSTANCE = null;

    private final DataSource mNoteLocalDataSource;

    // Prevent direct instantiation.
    private NoteRepository(DataSource noteLocalDataSource) {
        mNoteLocalDataSource = noteLocalDataSource;
    }

    public static NoteRepository getInstance(DataSource noteLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NoteRepository(noteLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getNotes(@NonNull final LoadNoteCallback callback) {
        mNoteLocalDataSource.getNotes(new LoadNoteCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                callback.onNotesLoaded(notes);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void insertNote(@NonNull Note note) {
        mNoteLocalDataSource.insertNote(note);
    }

    @Override
    public void completeNote(@NonNull Note note) {
        mNoteLocalDataSource.completeNote(note);
    }

    @Override
    public void getNoteDetailList(@NonNull final LoadNoteDetailListCallback callback, int noteId) {
        mNoteLocalDataSource.getNoteDetailList(new LoadNoteDetailListCallback() {
            @Override
            public void onNoteDetailListLoaded(List<NoteDetail> noteDetails) {
                callback.onNoteDetailListLoaded(noteDetails);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        }, noteId);
    }

    @Override
    public void getNoteDetail(@NonNull final LoadNoteDetailCallback callback, int noteDetailId) {
        mNoteLocalDataSource.getNoteDetail(new LoadNoteDetailCallback() {
            @Override
            public void onNoteDetailLoaded(NoteDetail noteDetail) {
                callback.onNoteDetailLoaded(noteDetail);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        }, noteDetailId);
    }

    @Override
    public void insertNoteDetail(@NonNull NoteDetail noteDetail) {
        mNoteLocalDataSource.insertNoteDetail(noteDetail);
    }

    @Override
    public void completeNoteDetail(@NonNull NoteDetail noteDetail) {
        mNoteLocalDataSource.completeNoteDetail(noteDetail);
    }
}
