package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    // Constant key string ofr Result Activity
    public static final String EXTRA_RESULT_BUTTON_PRESSED = "com.android.bignerdranch.surveyapp.result_button_pressed";

    // Member instance variables
    private TextView mYesVoteCount;
    private TextView mNoVoteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Stores values from extra in member variables
        int yesVotes = getIntent().getIntExtra(MainActivity.EXTRA_YES_VOTES_COUNT, 0);
        int noVotes = getIntent().getIntExtra(MainActivity.EXTRA_NO_VOTES_COUNT, 0);

        // Get references to vote TextView string resources
        mYesVoteCount = findViewById(R.id.yes_vote_count);
        mNoVoteCount = findViewById(R.id.no_vote_count);

        // Sets passed vote counts from MainActivity
        mYesVoteCount.setText(String.valueOf(yesVotes));
        mNoVoteCount.setText(String.valueOf(noVotes));

        // Gets reference to Reset Button string resource
        Button mResetButton = findViewById(R.id.reset_survey_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent carries data back to Activity that launches this one
                Intent returnIntent = new Intent();
                // Add the value true as an extra
                returnIntent.putExtra(EXTRA_RESULT_BUTTON_PRESSED, true);
                // Everything went well
                setResult(RESULT_OK, returnIntent);
                // End this Activity. Android will remove it from the screen, then create and show
                // the previous Activity - MainActivity in this case
                finish();

            }
        });

        // Gets reference to Continue Survey Button string resource
        Button mContinueButton = findViewById(R.id.continue_survey_button);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent carries data back to Activity that launches this one
                Intent returnIntent = new Intent();
                // Add the value true as an extra
                returnIntent.putExtra(EXTRA_RESULT_BUTTON_PRESSED, false);
                // Everything went well
                setResult(RESULT_OK, returnIntent);
                // End this Activity. Android will remove it from the screen, then create and show
                // the previous Activity - MainActivity in this case
                finish();
            }
        });
    }
}
