package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        SurveyQuestionFragment.ResultsListener,
        SurveyQuestionFragment.EditSurveyButtonListener,
        ResultsFragment.ResetListener,
        ConfigureSurveyFragment.EditSurveyListener {

    private static final String TAG_CONFIG_FRAG = "CONFIG FRAGMENT";

//    private SurveyQuestionFragment mSurveyQuestionFragment;
    private ResultsFragment mResultsFragment;
    private ConfigureSurveyFragment mConfigureSurveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurveyQuestionFragment mSurveyQuestionFragment = SurveyQuestionFragment.newInstance();
//        ResultsFragment mResultsFragment = ResultsFragment.newInstance();

//        // Create three fragments
//        mSurveyQuestionFragment = new SurveyQuestionFragment();
        mResultsFragment = new ResultsFragment();
        mConfigureSurveyFragment = new ConfigureSurveyFragment();

        // Show two of the fragments
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.results_fragment_container, mResultsFragment);
        ft.add(R.id.survey_question_fragment_container, mSurveyQuestionFragment);
        ft.commit();
    }

    @Override
    public void surveyResults(int yes, int no) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ResultsFragment mResultsFragment = ResultsFragment.newInstance(yes, no);
        ft.replace(R.id.results_fragment_container, mResultsFragment);
//        ft.addToBackStack("RESULT");
        ft.commit();
        // launch ResultActivity

    }

    public void editSurveyButtonPressed() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Create new ToDoItemDetailFragment with the selected ToDoItem
//        ConfigureSurveyFragment configureSurveyFragment = ConfigureSurveyFragment.newInstance();
        ft.replace(R.id.results_fragment_container, mConfigureSurveyFragment);

        // Add to the back stack, so if user presses back button from the DetailFragment,
        // it will revert this transaction - Activity will remove the DetailFragment, showing the Add+List fragments
        ft.addToBackStack(TAG_CONFIG_FRAG);

        ft.commit();
    }

    public void editSurvey(SurveyData newSurvey) {


//        mSurveyQuestion.getText().toString(newSurvey);


//        mTodoItems.add(newItem);

//        // Add item to the ArrayList
//        Log.d(TAG, "Notified that this new item was created: " + mTodoItems);
//
//        // Get reference to List Fragment from the FragmentManager, tell this Fragment that the date has changed
//        FragmentManager fm = getSupportFragmentManager();
//        ToDoListFragment listFragment = (ToDoListFragment) fm.findFragmentByTag(TAG_LIST_FRAG);
//        listFragment.notifyItemsChanged();
//
//        hideKeyboard();

//    }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Create new ToDoItemDetailFragment with the selected ToDoItem
//        ConfigureSurveyFragment configureSurveyFragment = ConfigureSurveyFragment.newInstance();
//        ft.replace(R.id.results_fragment_container, mConfigureSurveyFragment);


        // Add to the back stack, so if user presses back button from the DetailFragment,
        // it will revert this transaction - Activity will remove the DetailFragment, showing the Add+List fragments
		ft.addToBackStack(TAG_CONFIG_FRAG);

        ft.commit();
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        // Swaps ResultsFragment with ConfigureSurveyFragment
//        ft.add(R.id.results_fragment_container, mConfigureSurveyFragment);
//        ft.commit();
    }

    @Override
    public void resetSurvey(int yes, int no) {

        // Make brand new survey activity, replace result activity.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ResultsFragment mResultsFragment = ResultsFragment.newInstance(yes, no);
        ft.replace(R.id.results_fragment_container, mResultsFragment);
        ft.commit();

    }
}

