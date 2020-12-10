package com.example.statethefacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.statethefacts.ui.main.GameCardFactory;
import com.example.statethefacts.ui.main.GameViewModel;

public class GameActivity extends AppCompatActivity {

    public static final String GAME_ID = "com.example.statethefacts.GAME_ID";

    Intent intent;
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

    /**
     * The onCreateOptionsMenu Method
     * Purpose: create the 3 dots (ellipsis) on right side of the app in the top bar
     * @param menu
     * @return boolean as true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.youtube.com/watch?v=kknBxoCOYXI
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    /**
     * onOptionsItemSelected Method
     * Purpose: to listen to the user action when they select a menu item from the 3 dots
     * (ellipsis) on the upper right side of the app, in the top bar. Navigates to specific
     * activity based on user selection
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.history:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.score_card:
                intent = new Intent(this, ScoreCardActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.learn_mode:
                intent = new Intent(this, FactsListActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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