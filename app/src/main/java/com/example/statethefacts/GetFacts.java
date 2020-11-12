package com.example.statethefacts;

import android.content.Context;
import com.google.gson.Gson;

import java.io.*;

public class GetFacts {

    public Facts Fetch(Context ctx) {
        FileInputStream fis = null;
        Gson gson = new Gson();
        try {
            fis =  ctx.openFileInput("facts.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            String json = sb.toString();
            Facts facts = gson.fromJson(json, Facts.class);
            return facts;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
