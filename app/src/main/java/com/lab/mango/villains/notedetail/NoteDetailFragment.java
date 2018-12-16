package com.lab.mango.villains.notedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.lab.mango.villains.R;
import com.lab.mango.villains.data.NoteDetail;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    private NoteDetailContract.Presenter mPresenter;

    private YouTubePlayerView mYoutubePlayerView;

    private TextView mHintTextView;

    private PatternLockView mPatternLockView;

    public static NoteDetailFragment newInstance() {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_detail_fragment, container, false);

        mYoutubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(mYoutubePlayerView);

        mHintTextView = view.findViewById(R.id.hint);
        mPatternLockView = view.findViewById(R.id.patternView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroyView() {
        mYoutubePlayerView.release();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(NoteDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNoteDetail(final NoteDetail noteDetail) {
        if (getView() == null) {
            return;
        }

        if (getUserVisibleHint()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(noteDetail.getTitle());
        }


        mHintTextView.setText(noteDetail.getHint());
        mYoutubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = noteDetail.getYoutubeUrl();
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);

        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List progressPattern) {

            }

            @Override
            public void onComplete(List pattern) {

            }

            @Override
            public void onCleared() {

            }
        });
    }
}
