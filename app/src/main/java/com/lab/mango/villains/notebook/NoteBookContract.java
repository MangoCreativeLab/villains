package com.lab.mango.villains.notebook;

import com.lab.mango.villains.BasePresenter;
import com.lab.mango.villains.BaseView;
import com.lab.mango.villains.data.Note;

import java.util.List;

class NoteBookContract {
    interface View extends BaseView<Presenter> {
        void showNoteBook(List<Note> notes);

        void startNotes(Note note);
    }

    interface Presenter extends BasePresenter {
        void loadNotes();
    }
}
