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

/**
 *  Initialize the primary game view.  Pipes events to the presenter
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */

public class GameActivity extends AppCompatActivity {

    public static final String GAME_ID = "com.example.statethefacts.GAME_ID";
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";

    Intent intent;
    GameViewModel viewModel;  //note used as a presenter, used to keep state between screen rotations.
    GameCardFactory cardFactory;

    /**
     * Object initialization
     * Determine if a new game should be started or old one continued and loads it.
     * @param savedInstanceState - activity initialization data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_flipper_activity);

        Intent intent  = getIntent();
        boolean startNewGame = intent.getBooleanExtra(MainActivity.START_NEW_GAME,false);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        if(startNewGame && viewModel.getGameId() == null)
            viewModel.startNewGame();
        else
            viewModel.resumeGame();

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
            case R.id.game_mode:
                intent = new Intent(this, GameSettingsActivity.class);
                intent.putExtra(GAME_MODE, "game");
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * Save the game when the display is rotated or the app is no longer active
     */
    @Override
    protected void onPause(){
        super.onPause();

        viewModel.saveGame();
    }

    public void flipCard(CardType nextCardType) {
        Fragment nextCard = null;

        try {
            nextCard = cardFactory.getGameCard(nextCardType);
        } catch (ClassNotFoundException ex){
            Log.d("Error", "onCreate: Error Generating Game Card: "+ex.getMessage());
        }

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

    /**
     * Saves the entered answer and flip to the Answer card to show the results
     * @param view - current activity fragment
     */
    public void submitAnswer(View view) {
        viewModel.submitAnswer(this);
        flipCard(CardType.Answer);
    }

    /**
     * Checks to see if there are more states to ask questions about
     * If there are states then go to the next question card.
     * If all states have been used then finish the game
     * @param view - current activity fragment
     */
    public void nextQuestion(View view) {
        if(viewModel.hasMoreStates())
        {
            flipCard(CardType.Question);
            return;
        }

        viewModel.endGame(this);
        Intent intent = new Intent(this, ScoreCardActivity.class);
        intent.putExtra(GAME_ID, viewModel.getGameId().toString());
        startActivity(intent);
    }
}