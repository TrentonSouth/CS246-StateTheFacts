package com.example.statethefacts;

import java.util.ArrayList;
import java.util.List;

public class GameQuestionPresenter {
    private GameType gameType;
    private GameQuestion question;

    public GameQuestionPresenter(GameType gameType){
        this.gameType = gameType;

    }

    public GameQuestion getNextQuestion() {
        List<State> states = new ArrayList<>();
        states.add(new State("AZ","Phoenix","Arizona"));
        states.add(new State("OK","Oklahoma City","Oklahoma"));
        states.add(new State("TX","Austin","Texas"));
        states.add(new State("KN","Topeka","Kansas"));
        states.add(new State("LO","Baton Rouge","Louisiana"));


        question = new GameQuestion(states,QuestionsType.Capital);
        question.generate("Texas", GameType.MultipleChoice);
        return question;
    }

    public String checkAnswer(String submittedAnswer) {
        String answer = question.getAnswer();
        if(answer.toLowerCase().equalsIgnoreCase(submittedAnswer.toLowerCase()))
        {
            return "Correct Answer!";
        }
        return "Wrong Answer!";
    }


}
