package com.lab.mango.villains.data.source.local;


import android.content.Context;

import com.lab.mango.villains.data.Note;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.utils.Converters;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

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
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NoteDataBase.class, "Note.db").build();
            }

            return INSTANCE;
        }
    }
}
