package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 *  This class holds the result of a game, ongoing or historical.
 *  It contains a list of submitted answers.  This object can serialize and
 *  deserialize itself to and from JSON for a history of each play through.
 *
 * @author Gene Higgins
 * @since 12/1/2020
 */
public class GameResult {

    public static final String GAME_RESULT_PREFIX = "GameResult-";
    public static final String GAME_RESULT_EXTENSION = ".json";
    public static final String GAME_PREFERENCES = "GameData";
    public static final String GAME_PREFERENCE_CURRENTGAMEID = "CurrentGameId";

    private UUID gameId;
    private Date finishedOn;
    private GameType gameType;
    private List<GameAnswer> answers = new ArrayList<>();


    /**
     * Sets up a new Game based on the game type.  It generate a UUID
     * to make user the saved game file has a unique name
     * @param gameType - type of game to be played
     */
    public void startNewGame(GameType gameType) {
        this.gameId = UUID.randomUUID();
        this.gameType = gameType;
    }

    /**
     * Loads an ongoing game from application storage based on the UUID store in shared preferences
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult loadCurrentGame(Context context) {
        // Get the UUID of an ongoing game.  If the UUID is blank then a new game should be started
        SharedPreferences sharedPref = context.getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        String currentGameIdString = sharedPref.getString(GAME_PREFERENCE_CURRENTGAMEID, "");
        if (currentGameIdString.equals("")) {
            return null;
        }
        //game id found, load existing game
        UUID gameId = UUID.fromString(currentGameIdString);
        return loadGame(context, gameId);
    }

    /** loads an existing game from application storage based on a UUID
     * @param context the application context need to load a file
     * @param gameId UUID of the game to load
     * @return an instance of GameResult populated with game data
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult loadGame(Context context, UUID gameId) {
        String fileName = buildFileName(gameId);
        return loadGame(context, fileName);
    }

    /** loads an existing game from application storage based on a file name
     * @param context the application context need to load a file
     * @param fileName name of the file with game data
     * @return an instance of GameResult populated with game data
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public GameResult loadGame(Context context, String fileName) {

        // try to open the file
        FileInputStream fis;
        try {
            fis = context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // read in the full file
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
        }

        // parse the full file from JSON
        String contents = stringBuilder.toString();
        Gson gson = new Gson();
        return gson.fromJson(contents, GameResult.class);
    }

    /**
     * Save the current object to a file in JSON format
     * @param context application context used to create and open the file
     * @return true of the game was successfully saved.
     */
    public boolean saveCurrentGame(Context context) {
        // serialize current object to JSON
        Gson gson = new Gson();
        String data = gson.toJson(this);

        // open/create the file and write the JSON to it
        String fileName = buildFileName(gameId);
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(data.getBytes());

            SharedPreferences sharedPref = context.getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(GAME_PREFERENCE_CURRENTGAMEID, gameId.toString());
            editor.apply();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create a standardized file name
     * @param gameId unique id to set file name apart
     * @return the built file name
     */
    private String buildFileName(UUID gameId) {
        return GAME_RESULT_PREFIX + gameId.toString() + GAME_RESULT_EXTENSION;
    }

    /**
     * get a list of all the application files and load all that have the game prefix
     * @param context application context needed to load the files
     * @return list of game results from storage
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<GameResult> getAllGames(Context context) {
        String[] files = context.fileList();

        List<GameResult> gameResults = new ArrayList<>();
        for (String file : files) {
            if (!file.contains(GAME_RESULT_PREFIX))
                continue;
            GameResult gameResult = loadGame(context, file);
            gameResults.add(gameResult);
        }
        return gameResults;
    }

    /**
     * add a new answer from the user
     * @param gameAnswer new answer
     */
    public void addAnswer(GameAnswer gameAnswer) {
        answers.add(gameAnswer);
    }

    /**
     * sets the finished on value for the game, saves the game and clears the shared preferences
     * so a new game is started the next time the game is played
     * @param context
     */
    public void finishGame(Context context) {
        // Set finished date
        finishedOn = new Date();

        //save game
        saveCurrentGame(context);

        // clear current game id so the next game must start fresh
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("", GAME_PREFERENCE_CURRENTGAMEID);
        editor.commit();
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


}
