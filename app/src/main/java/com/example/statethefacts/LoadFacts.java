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

public class LoadFacts extends Thread {
    private WeakReference<Activity> activityRef;

    public LoadFacts(MainActivity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void run() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference("/facts");
        mDbRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Gson gson = new Gson();
                HashMap<String, JSONObject> dataSnapshotValue = (HashMap<String, JSONObject>) dataSnapshot.getValue();
                String json = new Gson().toJson(dataSnapshotValue);
                //Facts facts = gson.fromJson(json, Facts.class);
                //Log.d("Message:", "Value is: " + json);
                //Log.d("Message:", "Value is: " + facts.states.get(0).abbreviation);
                FileOutputStream fos = null;
                try {
                    fos = activityRef.get().openFileOutput("facts.json",MODE_PRIVATE);
                    fos.write(json.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
