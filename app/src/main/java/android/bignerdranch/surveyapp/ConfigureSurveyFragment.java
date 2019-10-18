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

//    interface EditSurveyListener {
//        void editSurvey();
//    }
//
//    private EditSurveyListener mEditSurveyListener;

    interface NewSurveyListener {
        void saveEdits(String mQuestion, String mAnswerOne, String mAnswerTwo);
    }

    private NewSurveyListener mNewSurveyListener;

    private static final String ARG_NEW_QUESTION = "arg_question";
    private static final String ARG_ANSWER_ONE = "arg_answer_one";
    private static final String ARG_ANSWER_TWO = "arg_answer_two";

    public ConfigureSurveyFragment() {
        // Required empty public constructor
    }

    public static ConfigureSurveyFragment newInstance() {
        return new ConfigureSurveyFragment();
    }

//    public static ConfigureSurveyFragment newInstance(String mQuestion, String mAnswerOne, String mAnswerTwo) {
//        ConfigureSurveyFragment fragment = new ConfigureSurveyFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_NEW_QUESTION, mQuestion);
//        args.putString(ARG_ANSWER_ONE, mAnswerOne);
//        args.putString(ARG_ANSWER_TWO, mAnswerTwo);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mQuestion = getArguments().getString(ARG_NEW_QUESTION);
//            mAnswerOne = getArguments().getString(ARG_ANSWER_ONE);
//            mAnswerTwo = getArguments().getString(ARG_ANSWER_TWO);
//        }
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

//        if (context instanceof EditSurveyListener){    // Context is the hosting Activity.
//            mEditSurveyListener = (EditSurveyListener) context;
//            Log.d(TAG, "Listener set");
//        } else  {
//            throw new RuntimeException(context.toString() + " must implement EditSurveyListener");
//        }

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

//                newQuestion.getText().clear();
//                newAnswerOne.getText().clear();
//                newAnswerTwo.getText().clear();

                // Call listener's saveEdits method to notify it that new data has been created
                mNewSurveyListener.saveEdits(mQuestion, mAnswerOne, mAnswerTwo);
            }
        });

        return view;
    }

}
