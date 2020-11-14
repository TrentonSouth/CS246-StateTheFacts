package com.example.statethefacts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GameResult {
    private UUID gameId;
    private Date finishedOn;
    private GameType gameType;
    private List<GameAnswer> answers = new ArrayList<>();

    public GameResult(GameType gameType){
        this.gameId = UUID.randomUUID();
        this.gameType = gameType;
    }


    public UUID getGameId() {
        return gameId;
    }

    public Date getFinishedOn() {
        return finishedOn;
    }

    public GameType getGameType() {
        return gameType;
    }

    public List<GameAnswer> getAnswers() {
        return answers;
    }

    public void AddAnswer(GameAnswer gameAnswer) {
        answers.add(gameAnswer);
    }

    public void FinishGame(){
        finishedOn = new Date();
    }
}
