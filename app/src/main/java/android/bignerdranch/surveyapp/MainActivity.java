package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements
        SurveyQuestionFragment.ResultsListener,
        SurveyQuestionFragment.EditSurveyButtonListener,
//        SurveyQuestionFragment.SetEditsListener,
        ResultsFragment.ResetResultsListener,
        ResultsFragment.ResetSurveyListener,
        ConfigureSurveyFragment.NewSurveyListener {

    private static final String TAG_CONFIG_FRAG = "CONFIG FRAGMENT";
    private static final String TAG_RESULTS_FRAG = "RESULTS FRAGMENT";

    private SurveyQuestionFragment mSurveyQuestionFragment;
    private ResultsFragment mResultsFragment;
//    private ConfigureSurveyFragment mConfigureSurveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String mSurveyQuestion = mQuestion.getText().toString();
//        String mFirstAnswer = mAnswerOne.getText().toString();
//        String mSecondAnswer = mAnswerTwo.getText().toString();

//        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(mSurveyQuestion, mFirstAnswer, mSecondAnswer);
//        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance();
//        ResultsFragment mResultsFragment = ResultsFragment.newInstance();

        // Create three fragments
        mSurveyQuestionFragment = new SurveyQuestionFragment();
        mResultsFragment = new ResultsFragment();
//        mConfigureSurveyFragment = new ConfigureSurveyFragment();

        // Show two of the fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.results_fragment_container, mResultsFragment);
        ft.add(R.id.survey_question_fragment_container, mSurveyQuestionFragment);
        ft.commit();
    }

    @Override
    public void surveyResults(int mAnswerOneCount, int mAnswerTwoCount) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mResultsFragment = ResultsFragment.newInstance(mAnswerOneCount, mAnswerTwoCount);
        ft.replace(R.id.results_fragment_container, mResultsFragment);
//        ft.addToBackStack("RESULT");
        ft.commit();
        // Launch ResultsFragment
    }

    @Override
    public void saveEdits(String mQuestion, String mAnswerOne, String mAnswerTwo) {

        // Make brand new survey Fragment Activity by replacing existing activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(mQuestion, mAnswerOne, mAnswerTwo);
        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);

        ft.commit();
    }

    @Override
    public void resetResults(int mAnswerOneCount, int mAnswerTwoCount) {

        // Make brand new results Fragment Activity by replacing existing activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mSurveyQuestionFragment = new SurveyQuestionFragment();
        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);

        mResultsFragment = ResultsFragment.newInstance(mAnswerOneCount, mAnswerTwoCount);
        ft.replace(R.id.results_fragment_container, mResultsFragment);

        ft.commit();
    }

    @Override
    public void resetSurvey() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mSurveyQuestionFragment = new SurveyQuestionFragment();
        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);

        ft.commit();
    }

//    @Override
//    public void setEditsButtonPressed(String mQuestion, String mAnswerOne, String mAnswerTwo) {
//
//        // Make brand new survey Fragment Activity by replacing existing activity
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        mSurveyQuestionFragment = SurveyQuestionFragment.newInstance(mQuestion, mAnswerOne, mAnswerTwo);
//        ft.replace(R.id.survey_question_fragment_container, mSurveyQuestionFragment);
//
//        ft.commit();
//    }

    // Opens ConfigureSurveyFragment
    public void editSurveyButtonPressed() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Create new ToDoItemDetailFragment with the selected ToDoItem
        ConfigureSurveyFragment mConfigureSurveyFragment = new ConfigureSurveyFragment();
        ft.replace(R.id.results_fragment_container, mConfigureSurveyFragment);

        ft.addToBackStack(TAG_CONFIG_FRAG);

        ft.commit();
    }
}

