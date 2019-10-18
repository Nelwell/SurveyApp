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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureSurveyFragment extends Fragment {

    private String mQuestion;
    private String mAnswerOne;
    private String mAnswerTwo;

    interface NewSurveyListener {
        void saveEdits(String mQuestion, String mAnswerOne, String mAnswerTwo);
    }

    private NewSurveyListener mNewSurveyListener;

    public ConfigureSurveyFragment() {
        // Required empty public constructor
    }

    public static ConfigureSurveyFragment newInstance() {
        return new ConfigureSurveyFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof NewSurveyListener){    // Context is the hosting Activity.
            mNewSurveyListener = (NewSurveyListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement NewSurveyListener");
        }
}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configure_survey, container, false);

        // Get Button resource ID and EditText String resource IDs
        Button mSaveEditsButton = view.findViewById(R.id.save_edits_button);
        final EditText newQuestion = view.findViewById(R.id.survey_question_edit_text);
        final EditText newAnswerOne = view.findViewById(R.id.answer_one);
        final EditText newAnswerTwo = view.findViewById(R.id.answer_two);

//        mEditSurveyListener.editSurvey();

        mSaveEditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SurveyData newSurvey = new SurveyData(question, answerOne, answerTwo);
                mQuestion = newQuestion.getText().toString();
                mAnswerOne = newAnswerOne.getText().toString();
                mAnswerTwo = newAnswerTwo.getText().toString();

                Log.d(TAG, "Save edits clicked... " + mQuestion + " " + mAnswerOne + " " + mAnswerTwo);

                // Call listener's saveEdits method to notify it that new data has been created
                mNewSurveyListener.saveEdits(mQuestion, mAnswerOne, mAnswerTwo);

                // Clear fields
                newQuestion.getText().clear();
                newAnswerOne.getText().clear();
                newAnswerTwo.getText().clear();
            }
        });

        return view;
    }

}
