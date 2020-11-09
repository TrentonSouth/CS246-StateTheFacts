package com.example.statethefacts;

import android.os.Build;
import android.util.Log;

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

public class PracticeModeReviewPresenter {

    GameSettings gameSettings;
    PracticeModeReviewActivity practiceModeReviewActivity;

    /*public checkAnswer() {

    }
     */

    /*public displayNextState() {

    }
     */

    /*public displayEndMode() {

    }
     */

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
            Log.e("GameSettingsPresenter", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("GameSettingsPresenter", e.toString());
        }

        try {
            InputStream inputStream = new URL(stateTheFactsURL).openStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            gameSettings = gson.fromJson(reader, GameSettings.class);
        } catch (Exception exception) {
            Log.e("GameSettingsPresenter", exception.toString());
        }
    }
}
