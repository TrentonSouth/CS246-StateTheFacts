package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.ui.main.GameCardFactory;
import com.example.statethefacts.ui.main.GameViewModel;

public class GameActivity extends AppCompatActivity {

    public static final String GAME_ID = "com.example.statethefacts.GAME_ID";

    GameViewModel viewModel;
    GameCardFactory cardFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_flipper_activity);

        Intent intent  = getIntent();
        boolean startNewGame = intent.getBooleanExtra(MainActivity.START_NEW_GAME,true);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        if(startNewGame)
            viewModel.StartNewGame();
        else
            viewModel.ResumeGame();

        cardFactory = new GameCardFactory(viewModel);


        try {
            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, cardFactory.getGameCard(CardType.Question))
                        .commit();
            }
        }catch(ClassNotFoundException ex){
            Log.d("Error", "onCreate: Error Generating Game Card: "+ex.getMessage());
        }
    }

    @Override
    protected void onPause(){
        super.onPause();

        viewModel.saveGame();
    }


    private boolean showingBack;

    public void flipCard() {
        Fragment nextCard = null;

        try {
            if (showingBack) {
                nextCard = cardFactory.getGameCard(CardType.Question);
            } else {
                nextCard = cardFactory.getGameCard(CardType.Answer);
            }
        } catch (ClassNotFoundException ex){
            Log.d("Error", "onCreate: Error Generating Game Card: "+ex.getMessage());
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
        viewModel.submitAnswer(this);
        flipCard();
    }

    public void nextQuestion(View view) {
        if(viewModel.hasMoreStates())
        {
            flipCard();
            return;
        }

        viewModel.endGame(this);
        Intent intent = new Intent(this, ScoreCardActivity.class);
        intent.putExtra(GAME_ID, viewModel.getGameId().toString());
        startActivity(intent);
    }
}