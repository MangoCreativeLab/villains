package com.lab.mango.villains.notedetail;

import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.source.DataSource;
import com.lab.mango.villains.data.source.NoteRepository;

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
        mNoteRepository.getNoteDetail(new DataSource.LoadNoteDetailCallback() {
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
    public void updateAnswer(final String result) {
        mNoteRepository.getNoteDetail(new DataSource.LoadNoteDetailCallback() {
            @Override
            public void onNoteDetailLoaded(NoteDetail noteDetail) {
                if (noteDetail.getAnswer().equals(result)) {
                    mNoteRepository.completeNoteDetail(noteDetail);
                    mNoteDetailView.showSolveQuestion();
                } else {
                    mNoteDetailView.showNotSolveQuestion();
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        }, mNoteDetailId);
    }

    @Override
    public void start() {
        loadNoteDetail(mNoteDetailId);
    }
}
