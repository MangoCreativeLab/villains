package com.lab.mango.villains.data.source.local;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.source.DataSource;
import com.lab.mango.villains.utils.AppExecutors;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Concrete implementation of a data source as a db.
 */

public class NoteLocalDataSource implements DataSource {

    private static volatile NoteLocalDataSource INSTANCE;

    private NoteDao mNoteDao;

    private NoteDetailDao mNoteDetailDao;

    private AppExecutors mAppExcutors;

    private NoteLocalDataSource(AppExecutors appExecutors, NoteDao noteDao, NoteDetailDao noteDetailDao) {
        mAppExcutors = appExecutors;
        mNoteDao = noteDao;
        mNoteDetailDao = noteDetailDao;
    }

    public static NoteLocalDataSource getInstance(AppExecutors appExecutors, NoteDao noteDao, NoteDetailDao noteDetailDao) {
        if (INSTANCE == null) {
            synchronized (NoteLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteLocalDataSource(appExecutors, noteDao, noteDetailDao);
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void getNotes(@NonNull final LoadNoteCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Note> notes = mNoteDao.getNotes();
                mAppExcutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (notes.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onNotesLoaded(notes);
                        }
                    }
                });
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void insertNote(@NonNull final Note note) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mNoteDao.insertNote(note);
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void completeNote(@NonNull final Note note) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mNoteDao.updateCompleted(note.getId(), true);
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void getNoteDetailList(@NonNull final LoadNoteDetailListCallback callback, final int noteId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<NoteDetail> noteDetails = mNoteDetailDao.getNoteDetailsByNoteId(noteId);
                mAppExcutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (noteDetails.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onNoteDetailListLoaded(noteDetails);
                        }
                    }
                });
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void getNoteDetail(@NonNull final LoadNoteDetailCallback callback, final int noteDetailId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final NoteDetail noteDetail = mNoteDetailDao.getNoteDetailById(noteDetailId);
                mAppExcutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (noteDetail == null) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onNoteDetailLoaded(noteDetail);
                        }
                    }
                });
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void insertNoteDetail(@NonNull final NoteDetail noteDetail) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mNoteDetailDao.insertNoteDetail(noteDetail);
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }

    @Override
    public void completeNoteDetail(@NonNull final NoteDetail noteDetail) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mNoteDetailDao.updateCompleted(noteDetail.getId(), true);
            }
        };

        mAppExcutors.diskIO().execute(runnable);
    }
}
