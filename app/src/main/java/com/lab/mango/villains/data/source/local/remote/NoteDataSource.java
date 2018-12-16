package com.lab.mango.villains.data.source.local.remote;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;

import java.util.List;

import androidx.annotation.NonNull;

public interface NoteDataSource {
    // note
    void getNotes(@NonNull LoadNoteCallback callback);

    // note details
    void getNoteDetailList(@NonNull LoadNoteDetailListCallback callback, int noteId);

    // note detail
    void getNoteDetail(@NonNull LoadNoteDetailCallback callback, int noteDetailId);

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
