package com.example.statethefacts;


import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;


public class GameSettingsPresenter {

    GameSettings gameSettings;
    GameSettingsActivity activity;


    public GameSettingsPresenter(GameSettingsActivity activity) {
        this.activity = activity;
        gameSettings = new GameSettings();
    }

    public void loadSettings(String mode) {
        // set button text to match mode
        TextView button = activity.findViewById(R.id.buttonStartGame);
        button.setText(mode);

        gameSettings.LoadSettings(activity);

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
    }

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
        gameSettings.SaveSettings(activity);
    }


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
        gameSettings.SaveSettings(activity);
    }
}
