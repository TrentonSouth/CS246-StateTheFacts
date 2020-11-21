package com.example.statethefacts.ui.main;

import androidx.lifecycle.ViewModel;

import com.example.statethefacts.Facts;
import com.example.statethefacts.GameActivity;
import com.example.statethefacts.GameAnswer;
import com.example.statethefacts.GameQuestion;
import com.example.statethefacts.GameResult;
import com.example.statethefacts.GameType;
import com.example.statethefacts.GetFacts;
import com.example.statethefacts.QuestionsType;
import com.example.statethefacts.State;

import java.util.List;

public class GameViewModel extends ViewModel {
    private GameQuestion question;
    private List<State> states;
    private GameResult gameResult;
    private GameAnswer lastAnswer;
    private GameType gameType;


    public GameViewModel(){
        //this.activity = activity;

        GetFacts gf = new GetFacts();

//        Facts facts = gf.Fetch(this);
//        states = facts.getStates();
//        this.gameType = gameType;
        gameResult = new GameResult(gameType);
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

    public GameType getGameType() { return gameType; }
}