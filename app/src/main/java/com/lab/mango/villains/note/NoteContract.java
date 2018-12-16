package com.lab.mango.villains.note;

import com.lab.mango.villains.BasePresenter;
import com.lab.mango.villains.BaseView;
import com.lab.mango.villains.data.NoteDetail;

import java.util.List;

class NoteContract {
    interface View extends BaseView<Presenter> {
        void showNoteDetails(List<NoteDetail> noteDetails);

        void showReward(int reward);

        void startNoteDetail(List<NoteDetail> noteDetails, NoteDetail selectedNoteDetail);
    }

    interface Presenter extends BasePresenter {
        void loadNoteDetails(int noteId);

        void loadNoteDetails(NoteDetail clickedDetail);
    }
}
