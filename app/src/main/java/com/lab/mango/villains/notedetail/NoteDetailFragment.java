package com.lab.mango.villains.notedetail;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.lab.mango.villains.R;
import com.lab.mango.villains.data.NoteDetail;
import com.lab.mango.villains.notedetail.answer.AnswerDialogFragment;
import com.lab.mango.villains.notedetail.youtube.CustomPlayerUIController;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    private NoteDetailContract.Presenter mPresenter;

    // https://github.com/PierfrancescoSoffritti/android-youtube-player
    private YouTubePlayerView mYoutubePlayerView;

    private YouTubePlayer mYouTubePlayer;

    private CustomPlayerUIController mCustomPlayerUIController;

    private YouTubePlayerFullScreenListener mYouTubePlayerFullScreenListener;

    private IFramePlayerOptions mFramePlayerOptions;

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

        mYoutubePlayerView.inflateCustomPlayerUI(R.layout.note_detail_youtube_player_ui);
        mCustomPlayerUIController = new CustomPlayerUIController(mYoutubePlayerView, new CustomPlayerUIController.AnswerButtonClickListener() {
            @Override
            public void onAnswerButtonClick() {
                AnswerDialogFragment fragment = AnswerDialogFragment.newInstance();
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().add(fragment, "AnswerDialogFragment").commit();
                }
            }
        });

        mYouTubePlayerFullScreenListener = new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                setFullScreen(true);
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                setFullScreen(false);
            }
        };

        mYoutubePlayerView.addFullScreenListener(mYouTubePlayerFullScreenListener);
        if (isFullScreen()) {
            mYoutubePlayerView.enterFullScreen();
        }

        // https://developers.google.com/youtube/player_parameters#Parameters
        mFramePlayerOptions = new IFramePlayerOptions.Builder()
                .controls(0)
                .modestBranding(1)
                .rel(0)
                .showInfo(0)
                .build();

        ((NoteDetailActivity) getActivity()).setOnBackClickListener(new NoteDetailActivity.OnBackClickListener() {
            @Override
            public boolean onBackClick() {
                if (isFullScreen()) {
                    mYoutubePlayerView.exitFullScreen();
                    return true;
                }
                return false;
            }
        });

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
        } else {
            pausePlayer();
        }
    }

    @Override
    public void onDestroyView() {
        mYoutubePlayerView.removeFullScreenListener(mYouTubePlayerFullScreenListener);
        mYoutubePlayerView.release();
        mYouTubePlayer = null;
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

        if (mYouTubePlayer != null) {
            return;
        }

        mYoutubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {

                initializedYouTubePlayer.addListener(mCustomPlayerUIController);
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        mYouTubePlayer = initializedYouTubePlayer;

                        mCustomPlayerUIController.setYouTubePlayer(initializedYouTubePlayer);
                        String videoId = noteDetail.getYoutubeUrl();
//                        initializedYouTubePlayer.loadVideo(videoId, 0);
                        initializedYouTubePlayer.cueVideo(videoId, 0);
                        pausePlayer();

                    }
                });
            }
        }, true, mFramePlayerOptions);
    }

    private void pausePlayer() {
        if (mYoutubePlayerView == null) {
            return;
        }

        mYoutubePlayerView.post(new Runnable() {
            @Override
            public void run() {
                if (mYouTubePlayer != null) {
                    mYouTubePlayer.pause();
                }
            }
        });
    }

    private void setFullScreen(boolean fullscreen) {
        if (getActivity() == null) {
            return;
        }

        WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
        if (fullscreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        getActivity().getWindow().setAttributes(attrs);
    }

    private boolean isFullScreen() {
        if (getActivity() == null) {
            return false;
        }

        return (getActivity().getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (isFullScreen()) {
            mYoutubePlayerView.enterFullScreen();
        } else {
            mYoutubePlayerView.exitFullScreen();
        }
    }
}
