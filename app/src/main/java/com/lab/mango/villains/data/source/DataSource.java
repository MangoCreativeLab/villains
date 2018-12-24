package com.lab.mango.villains.data.source;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;

import java.util.List;

import androidx.annotation.NonNull;

public interface DataSource {
    // note
    void getNotes(@NonNull LoadNoteCallback callback);

    void insertNote(@NonNull Note note);

    void completeNote(@NonNull Note note);

    // note details
    void getNoteDetailList(@NonNull LoadNoteDetailListCallback callback, int noteId);

    void getNoteDetail(@NonNull LoadNoteDetailCallback callback, int noteDetailId);

    void insertNoteDetail(@NonNull NoteDetail noteDetail);

    void completeNoteDetail(@NonNull NoteDetail noteDetail);

    interface LoadNoteCallback {
        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }

    interface LoadNoteDetailListCallback {
        void onNoteDetailListLoaded(List<NoteDetail> noteDetails);

        void onDataNotAvailable();
    }

    interface LoadNoteDetailCallback {

        void onNoteDetailLoaded(NoteDetail noteDetail);

        void onDataNotAvailable();
    }
}
