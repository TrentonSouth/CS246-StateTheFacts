package com.example.statethefacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * The FactsListActivity class is the backend for activity_facts_list.xml
 * Its purpose is to handle the events of toggling the different
 * fact types and using that information to generate a display
 * of all the facts for each state. It uses FactsListPresenter as
 * its presenter.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */

public class FactsListActivity extends AppCompatActivity {
    public static final String GAME_MODE = "com.example.statethefacts.GAME_MODE";
    private FactsListPresenter presenter;
    Intent intent;

    // When the class is instantiated, the presenter is created
    // and the display is built from buildList()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        presenter = new FactsListPresenter(this);
        buildList();
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
        getMenuInflater().inflate(R.menu.practice_menu, menu);
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

    // When the user checks or unchecks the bird option the
    // boolean value in the presenter for bird is updated
    // and the display is built from buildList()
    public void birdChange(View view) {
        presenter.setBirdChecked(((CheckBox) view).isChecked());
        buildList();
    }

    // When the user checks or unchecks the capital option the
    // boolean value in the presenter for capital is updated
    // and the display is built from buildList()
    public void capitalChange(View view) {
        presenter.setCapitalChecked(((CheckBox) view).isChecked());
        buildList();
    }

    // When the user checks or unchecks the flower option the
    // boolean value in the presenter for flower is updated
    // and the display is built from buildList()
    public void flowerChange(View view) {
        presenter.setFlowerChecked(((CheckBox) view).isChecked());
        buildList();
    }

    // When the user checks or unchecks the governor option the
    // boolean value in the presenter for governor is updated
    // and the display is built from buildList()
    public void governorChange(View view) {
        presenter.setGovernorChecked(((CheckBox) view).isChecked());
        buildList();
    }

    // When the user checks or unchecks the rock option the
    // boolean value in the presenter for rock is updated
    // and the display is built from buildList()
    public void rockChange(View view) {
        presenter.setRockChecked(((CheckBox) view).isChecked());
        buildList();
    }

    // When the user clicks the main menu button, the main
    // activitiy is opened.
    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // The buildList function calls on the buildList function in
    // the presenter to make the display and then it sets the
    // text of txtFacts to that value.
    private void buildList() {
        TextView txtFacts = (TextView) findViewById(R.id.txtFacts);
        String strFacts = presenter.buildList();
        txtFacts.setText(strFacts);
    }
}

