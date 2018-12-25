package com.lab.mango.villains.data;

import java.util.List;
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
    private final List<String> mYoutubeUrl;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @ColumnInfo(name = "prepared")
    private final boolean mPrepared;

    @ColumnInfo(name = "questiontype")
    private final int mQuestionType;

    @ColumnInfo(name = "answer")
    private final String mAnswer;

    @Ignore
    public NoteDetail(@Nullable int noteId,
                      @Nullable String title, @Nullable String description,
                      String hint, List<String> youtubeUrl, boolean completed, boolean prepared,
                      int questionType, String answer) {
        this(noteId, title, description, UUID.randomUUID().hashCode(), hint, youtubeUrl, completed, prepared, questionType, answer);
    }

    public NoteDetail(@Nullable int noteId, @Nullable String title, @Nullable String description,
                      int id, String hint, List<String> youtubeUrl, boolean completed, boolean prepared, int questionType, String answer) {
        mId = id;
        mNoteId = noteId;
        mTitle = title;
        mDescription = description;
        mHint = hint;
        mYoutubeUrl = youtubeUrl;
        mCompleted = completed;
        mPrepared = prepared;
        mQuestionType = questionType;
        mAnswer = answer;
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

    public List<String> getYoutubeUrl() {
        return mYoutubeUrl;
    }

    public boolean getCompleted() {
        return mCompleted;
    }

    public boolean getPrepared() {
        return mPrepared;
    }

    public int getQuestionType() {
        return mQuestionType;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
