package com.lab.mango.villains.data;

import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Note.class,
        parentColumns = "entryid",
        childColumns = "noteid",
        onDelete = CASCADE))

public class NoteDetail {

    @PrimaryKey
    @ColumnInfo(name = "noteDetailId")
    private final int mId;

    @ColumnInfo(name = "noteid")
    private final int mNoteId;

    @ColumnInfo(name = "title")
    private final String mTitle;

    @ColumnInfo(name = "description")
    private final String mDescription;

    @ColumnInfo(name = "hint")
    private final String mHint;

    @ColumnInfo(name = "youtubelink")
    private final String mYoutubeUrl;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @Ignore
    public NoteDetail(@Nullable int noteId, @Nullable String title, @Nullable String description, String hint, String youtubeUrl, boolean completed) {
        this(noteId, title, description, UUID.randomUUID().hashCode(), hint, youtubeUrl, completed);
    }

    public NoteDetail(@Nullable int noteId, @Nullable String title, @Nullable String description,
                      int id, String hint, String youtubeUrl, boolean completed) {
        mId = id;
        mNoteId = noteId;
        mTitle = title;
        mDescription = description;
        mHint = hint;
        mYoutubeUrl = youtubeUrl;
        mCompleted = completed;
    }

    public int getId() {
        return mId;
    }

    public int getNoteId() {
        return mNoteId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getHint() {
        return mHint;
    }

    public String getYoutubeUrl() {
        return mYoutubeUrl;
    }

    public boolean getCompleted() {
        return mCompleted;
    }
}
