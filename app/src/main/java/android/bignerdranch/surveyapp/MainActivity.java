package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_YES_VOTES_COUNT = "android.bignerdranch.surveyapp.yes_vote_count";
    public static final String EXTRA_NO_VOTES_COUNT = "android.bignerdranch.surveyapp.no_vote_count";

    // Keys strings for finding saved instance data
    private static final String KEY_INDEX_YES = "yes vote";
    private static final String KEY_INDEX_NO = "no vote";

    // Initialized widgets to be inflated
    Button mYesButton;
    Button mNoButton;
    Button mResetVotesButton;
    Button mResultsButton;
    TextView mSurveyQuestion;
    TextView mYesCount;
    TextView mNoCount;

    // Initialize variables for counting/resetting votes
    private int mVoteYesCount = 0;
    private int mVoteNoCount = 0;
    private int resetVotes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Calls references to String resources
        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);
        mResultsButton = findViewById(R.id.results_button);
        mSurveyQuestion = findViewById(R.id.survey_question);
        mYesCount = findViewById(R.id.yes_count);
        mNoCount = findViewById(R.id.no_count);
        mResetVotesButton = findViewById(R.id.reset_button);

        // Checks for saved instance variable data during rotation
        if (savedInstanceState != null) {
            mVoteYesCount = savedInstanceState.getInt(KEY_INDEX_YES, mVoteYesCount);
            mVoteNoCount = savedInstanceState.getInt(KEY_INDEX_NO, mVoteNoCount);
            mYesCount.setText(String.valueOf(mVoteYesCount));
            mNoCount.setText(String.valueOf(mVoteNoCount));
        }

        // Button listeners
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
            }
        });

        mResetVotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYesCount.setText(String.valueOf(resetVotes));
                mNoCount.setText(String.valueOf(resetVotes));
                mVoteYesCount = 0; // Set counts back to 0
                mVoteNoCount = 0;
            }
        });

        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create intent and start ResultsActivity
                Intent showResultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
                showResultsIntent.putExtra(EXTRA_YES_VOTES_COUNT, mVoteYesCount);
                showResultsIntent.putExtra(EXTRA_NO_VOTES_COUNT, mVoteNoCount);
                startActivity(showResultsIntent);
            }
        });
    }

    // Method to save instance variable data in Bundle
    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putInt(KEY_INDEX_YES, mVoteYesCount);
        outBundle.putInt(KEY_INDEX_NO, mVoteNoCount);
    }

    // Adds single vote per press to corresponding yes or no button
    private void addVote() {

        if (mYesButton.isPressed()) {
            mVoteYesCount++; // +1 vote
            mYesCount.setText(String.valueOf(mVoteYesCount)); // Sets value to textView
        }
        else if (mNoButton.isPressed()) {
            mVoteNoCount++;
            mNoCount.setText(String.valueOf(mVoteNoCount));
        }
    }
}

