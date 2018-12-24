package com.lab.mango.villains.data.source.local;

import com.lab.mango.villains.data.NoteDetail;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data Access Object for the note detail table.
 */

@Dao
public interface NoteDetailDao {

    @Query("SELECT * FROM NoteDetail")
    List<NoteDetail> getNoteDetails();

    @Query("SELECT * FROM NoteDetail WHERE noteid = :noteid")
    List<NoteDetail> getNoteDetailsByNoteId(int noteid);

    @Query("SELECT * FROM NoteDetail WHERE noteDetailId = :noteDetailId")
    NoteDetail getNoteDetailById(int noteDetailId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNoteDetail(NoteDetail noteDetail);

    @Update
    int updateNoteDetail(NoteDetail noteDetail);

    @Query(("UPDATE noteDetail SET completed = :completed WHERE noteDetailId = :noteDetailId"))
    void updateCompleted(int noteDetailId, boolean completed);
}
