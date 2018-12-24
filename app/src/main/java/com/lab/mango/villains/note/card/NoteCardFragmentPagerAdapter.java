package com.lab.mango.villains.note.card;

import android.view.ViewGroup;

import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.data.TestNoteDetailData;
import com.lab.mango.villains.note.NoteFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NoteCardFragmentPagerAdapter extends FragmentStatePagerAdapter implements NoteCardAdapter {

    private static int NUM_ITEMS = TestNoteDetailData.NoteDetailCount;

    private NoteFragment.NoteDetailItemListener mNoteDetailItemListener;
    private List<NoteCardFragment> mFragments;
    private float mBaseElevation;

    public NoteCardFragmentPagerAdapter(FragmentManager fm, float baseElevation, NoteFragment.NoteDetailItemListener noteDetailItemListener) {

        super(fm);
        mFragments = new ArrayList<>();
        mBaseElevation = baseElevation;
        mNoteDetailItemListener = noteDetailItemListener;

        for (int i = 0; i < NUM_ITEMS; i++) {
            addCardFragment(i);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (NoteCardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(int position) {
        NoteCardFragment fragment = NoteCardFragment.newInstance(position);
        fragment.setListener(mNoteDetailItemListener);
        mFragments.add(fragment);
    }

    public void replaceData(List<NoteDetail> noteDetails) {
        for (int i = 0; i < mFragments.size(); i++) {
            mFragments.get(i).replaceData(noteDetails.get(i));
        }
    }
}