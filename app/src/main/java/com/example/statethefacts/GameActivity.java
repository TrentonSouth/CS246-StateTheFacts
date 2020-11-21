package com.example.statethefacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.statethefacts.ui.main.CardAnswerFragment;
import com.example.statethefacts.ui.main.CardMultipleChoiceFragment;
import com.example.statethefacts.ui.main.CardTextEntryFragment;
import com.example.statethefacts.ui.main.GameViewModel;

public class GameActivity extends AppCompatActivity {

    private GameQuestionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_flipper_activity);

        GameViewModel model = new ViewModelProvider(this).get(GameViewModel.class);

        presenter = new GameQuestionPresenter(this, GameType.TextEntry);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new CardTextEntryFragment(presenter))
                    .commit();
        }
    }

    public void onFlipCard(View view) {
        flipCard();
    }

    private boolean showingBack;

    public void flipCard() {
//        if (showingBack) {
//            getSupportFragmentManager().popBackStack();
//            showingBack = false;
//            return;
//        }

        // Flip to the back.

        //showingBack = true;


        Fragment nextCard;
        if (showingBack) {
            switch (presenter.getGameType()) {
                case MultipleChoice:
                    nextCard = new CardMultipleChoiceFragment(presenter);
                    break;
                case TextEntry:
                    nextCard = new CardTextEntryFragment(presenter);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Game Type: " + presenter.getGameType().toString());
            }
        } else {
            nextCard = new CardAnswerFragment(presenter);
        }


        showingBack = !showingBack;


        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getSupportFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, nextCard)

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                //.addToBackStack(null)

                // Commit the transaction.
                .commit();
    }

    public void submitAnswer(View view) {

        String answer = "";
        switch (presenter.getGameType()) {
            case MultipleChoice:
                RadioGroup radioGroup = findViewById(R.id.radioGroup_answers);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                if (selectedRadioButton == null)
                    return;

                answer = selectedRadioButton.getText().toString();
                break;

            case TextEntry:
                EditText editTextAnswer = findViewById(R.id.editTextAnswer);
                if (editTextAnswer == null)
                    return;

                answer = editTextAnswer.getText().toString();
                break;

            default:
                throw new IllegalArgumentException("Invalid Game Type: " + presenter.getGameType().toString());
        }

        presenter.submitAnswer(answer);

    }

    public void nextQuestion(View view) {
        flipCard();
    }


    /**
     * A fragment representing the front of the card.
     */


    /**
     * A fragment representing the back of the card.
     */

}