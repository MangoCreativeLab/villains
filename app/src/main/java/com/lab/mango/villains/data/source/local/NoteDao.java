package com.lab.mango.villains.data.source.local;

import com.lab.mango.villains.data.Note;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for the note table.
 */

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note")
    List<Note> getNotes();

    @Query("SELECT * FROM Note WHERE entryid = :noteId")
    Note getNoteById(int noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    int updateNote(Note note);

    @Query(("UPDATE note SET completed = :completed WHERE entryid = :noteId"))
    void updateCompleted(int noteId, boolean completed);
}
