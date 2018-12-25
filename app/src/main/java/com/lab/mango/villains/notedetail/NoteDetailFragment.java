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
import com.lab.mango.villains.notedetail.youtube.NoteDetailRecyclerViewAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    private NoteDetailContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private NoteDetailRecyclerViewAdapter mAdapter;

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
        mRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NoteDetailRecyclerViewAdapter(getContext(), this.getLifecycle(), new ArrayList<String>(), new NoteDetailRecyclerViewAdapter.ItemListener() {

            @Override
            public void onYouTubePlayerEnterFullScreen() {
                setFullScreen(true);
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                setFullScreen(false);
            }

            @Override
            public void onAnswerButtonClick() {
                AnswerDialogFragment fragment = AnswerDialogFragment.newInstance();
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().add(fragment, "AnswerDialogFragment").commit();
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);

//        mYoutubePlayerView.addFullScreenListener(mYouTubePlayerFullScreenListener);
//        if (isFullScreen()) {
//            mYoutubePlayerView.enterFullScreen();
//        }
//
//        ((NoteDetailActivity) getActivity()).setOnBackClickListener(new NoteDetailActivity.OnBackClickListener() {
//            @Override
//            public boolean onBackClick() {
//                if (isFullScreen()) {
//                    mYoutubePlayerView.exitFullScreen();
//                    return true;
//                }
//                return false;
//            }
//        });

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
            if (mAdapter != null) {
                mAdapter.pausePlayer();
            }
        }
    }

    @Override
    public void onDestroyView() {
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
        mAdapter.setYoutubeUrl(noteDetail.getYoutubeUrl());
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
//        if (isFullScreen()) {
//            mYoutubePlayerView.enterFullScreen();
//        } else {
//            mYoutubePlayerView.exitFullScreen();
//        }
    }
}
