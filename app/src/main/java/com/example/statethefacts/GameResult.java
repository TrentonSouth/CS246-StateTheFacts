package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GameResult {

    public static final String GAME_RESULT_PREFIX = "GameResult-";
    public static final String GAME_RESULT_EXTENSION = ".json";
    public static final String GAME_PREFERENCES = "GameData";
    public static final String GAME_PREFERENCE_CURRENTGAMEID = "CurrentGameId";

    private UUID gameId;
    private Date finishedOn;
    private GameType gameType;
    private List<GameAnswer> answers = new ArrayList<>();


    public void StartNewGame(GameType gameType){
        this.gameId = UUID.randomUUID();
        this.gameType = gameType;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult LoadCurrentGame(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        String currentGameIdString = sharedPref.getString(GAME_PREFERENCE_CURRENTGAMEID, "");
        if(currentGameIdString == ""){
            return null;
        }
        UUID gameId = UUID.fromString(currentGameIdString);
        return LoadGame(context, gameId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult LoadGame(Context context, UUID gameId) {
        String fileName = BuildFileName(gameId);
        return LoadGame(context, fileName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult LoadGame(Context context, String fileName) {

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
            Gson gson = new Gson();
            GameResult gameResult = gson.fromJson(contents,GameResult.class);
            return gameResult;
        }

    }

    public boolean SaveCurrentGame(Context context) {
        Gson gson = new Gson();
        String data = gson.toJson(this);
        String fileName = BuildFileName(gameId);
        try{
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(data.getBytes());

            SharedPreferences sharedPref = context.getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(GAME_PREFERENCE_CURRENTGAMEID, gameId.toString());
            editor.apply();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private String BuildFileName(UUID gameId) {
        return GAME_RESULT_PREFIX + gameId.toString() + GAME_RESULT_EXTENSION;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<GameResult> GetAllGames(Context context) {
        String[] files = context.fileList();

        List<GameResult> gameResults = new ArrayList<>();
        for(String file : files) {
            if(!file.contains(GAME_RESULT_PREFIX))
                continue;
            GameResult gameResult = LoadGame(context, file);
            gameResults.add(gameResult);
        }
        return gameResults;
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
