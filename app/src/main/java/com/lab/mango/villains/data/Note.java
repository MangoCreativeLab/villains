package com.lab.mango.villains.data;

import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {

    @PrimaryKey
    @ColumnInfo(name = "entryid")
    private final int mId;

    @ColumnInfo(name = "title")
    private final String mTitle;

    @ColumnInfo(name = "description")
    private final String mDescription;

    @ColumnInfo(name = "reward")
    private final int mReward;

    @ColumnInfo(name = "image")
    private final int mImage;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @Ignore
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

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getReward() {
        return mReward;
    }

    public int getImage() {
        return mImage;
    }

    public boolean getCompleted() {
        return mCompleted;
    }

}
