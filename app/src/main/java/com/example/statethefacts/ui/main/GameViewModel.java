package com.example.statethefacts.ui.main;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.statethefacts.Facts;
import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.GameResult;
import com.example.statethefacts.GameType;
import com.example.statethefacts.GetFacts;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.State;

import java.util.List;
import java.util.Random;

public class GameViewModel extends AndroidViewModel {
    private GameQuestion question;
    private List<State> states;
    private List<State> allStates;
    private GameResult gameResult;
    private GameAnswer lastAnswer;

    public GameViewModel(Application application){
        super(application);

        GetFacts gf = new GetFacts();
        Facts facts = gf.Fetch(getApplication().getApplicationContext());
        states = facts.getStates();
        gameResult = new GameResult();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ResumeGame() {
         gameResult = gameResult.LoadCurrentGame(getApplication().getApplicationContext());
    }

    public void StartNewGame(GameType gameType){
        gameResult.StartNewGame(gameType);
    }

    public GameQuestion getNextQuestion(QuestionsType questionsType) {
        question = new GameQuestion(states,questionsType);
        question.generate("Texas", gameResult.getGameType());
        return question;
    }

    public void submitAnswer(String submittedAnswer) {
        lastAnswer = new GameAnswer(gameResult.getGameId(), question.getState(), question.getAnswer(), submittedAnswer, question.getQuestionType());
        gameResult.AddAnswer(lastAnswer);
    }


    public GameQuestion getQuestion(){
        return question;
    }

    public GameAnswer getLastAnswer() {
        return lastAnswer;
    }

    public GameType getGameType() { return gameResult.getGameType(); }

    public void saveGame() {
        gameResult.SaveCurrentGame(getApplication().getApplicationContext());
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