package com.lab.mango.villains.data.source.local;


import android.content.Context;
import android.util.Log;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.TestNoteData;
import com.lab.mango.villains.data.TestNoteDetailData;
import com.lab.mango.villains.utils.Converters;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * The Room Database that contains the Note table.
 */

@Database(entities = {Note.class, NoteDetail.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase INSTANCE;

    public abstract NoteDao noteDao();

    public abstract NoteDetailDao noteDetailDao();

    private static final Object sLock = new Object();

    public static NoteDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                        NoteDataBase.class, "Note.db").build();
                INSTANCE = buildPreDatabase(context);
            }

            return INSTANCE;
        }
    }

    // For Test (load test data)
    private static NoteDataBase buildPreDatabase(final Context context) {
        return Room.databaseBuilder(context,
                NoteDataBase.class,
                "Note.db").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        for (Note note : TestNoteData.getData()) {
                            getInstance(context).noteDao().insertNote(note);
                        }

                        for (int i = 0; i < 5; i++) {
                            for (NoteDetail detail : TestNoteDetailData.getNoteDetails(i)) {
                                getInstance(context).noteDetailDao().insertNoteDetail(detail);
                            }
                        }
                    }
                });
            }
        }).build();
    }
}
