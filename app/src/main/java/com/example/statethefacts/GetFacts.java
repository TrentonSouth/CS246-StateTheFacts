package com.example.statethefacts;

import android.app.Activity;
import android.util.Log;
import com.google.firebase.database.*;
import com.google.gson.Gson;


import java.lang.ref.WeakReference;

public class GetFacts extends Thread {
    private WeakReference<Activity> activityRef;

    public GetFacts(MainActivity activity) {
        this.activityRef = new WeakReference<Activity>(activity);
    }

    @Override
    public void run() {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference("/");
        mDbRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String json = dataSnapshot.getValue().toString();
                
                Gson gson = new Gson();
                Facts facts = gson.fromJson(json,Facts.class);
                Log.d("TAG", "Value is: " + facts.states.get(0).abbreviation);
            }

            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }


}
