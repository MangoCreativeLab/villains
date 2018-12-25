package com.lab.mango.villains.notedetail.answer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;

import com.lab.mango.villains.R;
import com.lab.mango.villains.utils.Const;

import androidx.fragment.app.DialogFragment;

public class AnswerDialogFragment extends DialogFragment {

    private AnswerDialogFragment mThis;

    private int mQuestionType;

    public static AnswerDialogFragment newInstance(int questionType) {
        AnswerDialogFragment fragment = new AnswerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("questionType", questionType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog);

        Bundle bundle = getArguments();
        mQuestionType = bundle.getInt("questionType");
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mThis = this;

        // TODO : need to refactoring by using Factory or Builder
        View view;
        if (mQuestionType == Const.QUESTION_TYPE_EDITTEXT) {
            view = inflater.inflate(R.layout.note_detail_answer_edittext, container, false);
        } else if (mQuestionType == Const.QUESTION_TYPE_NUMBER_PICKER) {
            view = inflater.inflate(R.layout.note_detail_answer_number_picker, container, false);
            NumberPicker numberPicker1 = view.findViewById(R.id.number1);
            numberPicker1.setMinValue(0);
            numberPicker1.setValue(3);
            numberPicker1.setMaxValue(12);

            NumberPicker numberPicker2 = view.findViewById(R.id.number2);
            numberPicker2.setMinValue(0);
            numberPicker2.setValue(6);
            numberPicker2.setMaxValue(12);

            NumberPicker numberPicker3 = view.findViewById(R.id.number3);
            numberPicker3.setMinValue(0);
            numberPicker3.setValue(9);
            numberPicker3.setMaxValue(12);
        } else {
            view = inflater.inflate(R.layout.note_detail_answer_pattern, container, false);
        }

        Button summitButton = view.findViewById(R.id.summit);
        summitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", "test");
                mThis.getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                mThis.dismiss();
            }
        });

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
