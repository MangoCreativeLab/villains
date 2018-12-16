package com.lab.mango.villains.notedetail;

import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.source.local.remote.NoteDataSource;
import com.lab.mango.villains.data.source.local.remote.NoteRepository;

import androidx.annotation.NonNull;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private final NoteRepository mNoteRepository;

    private final NoteDetailContract.View mNoteDetailView;

    private final int mNoteDetailId;

    public NoteDetailPresenter(int noteDetailId, @NonNull NoteRepository notesRepository, @NonNull NoteDetailContract.View noteDetailView) {
        mNoteDetailId = noteDetailId;

        mNoteRepository = notesRepository;
        mNoteDetailView = noteDetailView;

        mNoteDetailView.setPresenter(this);
    }

    @Override
    public void loadNoteDetail(int noteDetailId) {
        mNoteRepository.getNoteDetail(new NoteDataSource.LoadNoteDetailCallback() {
            @Override
            public void onNoteDetailLoaded(NoteDetail noteDetail) {
                mNoteDetailView.showNoteDetail(noteDetail);
            }

            @Override
            public void onDataNotAvailable() {

            }
        }, noteDetailId);

    }

    @Override
    public void start() {
        loadNoteDetail(mNoteDetailId);
    }
}
