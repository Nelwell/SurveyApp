package android.bignerdranch.surveyapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SurveyData implements Parcelable{

    private String question;
    private String yesAnswer;
    private String noAnswer;

    public SurveyData(String question, String yesAnswer, String noAnswer) {
        this.question = question;
        this.yesAnswer = yesAnswer;
        this.noAnswer = noAnswer;
    }

    public SurveyData(Parcel in) {
        question = in.readString();
        yesAnswer = in.readString();
        noAnswer = in.readString();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getYesAnswer() {
        return yesAnswer;
    }

    public void setYesAnswer(String yesAnswer) {
        this.yesAnswer = yesAnswer;
    }

    public String getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(String noAnswer) {
        this.noAnswer = noAnswer;
    }

    @NonNull
    @Override
    public String toString() {
        return question + " " + yesAnswer + " " + noAnswer;
    }

    // Code required by the Parcelable interface.
    // If ToDoItem is parcelable, can send as an Extra between Fragments/Activities
    static final Parcelable.Creator<SurveyData> CREATOR = new Parcelable.Creator<SurveyData>() {
        public SurveyData createFromParcel(Parcel in) {
            return new SurveyData(in);
        }

        @Override
        public SurveyData[] newArray(int size) {
            return new SurveyData[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(yesAnswer);
        dest.writeString(noAnswer);
    }
}
