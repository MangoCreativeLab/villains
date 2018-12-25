package com.lab.mango.villains.notedetail;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

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

    public static int REQUEST_CODE = 0;

    private Fragment mThis;

    private NoteDetailContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private NoteDetailRecyclerViewAdapter mAdapter;

    private ViewGroup mRootView;

    private TextView mDescription;

    private TextView mHint;

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
        mThis = this;

        View view = inflater.inflate(R.layout.note_detail_fragment, container, false);
        mRootView = view.findViewById(R.id.root_view);

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
            public void onAnswerButtonClick(int questionType) {
                AnswerDialogFragment fragment = AnswerDialogFragment.newInstance(questionType);
                fragment.setTargetFragment(mThis, REQUEST_CODE);
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().add(fragment, "AnswerDialogFragment").commit();
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        mDescription = view.findViewById(R.id.note_detail_description);
        mHint = view.findViewById(R.id.note_detail_hint);

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

        mDescription.setText(noteDetail.getDescription());
        mHint.setText(noteDetail.getHint());

        if (getUserVisibleHint()) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(noteDetail.getTitle());
        }

        mAdapter.setQuestionType(noteDetail.getQuestionType());
        mAdapter.setYoutubeUrl(noteDetail.getYoutubeUrl());

        final boolean isComplete = noteDetail.getCompleted();
        mRootView.setBackgroundColor(getResources().getColor(
                isComplete ? R.color.complete_background : R.color.incomplete_background));
    }

    @Override
    public void showSolveQuestion() {
        mRootView.setBackgroundColor(getResources().getColor(R.color.complete_background));
        Toast.makeText(getContext(), "정답을 맞추었습니다!!", Toast.LENGTH_SHORT).show();
        mPresenter.start();
    }

    @Override
    public void showNotSolveQuestion() {
        mRootView.setBackgroundColor(getResources().getColor(R.color.incomplete_background));
        Toast.makeText(getContext(), "정답을 못맞추었습니다!!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getTargetRequestCode()) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("result");
                mPresenter.updateAnswer(result);
            }
        }
    }
}
