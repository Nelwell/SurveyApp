package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

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

    }
}
