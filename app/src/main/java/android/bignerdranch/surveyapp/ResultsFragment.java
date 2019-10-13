package android.bignerdranch.surveyapp;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {

    interface ResetListener {
        void resetSurvey(int yes, int no);
    }

    private ResetListener mResetListener;

    private static final String ARG_YES_COUNT = "arg_yes";
    private static final String ARG_NO_COUNT = "arg_no";

    private int mAnswerOneCount;
    private int mAnswerTwoCount;

    public ResultsFragment() {
        // Required empty public constructor
    }

    public static ResultsFragment newInstance(int yes, int no) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YES_COUNT, yes);
        args.putInt(ARG_NO_COUNT, no);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAnswerOneCount = getArguments().getInt(ARG_YES_COUNT);
            mAnswerTwoCount = getArguments().getInt(ARG_NO_COUNT);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof ResultsFragment.ResetListener){  // Context is the hosting Activity.
            mResetListener = (ResultsFragment.ResetListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement ResetListener");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        final TextView answerOneView = view.findViewById(R.id.yes_vote_count);
        answerOneView.setText(""+mAnswerOneCount);

        final TextView answerTwoView = view.findViewById(R.id.no_vote_count);
        answerTwoView.setText(""+mAnswerTwoCount);

        Button resetSurvey = view.findViewById(R.id.reset_survey_button);
        resetSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reset counts to 0
                mAnswerOneCount = 0;
                mAnswerTwoCount = 0;

                // Inflate the layout and reset counts
                view = inflater.inflate(R.layout.fragment_results, container, false);
                TextView answerOneView = view.findViewById(R.id.yes_vote_count);
                answerOneView.setText(""+mAnswerOneCount);

                TextView answerTwoView = view.findViewById(R.id.no_vote_count);
                answerTwoView.setText(""+mAnswerTwoCount);
                mResetListener.resetSurvey(mAnswerOneCount, mAnswerTwoCount);
            }
        });

        return view;
    }

}
