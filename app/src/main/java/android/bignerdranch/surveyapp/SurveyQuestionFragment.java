package android.bignerdranch.surveyapp;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyQuestionFragment extends Fragment {

    private int mAnswerOneCount = 0;
    private int mAnswerTwoCount = 0;

    private static final String ARG_NEW_QUESTION = "arg_question";
    private static final String ARG_ANSWER_ONE = "arg_answer_one";
    private static final String ARG_ANSWER_TWO = "arg_answer_two";

    interface ResultsListener {
        void surveyResults(int mAnswerOneCount, int mAnswerTwoCount);

    }
    private ResultsListener mResultsListener;

    interface EditSurveyButtonListener {
        void editSurveyButtonPressed();

    }
    private EditSurveyButtonListener mEditSurveyButtonListener;

//    interface SetEditsListener {
//        void setEditsButtonPressed(String mQuestion, String mAnswerOne, String mAnswerTwo);
//
//    }
//
//    private SetEditsListener mSetEditsListener;


    public SurveyQuestionFragment() {
        // Required empty public constructor
    }

//    public static SurveyQuestionFragment newInstance() {
//        return new SurveyQuestionFragment();
//    }

    public static SurveyQuestionFragment newInstance(String mQuestion, String mAnswerOne, String mAnswerTwo) {
        SurveyQuestionFragment fragment = new SurveyQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NEW_QUESTION, mQuestion);
        args.putString(ARG_ANSWER_ONE, mAnswerOne);
        args.putString(ARG_ANSWER_TWO, mAnswerTwo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mQuestion = getArguments().getString(ARG_NEW_QUESTION);
            String mAnswerOne = getArguments().getString(ARG_ANSWER_ONE);
            String mAnswerTwo = getArguments().getString(ARG_ANSWER_TWO);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        // Verifies it's a listener
        if (context instanceof ResultsListener){  // Context is the hosting Activity.
            mResultsListener = (ResultsListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement ResultsListener");
        }

        if (context instanceof EditSurveyButtonListener){  // Context is the hosting Activity.
            mEditSurveyButtonListener = (EditSurveyButtonListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement EditSurveyButtonListener");
        }

//        if (context instanceof SetEditsListener){  // Context is the hosting Activity.
//            mSetEditsListener = (SetEditsListener) context;
//            Log.d(TAG, "Listener set");
//        } else  {
//            throw new RuntimeException(context.toString() + " must implement SetEditsListener");
//        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_survey_question, container, false);

//        if (getArguments() !=null && getArguments().getString(ARG_NEW_QUESTION) != null) {
//
//            final String mQuestion = getArguments().getString(ARG_NEW_QUESTION);
//            final String mAnswerOne = getArguments().getString(ARG_ANSWER_ONE);
//            Log.d(TAG, "onCreateView received the following item: " + mQuestion);

        final TextView mSurveyQuestion = view.findViewById(R.id.survey_question);
//        mSurveyQuestion.setText("");

        // Get button IDs and TextView String resource ID
        final Button mAnswerOneButton = view.findViewById(R.id.answer_one_button);
//        mAnswerOneButton.setText("");

        final Button mAnswerTwoButton = view.findViewById(R.id.answer_two_button);
//        mAnswerTwoButton.setText(""+mAnswerTwo);

        Button mEditSurveyButton = view.findViewById(R.id.edit_survey_button);

//        Button mSetSurveyEditsButton = view.findViewById(R.id.get_survey_edits);

//        mSetSurveyEditsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Inflate the layout for this fragment
//                view = inflater.inflate(R.layout.fragment_survey_question, container, false);
//
//                mQuestion = mSurveyQuestion.getText().toString();
//                mAnswerOne = mAnswerOneButton.getText().toString();
//                mAnswerTwo = mAnswerTwoButton.getText().toString();
//
////                mSurveyQuestion.setText(""+mQuestion);
////                mAnswerOneButton.setText(""+mAnswerOne);
////                mAnswerTwoButton.setText(""+mAnswerTwo);
////
////                TextView newQuestion = view.findViewById(R.id.survey_question);
////                newQuestion.setText(""+mQuestion);
////
////                TextView newAnswerOne = view.findViewById(R.id.answer_one_button);
////                newAnswerOne.setText(""+mQuestion);
////
////                TextView newAnswerTwo = view.findViewById(R.id.answer_two_button);
////                newAnswerTwo.setText(""+mQuestion);
//
//                mSetEditsListener.setEditsButtonPressed(mQuestion, mAnswerOne, mAnswerTwo);
//            }
//        });

        mAnswerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SURVEY", "First answer " + mAnswerOneCount);
                mAnswerOneCount++; // Adds one vote per click
                mResultsListener.surveyResults(mAnswerOneCount, mAnswerTwoCount);
            }
        });

        mAnswerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SURVEY", "Second answer " + mAnswerTwoCount);
                mAnswerTwoCount++; // Adds one vote per click
                mResultsListener.surveyResults(mAnswerOneCount, mAnswerTwoCount);
            }
        });

        mEditSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//            String question = newQuestionText.getText().toString();
//            String yesAnswer = newYesAnswerText.getText().toString();
//            String noAnswer = newNoAnswerText.getText().toString();
//
//            EditSurveyButtonPressed isPressed = new EditSurveyButtonPressed(buttonPressed);

            // Call listener's editSurveyButtonPressed method to notify it that a EditSurveyButton was pressed
                mEditSurveyButtonListener.editSurveyButtonPressed();
            }
        });

//        } else {
//            Log.w(TAG, "Did not receive a ToDoItem");
//        }

        return view;
    }

}
