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

    private int mAnswerOneCount;
    private int mAnswerTwoCount;

    interface ResetResultsListener {
        void resetResults(int mAnswerOneCount, int mAnswerTwoCount);
    }

    private ResetResultsListener mResetResultsListener;

    private static final String ARG_ANSWER_ONE_COUNT = "arg_answer_one";
    private static final String ARG_ANSWER_TWO_COUNT = "arg_answer_two";


    public ResultsFragment() {
        // Required empty public constructor
    }

    public static ResultsFragment newInstance(int answerOneCount, int answerTwoCount) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ANSWER_ONE_COUNT, answerOneCount);
        args.putInt(ARG_ANSWER_TWO_COUNT, answerTwoCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAnswerOneCount = getArguments().getInt(ARG_ANSWER_ONE_COUNT);
            mAnswerTwoCount = getArguments().getInt(ARG_ANSWER_TWO_COUNT);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if (context instanceof ResultsFragment.ResetResultsListener){  // Context is the hosting Activity.
            mResetResultsListener = (ResultsFragment.ResetResultsListener) context;
            Log.d(TAG, "Listener set");
        } else  {
            throw new RuntimeException(context.toString() + " must implement mResetResultsListener");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and set new vote count value to TextViews
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        final TextView mAnswerOne = view.findViewById(R.id.yes_vote_count);
        mAnswerOne.setText(String.valueOf(mAnswerOneCount));

        final TextView mAnswerTwo = view.findViewById(R.id.no_vote_count);
        mAnswerTwo.setText(String.valueOf(mAnswerTwoCount));

        Button mResetSurvey = view.findViewById(R.id.reset_survey_button);
        mResetSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reset counts to 0
                mAnswerOneCount = 0;
                mAnswerTwoCount = 0;

                // Inflate the layout and reset counts
                view = inflater.inflate(R.layout.fragment_results, container, false);

                TextView answerOneView = view.findViewById(R.id.yes_vote_count);
                answerOneView.setText(String.valueOf(mAnswerOneCount));

                TextView answerTwoView = view.findViewById(R.id.no_vote_count);
                answerTwoView.setText(String.valueOf(mAnswerTwoCount));
                mResetResultsListener.resetResults(mAnswerOneCount, mAnswerTwoCount);
            }
        });

        return view;
    }

}
