package android.bignerdranch.surveyapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureSurveyFragment extends Fragment {


    public ConfigureSurveyFragment() {
        // Required empty public constructor
    }

    private Button mSaveEditsButton;
    private TextView mSurveyQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configure_survey, container, false);

        // Get the To Do item from the arguments passed in when this Fragment was created.
        // Call setTodoItem to set data in view components

//        textText = view.findViewById(R.id.to_do_detail_text_textview);
////        dateText = view.findViewById(R.id.to_do_detail_date_created_textview);
////        urgentCheck = view.findViewById(R.id.to_do_detail_urgent_checkbox);
////        doneButton = view.findViewById(R.id.to_do_detail_done_button);
////
////
////        return view;
    }

}
