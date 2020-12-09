package com.example.statethefacts;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.database.*;
import com.google.gson.Gson;

import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * The LoadFacts class is used to get all of the facts
 * from firebase. Once it has the facts, it writes them
 * to a file named facts.json in json format.
 * A listener is established so that if the contents
 * in firebase ever change, the file is rewritten.
 *
 * @author Trenton South
 * @version 1.0
 * @since 12/9/2020
 */
public class LoadFacts extends Thread {
    private WeakReference<Activity> activityRef;

    /**
     * the LoadFacts Method is the constructor
     * Purpose: Create a weak reference so that it can
     * be called in its own thread.
     *
     * @param activity
     */
    public LoadFacts(MainActivity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    /**
     * The run method
     * Purpose: load the data from firebase and write to file
     */
    @Override
    public void run() {
        // Establish a connection to firebase
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        // The data we need is at the facts element of root.
        DatabaseReference mDbRef = mDatabase.getReference("/facts");
        // This is the listener that will execute once at the instantiation
        // and again every time the data is changed.
        mDbRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Create a new instance of Gson to handle converting the data into json.
                Gson gson = new Gson();
                // Create a HashMap from the returned value. This is needed for the conversion to json.
                HashMap<String, JSONObject> dataSnapshotValue = (HashMap<String, JSONObject>) dataSnapshot.getValue();
                // Convert dataSnapshotValue into json.
                String json = new Gson().toJson(dataSnapshotValue);
                // Create file output stream in order to write the json to a file.
                FileOutputStream fileOutputStream = null;
                try {
                    // Try to write to facts.json
                    fileOutputStream = activityRef.get().openFileOutput("facts.json", MODE_PRIVATE);
                    fileOutputStream.write(json.getBytes());
                } catch (FileNotFoundException e) {
                    // Catch file not found exception, but since it creates it, this shouldn't be an issue.
                    e.printStackTrace();
                } catch (IOException e) {
                    // Catch IOException
                    e.printStackTrace();
                }
            }

            // If there is a database error, write it to the log file for debugging.
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
