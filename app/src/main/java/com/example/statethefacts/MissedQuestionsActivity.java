package com.example.statethefacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * The MissedQuestionsActivity class is the backend for activity_missed_questions.xml
 * Its purpose is to call on the presenter to generate a display string that
 * can then be used to populate the text field on the form.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class MissedQuestionsActivity extends AppCompatActivity {
    private MissedQuestionsPresenter presenter;

    // When the class is instantiated, the presenter is created
    // and the display is built from getDisplay()
    // that value is used to set the text of txtMissedQuestions
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_questions);

        presenter = new MissedQuestionsPresenter(this);
        // set txtMissedQuestions to the result of getDisplay from the presenter
        TextView txtMissedQuestions = this.findViewById(R.id.txtMissedQuestions);
        txtMissedQuestions.setText(presenter.getDisplay(this));
    }

    // When the user clicks the main menu button, the main
    // activitiy is opened.
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} 