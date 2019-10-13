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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyQuestionFragment extends Fragment {

    private int mAnswerOneCount = 0;
    private int mAnswerTwoCount = 0;

    interface ResultsListener {
        void surveyResults(int yes, int no);
    }

    interface EditSurveyButtonListener {
        void editSurveyButtonPressed();
    }

    private EditSurveyButtonListener mEditSurveyButtonListener;

    private ResultsListener mResultsListener;

    public SurveyQuestionFragment() {
        // Required empty public constructor
    }

    public static SurveyQuestionFragment newInstance() {
        return new SurveyQuestionFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof ResultsListener){  // Context is the hosting Activity.
            mResultsListener = (ResultsListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement ResultsListener");
        }

        // Verifies it's a listener
        if (context instanceof EditSurveyButtonListener){  // Context is the hosting Activity.
            mEditSurveyButtonListener = (EditSurveyButtonListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement EditSurveyButtonListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_survey_question, container, false);

        // Get button IDs
        Button mAnswerOneButton = view.findViewById(R.id.answer_one_button);
        Button mAnswerTwoButton = view.findViewById(R.id.answer_two_button);
        Button mEditSurveyButton = view.findViewById(R.id.edit_survey_button);

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

            // Call listener's newItemCreated method to notify it that a newItem was created
            mEditSurveyButtonListener.editSurveyButtonPressed();
            }
        });

        return view;
    }
}
