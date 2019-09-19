package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Keys strings for finding saved instance data
    private static final String KEY_INDEX_YES = "yes vote";
    private static final String KEY_INDEX_NO = "no vote";

    // Initialized widgets to be inflated
    Button mYesButton;
    Button mNoButton;
    TextView mSurveyQuestion;
    TextView mYesCount;
    TextView mNoCount;
    Button mResetVotes;

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
        mSurveyQuestion = findViewById(R.id.survey_question);
        mYesCount = findViewById(R.id.yes_count);
        mNoCount = findViewById(R.id.no_count);
        mResetVotes = findViewById(R.id.reset_button);

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

        mResetVotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYesCount.setText(String.valueOf(resetVotes));
                mNoCount.setText(String.valueOf(resetVotes));
                mVoteYesCount = 0; // sets counts back to 0
                mVoteNoCount = 0;
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

