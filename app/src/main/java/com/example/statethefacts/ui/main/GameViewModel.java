package com.example.statethefacts.ui.main;

import android.app.Application;
import android.os.Build;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.statethefacts.Facts;
import com.example.statethefacts.GameActivity;
import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.GameResult;
import com.example.statethefacts.GameSettings;
import com.example.statethefacts.GameType;
import com.example.statethefacts.GetFacts;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.R;
import com.example.statethefacts.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameViewModel extends AndroidViewModel {
    private GameQuestion question;
    private List<State> remainingStates;
    private List<State> allStates;
    private GameResult gameResult;
    private GameAnswer lastAnswer;
    private GameType gameType;
    GameSettings gameSettings;

    public GameViewModel(Application application){
        super(application);

        GetFacts gf = new GetFacts();
        Facts facts = gf.fetchFacts(getApplication().getApplicationContext());
        allStates = facts.getStates();
        gameResult = new GameResult();

        gameSettings = new GameSettings();
        gameSettings.loadGameSettings(application);
        gameType = gameSettings.getGameType();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ResumeGame() {
         gameResult = gameResult.loadCurrentGame(getApplication().getApplicationContext());
    }

    public void StartNewGame(){
        int numberOfStates = gameSettings.getNumberOfFacts();
        gameResult.startNewGame(gameType);
        GetRandomStates(numberOfStates);
    }

    private void GetRandomStates(int numberOfStates) {
        remainingStates = new ArrayList<>();

        List<State> statesToChooseFrom = new ArrayList<>();
        statesToChooseFrom.addAll(allStates);

        Random random = new Random();
        for (int i = 0; i<numberOfStates; i++)
        {
            int index = random.nextInt(statesToChooseFrom.size());
            remainingStates.add(statesToChooseFrom.get(index));
            statesToChooseFrom.remove(index);
        }
    }

    public GameQuestion getNextQuestion() {
        QuestionsType questionsType = getNextQuestionType();
        question = new GameQuestion(allStates,questionsType);
        question.generate(remainingStates, gameResult.getGameType());
        return question;
    }

    private QuestionsType getNextQuestionType() {
        List<QuestionsType> availableTypes = new ArrayList<>();
        if(gameSettings.getBird())
            availableTypes.add(QuestionsType.Bird);
        if(gameSettings.getCapital())
            availableTypes.add(QuestionsType.Capital);
        if(gameSettings.getFlower())
            availableTypes.add(QuestionsType.Flower);
        if(gameSettings.getGovernor())
            availableTypes.add(QuestionsType.Governor);
        if(gameSettings.getRock())
            availableTypes.add(QuestionsType.Rock);

        Random random = new Random();

        int index = random.nextInt(availableTypes.size());
        return availableTypes.get(index);
    }

    public void submitAnswer(GameActivity activity) {
        String submittedAnswer = getSubmittedAnswer(activity);

        lastAnswer = new GameAnswer(gameResult.getGameId(), question.getStateName(), question.getAnswer(), submittedAnswer, question.getQuestionType());
        gameResult.addAnswer(lastAnswer);
    }

    private String getSubmittedAnswer(GameActivity activity) {

        switch (getGameType()) {
            case MultipleChoice:
                RadioGroup radioGroup = activity.findViewById(R.id.radioGroup_answers);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = activity.findViewById(selectedId);
                if (selectedRadioButton == null)
                    return "";

                return selectedRadioButton.getText().toString();

            case TextEntry:
                EditText editTextAnswer = activity.findViewById(R.id.editTextAnswer);
                if (editTextAnswer == null)
                    return "";

                return editTextAnswer.getText().toString();


            default:
                throw new IllegalArgumentException("Invalid Game Type: " + getGameType().toString());
        }
    }


    public GameQuestion getQuestion(){
        return question;
    }

    public GameAnswer getLastAnswer() {
        return lastAnswer;
    }

    public GameType getGameType() { return gameResult.getGameType(); }

    public void saveGame() {
        gameResult.saveCurrentGame(getApplication().getApplicationContext());
    }

    public boolean hasMoreStates() {
        return remainingStates.size() >= 1;
    }

    public UUID getGameId() {
        return gameResult.getGameId();
    }

    public void endGame(GameActivity activity) {
        gameResult.finishGame(activity);
    }

//    private void getStatesForGame(int stateCount){
//        int statesToKeep = states.size()-stateCount;
//        Random random = new Random();
//        for(int i=0; i<stateCount; i++){
//            int index = random.nextInt(states.size());
//            index;
//
//        }
//    }

}