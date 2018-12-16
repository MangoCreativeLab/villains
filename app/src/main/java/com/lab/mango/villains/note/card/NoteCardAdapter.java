package com.lab.mango.villains.note.card;

import androidx.cardview.widget.CardView;

public interface NoteCardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}