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
public class ConfigureSurveyFragment extends Fragment {

    public interface EditSurveyListener {
        void editSurvey(SurveyData newSurvey);
    }

    private EditSurveyListener mEditSurveyListener;

//    public ConfigureSurveyFragment() {
//        // Required empty public constructor
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof EditSurveyListener){    // Context is the hosting Activity.
            mEditSurveyListener = (EditSurveyListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement NewItemCreatedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configure_survey, container, false);


//        Button mEditSurveyButton = view.findViewById(R.id.edit_survey_button);
        Button mSaveEditsButton = view.findViewById(R.id.save_edits_button);
        final EditText newQuestionText = view.findViewById(R.id.survey_question);
        final EditText newYesAnswerText = view.findViewById(R.id.answer_one);
        final EditText newNoAnswerText = view.findViewById(R.id.answer_two);

        mSaveEditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String question = newQuestionText.getText().toString();
                String yesAnswer = newYesAnswerText.getText().toString();
                String noAnswer = newNoAnswerText.getText().toString();

                SurveyData newSurvey = new SurveyData(question, yesAnswer, noAnswer);

                newQuestionText.getText().clear();
                newYesAnswerText.getText().clear();
                newNoAnswerText.getText().clear();

                // Call listener's newItemCreated method to notify it that a newItem was created
                mEditSurveyListener.editSurvey(newSurvey);
            }
        });
        return view;

    }

}
