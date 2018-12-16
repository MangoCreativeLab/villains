package com.lab.mango.villains.data.source.local.remote;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.VirtualNoteDetailForTest;
import com.lab.mango.villains.data.VirtualNoteForTest;

import java.util.List;

import androidx.annotation.NonNull;

public class NoteRepository implements NoteDataSource {

    private static NoteRepository INSTANCE = null;

    // Prevent direct instantiation.
    private NoteRepository() {
    }

    public static NoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoteRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getNotes(@NonNull LoadNoteCallback callback) {
        List<Note> notes = VirtualNoteForTest.getData();
        callback.onNotesLoaded(notes);
    }

    @Override
    public void getNoteDetailList(@NonNull LoadNoteDetailListCallback callback, int noteId) {
        List<NoteDetail> notes = VirtualNoteDetailForTest.getNoteDetails(noteId);
        callback.onNoteDetailListLoaded(notes);
    }

    @Override
    public void getNoteDetail(@NonNull LoadNoteDetailCallback callback, int noteDetailId) {
        NoteDetail noteDetail = VirtualNoteDetailForTest.getNoteDetail(noteDetailId);
        callback.onNoteDetailLoaded(noteDetail);
    }
}
