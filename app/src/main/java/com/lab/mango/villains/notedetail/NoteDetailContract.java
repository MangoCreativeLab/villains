package com.lab.mango.villains.notedetail;

import com.lab.mango.villains.BasePresenter;
import com.lab.mango.villains.BaseView;
import com.lab.mango.villains.data.NoteDetail;

public class NoteDetailContract {
    interface View extends BaseView<Presenter> {
        void showNoteDetail(NoteDetail noteDetail);

        void showSolveQuestion();

        void showNotSolveQuestion();
    }

    interface Presenter extends BasePresenter {
        void loadNoteDetail(int id);

        void updateAnswer(String result);
    }
}
