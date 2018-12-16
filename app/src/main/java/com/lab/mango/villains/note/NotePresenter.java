package com.lab.mango.villains.note;

import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.source.local.remote.NoteDataSource;
import com.lab.mango.villains.data.source.local.remote.NoteRepository;

import java.util.List;

import androidx.annotation.NonNull;

public class NotePresenter implements NoteContract.Presenter {
    private final NoteRepository mNoteRepository;

    private final NoteContract.View mNoteDetailView;

    private final int mNoteId;

    private final int mReward;

    public NotePresenter(int noteId, int reward, @NonNull NoteRepository notesRepository, @NonNull NoteContract.View notesView) {
        mNoteId = noteId;
        mReward = reward;

        mNoteRepository = notesRepository;
        mNoteDetailView = notesView;

        mNoteDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        loadNoteDetails(mNoteId);
    }

    @Override
    public void loadNoteDetails(int noteId) {
        mNoteRepository.getNoteDetailList(new NoteDataSource.LoadNoteDetailListCallback() {
            @Override
            public void onNoteDetailListLoaded(List<NoteDetail> noteDetails) {
                mNoteDetailView.showNoteDetails(noteDetails);
            }

            @Override
            public void onDataNotAvailable() {

            }
        }, noteId);

        mNoteDetailView.showReward(mReward);
    }

    @Override
    public void loadNoteDetails(final NoteDetail clickedDetail) {
        mNoteRepository.getNoteDetailList(new NoteDataSource.LoadNoteDetailListCallback() {
            @Override
            public void onNoteDetailListLoaded(List<NoteDetail> noteDetails) {
                mNoteDetailView.startNoteDetail(noteDetails, clickedDetail);
            }

            @Override
            public void onDataNotAvailable() {

            }
        }, mNoteId);
    }
}
