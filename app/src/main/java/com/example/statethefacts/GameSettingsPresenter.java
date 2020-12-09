package com.example.statethefacts;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The GameSettingsPresenter class handles the
 * logic for the GameSettingsActivity class
 */
public class GameSettingsPresenter {

    GameSettings gameSettings;
    GameSettingsActivity activity;

    /**
     * Construct the GameSettingsPresenter which extends the Activity class
     * @param activity
     */
    public GameSettingsPresenter(GameSettingsActivity activity) {
        this.activity = activity;
        gameSettings = new GameSettings();
    }

    /**
     * the loadSettings Method
     * Purpose: pass the whether a checkbox has been selected from the GameSettingsActivity to the GameSettings class
     * @param mode
     */
    public void loadSettings(String mode) {
        // set button text to match mode
        TextView button = activity.findViewById(R.id.buttonStartGame);
        button.setText(mode);

        gameSettings.loadGameSettings(activity);

        CheckBox capital = activity.findViewById(R.id.checkBoxCapital);
        capital.setChecked(gameSettings.getCapital());

        CheckBox flower = activity.findViewById(R.id.checkBoxFlower);
        flower.setChecked(gameSettings.getFlower());

        CheckBox rock = activity.findViewById(R.id.checkBoxRock);
        rock.setChecked(gameSettings.getRock());

        CheckBox governor = activity.findViewById(R.id.checkBoxGovernor);
        governor.setChecked(gameSettings.getGovernor());

        CheckBox bird = activity.findViewById(R.id.checkBoxBird);
        bird.setChecked(gameSettings.getBird());

        RadioButton multipleChoice = activity.findViewById(R.id.radioButtonMultipleChoice);

        RadioButton textEntry = activity.findViewById(R.id.radioButtonTextEntry);

        if (gameSettings.getGameType() == GameType.MultipleChoice) {
            multipleChoice.setChecked(true);
            textEntry.setChecked(false);
        } else {
            textEntry.setChecked(true);
            multipleChoice.setChecked(false);
        }
        TextView viewNumberOfFacts = activity.findViewById(R.id.numberOfFacts);
        int intNumberOfFacts = gameSettings.getNumberOfFacts();
        String stringNumberOfFacts = String.valueOf(intNumberOfFacts);
        viewNumberOfFacts.setText(stringNumberOfFacts);
    }

    /**
     *the updateFactTypes Method
     * Purpose: pass which fact types were updated by the user from the GameSettingsActivity to the GameSettings class
     * @param view
     */
    public void updateFactTypes(View view) {
        //https://developer.android.com/guide/topics/ui/controls/checkbox#:~:text=To%20define%20the%20click%20event,then%20implement%20the%20corresponding%20method.
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkBoxCapital:
                gameSettings.setCapital(checked);
                break;
            case R.id.checkBoxFlower:
                gameSettings.setFlower(checked);
                break;
            case R.id.checkBoxRock:
                gameSettings.setRock(checked);
                break;
            case R.id.checkBoxGovernor:
                gameSettings.setGovernor(checked);
                break;
            case R.id.checkBoxBird:
                gameSettings.setBird(checked);
                break;

        }
        gameSettings.saveGameSettings(activity);
    }

    /**
     * the updateGameType Method
     * Purpose: pass which game type was chosen by the user from the GameSettingsActivity to the GameSettings class
     * @param view
     */
    public void updateGameType(View view) {
        //https://developer.android.com/guide/topics/ui/controls/radiobutton
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonMultipleChoice:
                if (checked) {
                    gameSettings.setGameType(GameType.MultipleChoice);
                }
                break;
            case R.id.radioButtonTextEntry:
                if (checked) {
                    gameSettings.setGameType(GameType.TextEntry);
                }
                break;
        }
        gameSettings.saveGameSettings(activity);
    }

    /**
     * the updateNumberOfFacts Method
     * Purpose: make sure user chooses a number between 1 and 50
     * @param numberOfFacts
     * @return boolean
     */
    public boolean updateNumberOfFacts(int numberOfFacts) {
            if (numberOfFacts < 1) {
                Toast.makeText(activity.getApplicationContext(), "Please enter a number between 1 and 50", Toast.LENGTH_LONG).show();
                return false;
            }
            else if (numberOfFacts > 50) {
                Toast.makeText(activity.getApplicationContext(), "Please enter a number between 1 and 50", Toast.LENGTH_LONG).show();
                return false;
            } else {
                gameSettings.setNumberOfFacts(numberOfFacts);
                gameSettings.saveGameSettings(activity);
                return true;
            }

    }

}

