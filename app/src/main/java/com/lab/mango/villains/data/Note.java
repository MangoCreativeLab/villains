package com.lab.mango.villains.data;

import java.util.UUID;

import androidx.annotation.Nullable;

public class Note {

    private final int mId;

    private final String mTitle;

    private final String mDescription;

    private final int mReward;

    private final int mImage;

    private final boolean mCompleted;

    public Note(@Nullable String title, @Nullable String description, int reward, int image, boolean completed) {
        this(title, description, UUID.randomUUID().hashCode(), reward, image, completed);
    }

    public Note(@Nullable String title, @Nullable String description,
                int id, int reward, int image, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mReward = reward;
        mImage = image;
        mCompleted = completed;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImage() {
        return mImage;
    }

    public int getReward() {
        return mReward;
    }

    public int getId() {
        return mId;
    }
}
