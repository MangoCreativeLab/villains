package com.lab.mango.villains.notedetail.viewpager;

import android.view.ViewGroup;

import com.lab.mango.villains.notedetail.NoteDetailFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NoteDetailPagerAdapter extends FragmentStatePagerAdapter {

    private List<NoteDetailFragment> mFragments;

    public NoteDetailPagerAdapter(FragmentManager fm, List<NoteDetailFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (NoteDetailFragment) fragment);
        return fragment;
    }
}
