package com.lab.mango.villains.data;

import java.util.UUID;

import androidx.annotation.Nullable;

public class NoteDetail {

    private final int mId;

    private final String mTitle;

    private final String mDescription;

    private final String mHint;

    private final String mYoutubeUrl;

    private final boolean mCompleted;

    public NoteDetail(@Nullable String title, @Nullable String description, String hint, String youtubeUrl, boolean completed) {
        this(title, description, UUID.randomUUID().hashCode(), hint, youtubeUrl, completed);
    }

    public NoteDetail(@Nullable String title, @Nullable String description,
                      int id, String hint, String youtubeUrl, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mHint = hint;
        mYoutubeUrl = youtubeUrl;
        mCompleted = completed;
    }

    public int getId() {
        return mId;
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
}
