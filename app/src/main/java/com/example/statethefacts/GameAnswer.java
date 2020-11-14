package com.example.statethefacts;

import com.example.statethefacts.QuestionsType;

import java.util.UUID;

public class GameAnswer {
    private UUID gameId;
    private String state;
    private String correctAnswer;
    private String submittedAnswer;
    private QuestionsType questionsType;

    public GameAnswer(UUID gameId, String state, String correctAnswer, String submittedAnswer, QuestionsType questionsType){
        this.gameId = gameId;
        this.state = state;
        this.correctAnswer = correctAnswer;
        this.submittedAnswer = submittedAnswer;
        this.questionsType = questionsType;
    }

    public boolean HasCorrectAnswer() {
        return correctAnswer.equalsIgnoreCase(submittedAnswer);
    }



    public String getSubmittedAnswer() {
        return submittedAnswer;
    }
}
