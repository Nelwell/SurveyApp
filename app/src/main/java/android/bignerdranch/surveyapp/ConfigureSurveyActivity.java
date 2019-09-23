package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigureSurveyActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_SURVEY_QUESTION = "android.bignerdranch.surveyapp.new_survey_question";
    public static final String EXTRA_ANSWER_ONE = "android.bignerdranch.surveyapp.answer_one";
    public static final String EXTRA_ANSWER_TWO = "android.bignerdranch.surveyapp.answer_two";

    // Constant key string for ConfigureSurveyActivity
    public static final String EXTRA_SAVE_EDITS_BUTTON_PRESSED =
            "com.android.bignerdranch.surveyapp.save_edits_button_pressed";

    private EditText mEditSurveyQuestion;
    private EditText mAnswerOne;
    private EditText mAnswerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_survey);

        // Reference string resource values
        TextView configureTitle = findViewById(R.id.configure_survey_button);
        mEditSurveyQuestion = findViewById(R.id.survey_question);
        mAnswerOne = findViewById(R.id.answer_one);
        mAnswerTwo = findViewById(R.id.answer_two);
        Button saveEditsButton = findViewById(R.id.save_edits);

        saveEditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert EditTexts to String values
                String mNewSurveyQuestion = mEditSurveyQuestion.toString();
                String mNewAnswerOne = mAnswerOne.toString();
                String mNewAnswerTwo = mAnswerTwo.toString();
                // Intent carries data back to Activity that launches this one
                Intent returnIntent = new Intent();
                // Add edited texts as extras
                returnIntent.putExtra(EXTRA_NEW_SURVEY_QUESTION, mNewSurveyQuestion);
                returnIntent.putExtra(EXTRA_ANSWER_ONE, mNewAnswerOne);
                returnIntent.putExtra(EXTRA_ANSWER_TWO, mNewAnswerTwo);
                // Everything went well
                setResult(RESULT_OK, returnIntent);
                // End this Activity. Android will remove it from the screen, then create and show
                // the previous Activity - MainActivity in this case
                finish();
            }
        });
    }
}
