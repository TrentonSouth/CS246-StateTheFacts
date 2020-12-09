package com.example.statethefacts;

import android.content.Context;

import com.google.gson.Gson;

import java.io.*;

/**
 * The GetFacts class is the class that handles getting the facts
 * that were obtained when the program started and were written
 * to a file, back out of that file and returned to the calling
 * function.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/8/2020
 */
public class GetFacts {

    /**
     * the fetchFacts Method
     * Purpose: to populate and return a list of all the facts
     * from the file facts.json
     *
     * @param ctx
     */
    public Facts fetchFacts(Context ctx) {
        // Create a fileInputStream variable which will be used to get the facts.json file.
        FileInputStream fileInputStream = null;
        // Create and instance of Gson named gson to handle the converting of the string to json.
        Gson gson = new Gson();
        try {
            // Open facts.json
            fileInputStream = ctx.openFileInput("facts.json");
            // Read in the file
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }
            // Set the stringBuilder string value to a variable called json
            // which is only a text representation of json, not real json.
            String json = stringBuilder.toString();
            // Create an instance of Facts called facts and populated it
            // with the json output from convertin json string into json.
            Facts facts = gson.fromJson(json, Facts.class);
            return facts;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
