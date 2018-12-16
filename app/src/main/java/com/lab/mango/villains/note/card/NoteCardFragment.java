package com.lab.mango.villains.note.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.note.NoteFragment;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class NoteCardFragment extends Fragment {

    private CardView mCardView;

    private TextView mTitleTextView;

    private TextView mBodyTextView;

    private Button mButton;

    private int mPage;

    private NoteFragment.NoteDetailItemListener mNoteDetailItemListener;

    public static NoteCardFragment newInstance(int page) {
        NoteCardFragment fragment = new NoteCardFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);

        fragment.setArguments(args);

        return fragment;
    }

    void setListener(NoteFragment.NoteDetailItemListener noteDetailItemListener) {
        mNoteDetailItemListener = noteDetailItemListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_detail_card_fragment_adapter, container, false);
        mCardView = view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * NoteCardAdapter.MAX_ELEVATION_FACTOR);

        mTitleTextView = mCardView.findViewById(R.id.title);
        mBodyTextView = mCardView.findViewById(R.id.body);
        mButton = mCardView.findViewById(R.id.button);

        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }

    void replaceData(final NoteDetail noteDetail) {

        if (getView() == null) {
            return;
        }

        mTitleTextView.setText(noteDetail.getTitle());
        mBodyTextView.setText(noteDetail.getDescription());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNoteDetailItemListener != null) {
                    mNoteDetailItemListener.onNoteDetailClick(noteDetail);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mNoteDetailItemListener.onUpdateData();
    }
}