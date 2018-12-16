package com.lab.mango.villains.note;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.note.card.NoteCardFragmentPagerAdapter;
import com.lab.mango.villains.note.card.ShadowTransformer;
import com.lab.mango.villains.notedetail.NoteDetailActivity;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class NoteFragment extends Fragment implements NoteContract.View {

    private NoteContract.Presenter mPresenter;

    private NoteCardFragmentPagerAdapter mPagerAdapter;

    private TickerView mTickerView;
    private NoteDetailItemListener mNoteDetailItemListener = new NoteDetailItemListener() {
        @Override
        public void onNoteDetailClick(NoteDetail clickedDetail) {

            mPresenter.loadNoteDetails(clickedDetail);
        }

        @Override
        public void onUpdateData() {
            mPresenter.start();
        }
    };

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_fragment, container, false);
        ViewPager viewPager = view.findViewById(R.id.view_pager_for_card);

        mPagerAdapter = new NoteCardFragmentPagerAdapter(getActivity().getSupportFragmentManager(), dpToPixels(2, getActivity()), mNoteDetailItemListener);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, mPagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

        mTickerView = view.findViewById(R.id.tickerView);
        mTickerView.setCharacterLists(TickerUtils.provideNumberList());

        return view;
    }

    private void startTickerView(final int reward) {
        if (mTickerView == null) {
            return;
        }

        mTickerView.setText("$000,000,000", false);
        mTickerView.setTypeface(Typeface.create("san-serif-condensed-light", Typeface.NORMAL));
        mTickerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTickerView.setText(getRewardStr(reward));
            }
        }, 500);
    }

    private String getRewardStr(int reward) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumIntegerDigits(100);
        return "$" + nf.format(reward);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showNoteDetails(List<NoteDetail> noteDetails) {
        mPagerAdapter.replaceData(noteDetails);
    }

    @Override
    public void showReward(int reward) {
        startTickerView(reward);
    }

    @Override
    public void startNoteDetail(List<NoteDetail> noteDetails, NoteDetail selectedNoteDetail) {
        int initPosition = 0;
        int[] detailIds = new int[noteDetails.size()];
        for (int i = 0; i < detailIds.length; i++) {
            detailIds[i] = noteDetails.get(i).getId();
            if (selectedNoteDetail.getId() == detailIds[i]) {
                initPosition = i;
            }
        }

        Intent intent = new Intent();
        intent.putExtra(NoteDetailActivity.EXTRA_TITLE, selectedNoteDetail.getTitle());
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_DETAIL_IDs, detailIds);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_DETAIL_INIT_POS, initPosition);

        intent.setClass(getContext(), NoteDetailActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void setPresenter(NoteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public interface NoteDetailItemListener {
        void onNoteDetailClick(NoteDetail clickedDetail);

        void onUpdateData();
    }
}
