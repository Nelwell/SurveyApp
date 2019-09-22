package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_YES_VOTES_COUNT = "android.bignerdranch.surveyapp.yes_vote_count";
    public static final String EXTRA_NO_VOTES_COUNT = "android.bignerdranch.surveyapp.no_vote_count";

    // Request codes for continue or reset ResultsActivity buttons
    private static final int RESULTS_ACTIVITY_REQUEST_CODE = 0;
    private static final int CONFIGURE_SURVEY_REQUEST_CODE = 1;

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
    public static int mVoteYesCount = 0;
    public static int mVoteNoCount = 0;
    public static int resetVotes = 0;

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
                resetVotes();
            }
        });

        // Calls ResultsActivity upon button press and passes along intent/extras
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create intent and start ResultsActivity
                Intent showResultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
                showResultsIntent.putExtra(EXTRA_YES_VOTES_COUNT, mVoteYesCount);
                showResultsIntent.putExtra(EXTRA_NO_VOTES_COUNT, mVoteNoCount);
                // Sends intent and requests return of result code
                startActivityForResult(showResultsIntent, RESULTS_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void resetVotes() {
        // Sets vote values back to 0 in TextViews
        mYesCount.setText(String.valueOf(resetVotes));
        mNoCount.setText(String.valueOf(resetVotes));
        mVoteYesCount = 0; // Set vote counts back to 0
        mVoteNoCount = 0;
    }

    // Method to save instance variable data in Bundle
    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putInt(KEY_INDEX_YES, mVoteYesCount);
        outBundle.putInt(KEY_INDEX_NO, mVoteNoCount);
    }

    // Adds single vote per press to corresponding yes or no vote count
    private void addVote() {

        if (mYesButton.isPressed()) {
            mVoteYesCount++; // +1 to vote count
            mYesCount.setText(String.valueOf(mVoteYesCount)); // Sets value to textView
        }
        else if (mNoButton.isPressed()) {
            mVoteNoCount++;
            mNoCount.setText(String.valueOf(mVoteNoCount));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Checks received request code and result code
        if (requestCode == RESULTS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            boolean tappedReset = data.getBooleanExtra(ResultsActivity.EXTRA_RESULT_BUTTON_PRESSED, false);

            // Determines if user reset votes or will continue survey
            if (tappedReset) {
                resetVotes();
                Toast.makeText(this, "Survey votes have been reset to 0", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Continue-eth thou survey-eth", Toast.LENGTH_LONG).show();
            }
        }

        // If back button is pressed
        if (requestCode == RESULTS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You pressed the back button", Toast.LENGTH_LONG).show();
        }

        // Checks received request code and result code
//        if (requestCode == CONFIGURE_SURVEY_REQUEST_CODE && resultCode == RESULT_OK) {
//            boolean buttonTapped = data.getBooleanExtra(ReviewActivity.EXTRA_TAPPED_BUTTON, true);
//
//            if (buttonTapped) {
//                Toast.makeText(this, "Thank you", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, ":(....", Toast.LENGTH_LONG).show();
//            }
//        }

    }
}

