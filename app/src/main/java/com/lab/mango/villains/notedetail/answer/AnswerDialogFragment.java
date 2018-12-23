package com.lab.mango.villains.notedetail.answer;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lab.mango.villains.R;

import androidx.fragment.app.DialogFragment;

public class AnswerDialogFragment extends DialogFragment {

    public static AnswerDialogFragment newInstance() {
        AnswerDialogFragment fragment = new AnswerDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.6f;
        window.setAttributes(params);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_detail_answer_layout, container, false);
        setDialogPosition();
        return view;
    }

    private void setDialogPosition() {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager.LayoutParams params = window.getAttributes();
        window.setAttributes(params);
    }
}
