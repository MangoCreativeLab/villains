package com.lab.mango.villains.notedetail.youtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.mango.villains.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

public class NoteDetailRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private Lifecycle mLifecycle;
    private ArrayList<String> mYoutubeUrl;
    private NoteDetailRecyclerViewAdapter.ItemListener mItemListener;

    private boolean mIsPause = false;

    public NoteDetailRecyclerViewAdapter(Context context, Lifecycle lifecycle, ArrayList<String> youtubeUrl, NoteDetailRecyclerViewAdapter.ItemListener listener) {
        mContext = context;
        mLifecycle = lifecycle;
        mYoutubeUrl = youtubeUrl;
        mItemListener = listener;
    }

    @Override
    public NoteDetailRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_detail_recyclerview_item, parent, false);
        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        mLifecycle.addObserver(youTubePlayerView);

        YouTubePlayerFullScreenListener youTubePlayerFullScreenListener = new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                mItemListener.onYouTubePlayerEnterFullScreen();
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                mItemListener.onYouTubePlayerExitFullScreen();
            }
        };

        CustomPlayerUIController.AnswerButtonClickListener answerButtonClickListener = new CustomPlayerUIController.AnswerButtonClickListener() {
            @Override
            public void onAnswerButtonClick() {
                mItemListener.onAnswerButtonClick();
            }
        };

        NoteDetailRecyclerViewAdapter.ViewHolder vh
                = new NoteDetailRecyclerViewAdapter.ViewHolder(view, youTubePlayerFullScreenListener, answerButtonClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final NoteDetailRecyclerViewAdapter.ViewHolder viewHolder = (NoteDetailRecyclerViewAdapter.ViewHolder) holder;

        if (mIsPause) {
            viewHolder.mYouTubePlayerView.post(new Runnable() {
                @Override
                public void run() {
                    if (viewHolder.mYouTubePlayer != null && viewHolder.mState != PlayerConstants.PlayerState.PLAYING) {
                        viewHolder.mYouTubePlayer.pause();
                    }
                }
            });
        }

        if (viewHolder.mState != PlayerConstants.PlayerState.VIDEO_CUED
                && viewHolder.mState != PlayerConstants.PlayerState.PAUSED) {
            viewHolder.cueVideo(mYoutubeUrl.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mYoutubeUrl.size();
    }

    public void setYoutubeUrl(List<String> youtubeUrl) {
        mYoutubeUrl.clear();
        mYoutubeUrl.addAll(youtubeUrl);
        notifyDataSetChanged();
    }

    public void pausePlayer() {
        mIsPause = true;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PlayerConstants.PlayerState mState;
        private YouTubePlayerView mYouTubePlayerView;
        private YouTubePlayer mYouTubePlayer;
        private String mCurrentVideoId;
        private CustomPlayerUIController mCustomPlayerUIController;

        ViewHolder(final View v,
                   YouTubePlayerFullScreenListener youTubePlayerFullScreenListener,
                   CustomPlayerUIController.AnswerButtonClickListener answerButtonClickListener) {
            super(v);

            mYouTubePlayerView = v.findViewById(R.id.youtube_player_view);
            mYouTubePlayerView.addFullScreenListener(youTubePlayerFullScreenListener);

            mYouTubePlayerView.inflateCustomPlayerUI(R.layout.note_detail_youtube_player_ui);
            mCustomPlayerUIController = new CustomPlayerUIController(mYouTubePlayerView, answerButtonClickListener);

            // https://developers.google.com/youtube/player_parameters#Parameters
            IFramePlayerOptions framePlayerOptions = new IFramePlayerOptions.Builder()
                    .controls(0)
                    .modestBranding(1)
                    .rel(0)
                    .showInfo(1)
                    .build();

            mYouTubePlayerView.initialize(new YouTubePlayerInitListener() {
                @Override
                public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                    initializedYouTubePlayer.addListener(mCustomPlayerUIController);
                    initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            mYouTubePlayer = initializedYouTubePlayer;
                            mCustomPlayerUIController.setYouTubePlayer(initializedYouTubePlayer);
                            mYouTubePlayer.cueVideo(mCurrentVideoId, 0);
                        }

                        @Override
                        public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
                            super.onStateChange(state);
                            mState = state;
                        }
                    });
                }
            }, true, framePlayerOptions);
        }

        void cueVideo(String videoId) {
            mCurrentVideoId = videoId;
            if (mYouTubePlayer == null) {
                return;
            }

            mYouTubePlayer.cueVideo(videoId, 0);
        }
    }

    public interface ItemListener {
        void onYouTubePlayerEnterFullScreen();

        void onYouTubePlayerExitFullScreen();

        void onAnswerButtonClick();
    }
}
