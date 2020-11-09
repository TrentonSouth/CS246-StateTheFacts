package com.example.statethefacts;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GameSettingsPresenter {
    GameSettings gameSettings;

    public void saveSettings() {

    }

    public void getSettings() {

    }

    public void closeSettings() {

    }

    public void startGame() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void run() {
        Gson gson = new Gson();
        HttpURLConnection httpURLConnection;
        final String stateTheFactsURL = "http://www.statethefacts.us/admin/";

        try {
            URL url = new URL(stateTheFactsURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            int status = httpURLConnection.getResponseCode();
            System.out.println("HTTP Response code: " + status);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream = new URL(stateTheFactsURL).openStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            gameSettings = gson.fromJson(reader, GameSettings.class);
        } catch (Exception exception) {
        }

    }

    /*public void GameSettingsActivity(View view) {

    }*/


}
