package android.bignerdranch.surveyapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EditSurveyButtonPressed implements Parcelable{

    private boolean buttonPressed;

    public EditSurveyButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public EditSurveyButtonPressed(Parcel in) {    // Constructor needed for un-parceling ToDoItem objects
        buttonPressed = in.readInt() == 1;  // true if a 1 was written, false otherwise
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    @NonNull
    @Override
    public String toString() {
        return " is pressed? " + buttonPressed;
    }


    // Code required by the Parcelable interface.
    // If ToDoItem is parcelable, can send as an Extra between Fragments/Activities
    static final Parcelable.Creator<EditSurveyButtonPressed> CREATOR = new Parcelable.Creator<EditSurveyButtonPressed>() {
        public EditSurveyButtonPressed createFromParcel(Parcel in) {
            return new EditSurveyButtonPressed(in);
        }

        @Override
        public EditSurveyButtonPressed[] newArray(int size) {
            return new EditSurveyButtonPressed[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( buttonPressed ? 1 : 0);   // Write 1 if urgent == true, 0 otherwise
    }


}
