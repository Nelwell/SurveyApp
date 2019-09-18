package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "vote";

    Button mYesButton;
    Button mNoButton;
    TextView mSurveyQuestion;
    TextView mYesCount;
    TextView mNoCount;
    Button mResetVotes;

    private int mVoteYesCount = 0;
    private int mVoteNoCount = 0;
    private int resetVotes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYesButton = findViewById(R.id.yes_button);
        mNoButton = findViewById(R.id.no_button);
        mSurveyQuestion = findViewById(R.id.survey_question);
        mYesCount = findViewById(R.id.yes_count);
        mNoCount = findViewById(R.id.no_count);
        mResetVotes = findViewById(R.id.reset_button);

        if (savedInstanceState != null) {
            mVoteYesCount = savedInstanceState.getInt(KEY_INDEX, 0);
            mVoteNoCount = savedInstanceState.getInt(KEY_INDEX, 0);
//            mYesCount.setText(""+mVoteYesCount);
//            mNoCount.setText(""+mVoteNoCount);

        }

        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
//                mYesCount.setText(""+mVoteYesCount);
//                mVoteYesCount++;
            }
        });

        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVote();
//                mNoCount.setText(""+mVoteNoCount);
//                mVoteNoCount++;
            }
        });

        mResetVotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYesCount.setText(""+resetVotes);
                mNoCount.setText(""+resetVotes);
                mVoteYesCount = 0;
                mVoteNoCount = 0;
            }
        });

//        mYesCount.setText(mVoteYesCount);
//        mNoCount.setText((mVoteNoCount));
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt(KEY_INDEX, mVoteYesCount);
//        mYesCount.setText(""+mVoteYesCount);
//    }

    private void addVote() {

        if (mYesButton.isPressed()) {
            mVoteYesCount++;
            mYesCount.setText(""+mVoteYesCount);
        }
        else if (mNoButton.isPressed()) {
            mVoteNoCount++;
            mNoCount.setText(""+mVoteNoCount);

        }
    }
}

