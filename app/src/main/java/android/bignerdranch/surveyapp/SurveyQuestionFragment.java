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

        public interface EditSurveyButtonListener {
        void editSurveyButtonPressed();
    }

    private EditSurveyButtonListener mEditSurveyButtonListener;

//    public SurveyQuestionFragment() {
//        // Required empty public constructor
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof EditSurveyButtonListener){    // Context is the hosting Activity.
            mEditSurveyButtonListener = (EditSurveyButtonListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement NewItemCreatedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_survey_question, container, false);


        Button mEditSurveyButton = view.findViewById(R.id.edit_survey_button);

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
