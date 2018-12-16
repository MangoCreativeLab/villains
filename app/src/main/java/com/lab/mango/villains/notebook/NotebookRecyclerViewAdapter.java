package com.lab.mango.villains.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotebookRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Note> mNotes;
    private NotebookFragment.NoteItemListener mItemListener;

    public NotebookRecyclerViewAdapter(Context context, ArrayList<Note> notes, NotebookFragment.NoteItemListener noteItemListener) {
        mContext = context;
        mNotes = notes;
        mItemListener = noteItemListener;
    }

    @Override
    public NotebookRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.notebook_recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        final Note note = mNotes.get(position);

        final int image = note.getImage();
        viewHolder.imageButton.setBackground(mContext.getDrawable(image));
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onNoteClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void replaceData(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;

        ViewHolder(View v) {
            super(v);
            imageButton = v.findViewById(R.id.imgae_button);
        }
    }
}
