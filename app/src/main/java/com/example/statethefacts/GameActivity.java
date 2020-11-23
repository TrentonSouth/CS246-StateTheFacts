package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.ui.main.CardAnswerFragment;
import com.example.statethefacts.ui.main.CardMultipleChoiceFragment;
import com.example.statethefacts.ui.main.CardTextEntryFragment;
import com.example.statethefacts.ui.main.GameViewModel;

public class GameActivity extends AppCompatActivity {

    GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_flipper_activity);

        Intent intent  = getIntent();
        int gameTypeValue = intent.getIntExtra(MainActivity.GAMETYPE, GameType.MultipleChoice.ordinal());
        GameType gameType = GameType.values()[gameTypeValue];

        boolean startNewGame = intent.getBooleanExtra(MainActivity.START_NEW_GAME,true);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        if(startNewGame)
            viewModel.StartNewGame(gameType);
        else
            viewModel.ResumeGame();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new CardTextEntryFragment(viewModel))
                    .commit();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();

        viewModel.saveGame();
    }


    private boolean showingBack;

    public void flipCard() {
        Fragment nextCard;
        if (showingBack) {
            switch (viewModel.getGameType()) {
                case MultipleChoice:
                    nextCard = new CardMultipleChoiceFragment(viewModel);
                    break;
                case TextEntry:
                    nextCard = new CardTextEntryFragment(viewModel);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Game Type: " + viewModel.getGameType().toString());
            }
        } else {
            nextCard = new CardAnswerFragment(viewModel);
        }

        showingBack = !showingBack;

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)
                .replace(R.id.container, nextCard)
                .commit();
    }

    public void submitAnswer(View view) {

        String answer = "";
        switch (viewModel.getGameType()) {
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
                throw new IllegalArgumentException("Invalid Game Type: " + viewModel.getGameType().toString());
        }

        viewModel.submitAnswer(answer);
        flipCard();
    }

    public void nextQuestion(View view) {
        flipCard();
    }
}