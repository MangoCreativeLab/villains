package com.lab.mango.villains.data;

import android.content.Context;

import com.lab.mango.villains.data.source.NoteRepository;
import com.lab.mango.villains.data.source.local.NoteDataBase;
import com.lab.mango.villains.data.source.local.NoteLocalDataSource;
import com.lab.mango.villains.utils.AppExecutors;

import androidx.annotation.NonNull;

public class Injection {

    public static NoteRepository provideNotesRepository(@NonNull Context context) {
        NoteDataBase database = NoteDataBase.getInstance(context);

        NoteRepository noteRepository = NoteRepository.getInstance(NoteLocalDataSource.getInstance(
                new AppExecutors(), database.noteDao(), database.noteDetailDao()));
        return noteRepository;
    }
}
