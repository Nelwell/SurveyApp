package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements
        SurveyQuestionFragment.ResultsListener,
        SurveyQuestionFragment.EditSurveyButtonListener,
        ResultsFragment.ResetResultsListener,
        ConfigureSurveyFragment.NewSurveyListener {

    private static final String TAG_CONFIG_FRAG = "CONFIG FRAGMENT";

    private SurveyQuestionFragment mSurveyQuestionFragment;
    private ResultsFragment mResultsFragment;

    private String mQuestion;
    private String mAnswerOne;
    private String mAnswerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestion = getString(R.string.are_you_excited);
        mAnswerOne = getString(R.string.heck_yeah);
        mAnswerTwo = getString(R.string.nope);

        // Create two home-screen fragments
        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(mQuestion, mAnswerOne, mAnswerTwo);
        mResultsFragment = new ResultsFragment();

        // Show the two created fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.survey_question_fragment_container, mSurveyQuestionFragment);
        ft.add(R.id.results_fragment_container, mResultsFragment);
        ft.commit();
    }

    @Override
    public void surveyResults(int answerOneCount, int answerTwoCount) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mResultsFragment = ResultsFragment.newInstance(answerOneCount, answerTwoCount);
        ft.replace(R.id.results_fragment_container, mResultsFragment);
        ft.commit();
        // Launch ResultsFragment
    }

    @Override
    public void saveEdits(String question, String answerOne, String answerTwo) {

        mQuestion = question;
        mAnswerOne = answerOne;
        mAnswerTwo = answerTwo;

        // Make brand new survey Fragment Activity by replacing existing activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(question, answerOne, answerTwo);
        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);

        Log.d("Main Activity", "Save Edits");

        ft.commit();
    }

    @Override
    public void resetResults(int answerOneCount, int answerTwoCount) {

        // Make brand new results Fragment Activity by replacing existing activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(mQuestion, mAnswerOne, mAnswerTwo);
        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);

        mResultsFragment = ResultsFragment.newInstance(answerOneCount, answerTwoCount);
        ft.replace(R.id.results_fragment_container, mResultsFragment);

        ft.commit();
    }

    // Opens ConfigureSurveyFragment
    public void editSurveyButtonPressed() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Make brand new ConfigureSurvey Fragment Activity by replacing ResultsFragment
        ConfigureSurveyFragment mConfigureSurveyFragment = ConfigureSurveyFragment.newInstance();
        ft.replace(R.id.results_fragment_container, mConfigureSurveyFragment);
        ft.addToBackStack(TAG_CONFIG_FRAG);

        ft.commit();
    }
}

