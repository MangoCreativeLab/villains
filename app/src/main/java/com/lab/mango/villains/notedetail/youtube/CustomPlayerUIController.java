package com.lab.mango.villains.notedetail.youtube;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lab.mango.villains.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.Utils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class CustomPlayerUIController
        implements YouTubePlayerListener, YouTubePlayerFullScreenListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    @NonNull
    private final YouTubePlayerView mYouTubePlayerView;

    @NonNull
    private YouTubePlayer mYouTubePlayer;

    private View mPanel, mControlsRoot;
    private TextView mVideoCurrentTime, mVideoDuration;
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;
    private ImageView mPlayPauseButton, mYouTubeButton, mAnswerButton, mFullScreenButton;

    private boolean mIsPlaying = false;
    private boolean mIsVisible = true;
    private boolean mCanFadeControls = false;

    @NonNull
    private final AnswerButtonClickListener mAnswerButtonClickListener;

    public interface AnswerButtonClickListener {
        void onAnswerButtonClick();
    }

    public CustomPlayerUIController(@NonNull YouTubePlayerView mYouTubePlayerView, @NonNull AnswerButtonClickListener answerButtonClickListener) {
        this.mYouTubePlayerView = mYouTubePlayerView;

        mAnswerButtonClickListener = answerButtonClickListener;
        initViews(mYouTubePlayerView);
    }

    public void setYouTubePlayer(@NonNull YouTubePlayer mYouTubePlayer) {
        this.mYouTubePlayer = mYouTubePlayer;
    }

    private void initViews(View controlsView) {
        mPanel = controlsView.findViewById(R.id.panel);

        mControlsRoot = controlsView.findViewById(R.id.controls_root);

        mVideoCurrentTime = controlsView.findViewById(R.id.video_current_time);
        mVideoDuration = controlsView.findViewById(R.id.video_duration);

        mProgressBar = controlsView.findViewById(R.id.progress);
        mPlayPauseButton = controlsView.findViewById(R.id.play_pause_button);
        mYouTubeButton = controlsView.findViewById(R.id.youtube_button);
        mAnswerButton = controlsView.findViewById(R.id.answer_button);

        mFullScreenButton = controlsView.findViewById(R.id.fullscreen_button);

        mSeekBar = controlsView.findViewById(R.id.seek_bar);

        mSeekBar.setOnSeekBarChangeListener(this);
        mPanel.setOnClickListener(this);
        mPlayPauseButton.setOnClickListener(this);
        mAnswerButton.setOnClickListener(this);
        mFullScreenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mPanel)
            toggleControlsVisibility();
        else if (view == mPlayPauseButton)
            onPlayButtonPressed();
        else if (view == mAnswerButton)
            onAnswerButtonPressed();
        else if (view == mFullScreenButton)
            onFullScreenButtonPressed();
    }

    private void onAnswerButtonPressed() {
        mAnswerButtonClickListener.onAnswerButtonClick();
    }

    private void onFullScreenButtonPressed() {
        mYouTubePlayerView.toggleFullScreen();
    }

    private void onPlayButtonPressed() {
        if (mIsPlaying) {
            mYouTubePlayer.pause();
        } else {
            mYouTubePlayer.play();
        }
    }

    private void updatePlayPauseButtonIcon(boolean playing) {
        final int img = playing ? R.drawable.ic_pause_36dp : R.drawable.ic_play_36dp;
        mPlayPauseButton.setImageResource(img);
    }

    private void toggleControlsVisibility() {
        final float finalAlpha = mIsVisible ? 0f : 1f;
        fadeControls(finalAlpha);
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable mFadeOutRunnable = new Runnable() {
        @Override
        public void run() {
            fadeControls(0f);
        }
    };

    private void fadeControls(final float finalAlpha) {
        if (!mCanFadeControls) {
            return;
        }

        mIsVisible = finalAlpha != 0f;
        if (finalAlpha == 1f && mIsPlaying) {
            startFadeOutViewTimer();
        } else {
            mHandler.removeCallbacks(mFadeOutRunnable);
        }

        mControlsRoot.animate()
                .alpha(finalAlpha)
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if (finalAlpha == 1f)
                            mControlsRoot.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if (finalAlpha == 0f)
                            mControlsRoot.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                }).start();
    }

    private void startFadeOutViewTimer() {
        mHandler.postDelayed(mFadeOutRunnable, 3000);
    }

    @Override
    public void onYouTubePlayerEnterFullScreen() {
        mFullScreenButton.setImageResource(R.drawable.ic_fullscreen_exit_24dp);
    }

    @Override
    public void onYouTubePlayerExitFullScreen() {
        mFullScreenButton.setImageResource(R.drawable.ic_fullscreen_24dp);
    }

    // YouTubePlayer callbacks

    // TODO refactor this method
    @Override
    public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
        mNewSeekBarProgress = -1;

        updateControlsState(state);

        if (state == PlayerConstants.PlayerState.PLAYING || state == PlayerConstants.PlayerState.PAUSED || state == PlayerConstants.PlayerState.VIDEO_CUED) {
            mPanel.setBackgroundColor(ContextCompat.getColor(mYouTubePlayerView.getContext(), android.R.color.transparent));
            mProgressBar.setVisibility(View.GONE);
            mPlayPauseButton.setVisibility(View.VISIBLE);

            mCanFadeControls = true;
            boolean playing = state == PlayerConstants.PlayerState.PLAYING;
            updatePlayPauseButtonIcon(playing);

            if (playing) {
                startFadeOutViewTimer();
            } else {
                mHandler.removeCallbacks(mFadeOutRunnable);
            }
        } else {
            updatePlayPauseButtonIcon(false);
            fadeControls(1f);

            if (state == PlayerConstants.PlayerState.BUFFERING) {
                mPanel.setBackgroundColor(ContextCompat.getColor(mYouTubePlayerView.getContext(), android.R.color.transparent));
                mPlayPauseButton.setVisibility(View.INVISIBLE);

                mCanFadeControls = false;
            }

            if (state == PlayerConstants.PlayerState.UNSTARTED) {
                mCanFadeControls = false;

                mProgressBar.setVisibility(View.GONE);
                mPlayPauseButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private void updateControlsState(PlayerConstants.PlayerState state) {
        switch (state) {
            case ENDED:
                mIsPlaying = false;
                break;
            case PAUSED:
                mIsPlaying = false;
                break;
            case PLAYING:
                mIsPlaying = true;
                break;
            case UNSTARTED:
                resetUI();
                break;
            default:
                break;
        }


        updatePlayPauseButtonIcon(!mIsPlaying);
    }

    @Override
    public void onCurrentSecond(float second) {
        if (mSeekBarTouchStarted) {
            return;
        }
        if (mNewSeekBarProgress > 0 && !Utils.formatTime(second).equals(Utils.formatTime(mNewSeekBarProgress))) {
            return;
        }

        mNewSeekBarProgress = -1;
        mSeekBar.setProgress((int) second);
    }

    @Override
    public void onVideoDuration(float duration) {
        mVideoDuration.setText(Utils.formatTime(duration));
        mSeekBar.setMax((int) duration);
    }

    @Override
    public void onVideoLoadedFraction(float loadedFraction) {
        mSeekBar.setSecondaryProgress((int) (loadedFraction * mSeekBar.getMax()));
    }

    @Override
    public void onVideoId(@NonNull final String videoId) {
        mYouTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId + "#t=" + mSeekBar.getProgress()));
                mControlsRoot.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onReady() {
    }

    @Override
    public void onPlaybackQualityChange(@NonNull PlayerConstants.PlaybackQuality playbackQuality) {
    }

    @Override
    public void onPlaybackRateChange(@NonNull PlayerConstants.PlaybackRate rate) {
    }

    @Override
    public void onError(@NonNull PlayerConstants.PlayerError error) {
    }

    @Override
    public void onApiChange() {
    }

    private boolean mSeekBarTouchStarted = false;
    private int mNewSeekBarProgress = -1;

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mVideoCurrentTime.setText(Utils.formatTime(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mSeekBarTouchStarted = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mIsPlaying)
            mNewSeekBarProgress = seekBar.getProgress();

        mYouTubePlayer.seekTo(seekBar.getProgress());
        mSeekBarTouchStarted = false;
    }

    private void resetUI() {
        mSeekBar.setProgress(0);
        mSeekBar.setMax(0);
        mVideoDuration.post(new Runnable() {
            @Override
            public void run() {
                mVideoDuration.setText("");
            }
        });
    }
}
