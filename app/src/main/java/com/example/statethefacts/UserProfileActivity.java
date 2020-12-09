package com.example.statethefacts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {
    /** define constants */
    private static final String TAG = "UserProfileActivity";
    private UserProfilePresenter profile;

    // getters and setters
    public UserProfilePresenter getProfile() {
        return profile;
    }
    public void setProfile(UserProfilePresenter profile) {
        this.profile = profile;
    }

    /** called when user clicks Save Profile button */
    public void saveProfile(View view) {
        // get name/email from form
        EditText editName = findViewById(R.id.name_entry);
        EditText editEmail = findViewById(R.id.email_entry);
        EditText editAge = findViewById(R.id.age_entry);

        // put name/email into variables
        String user = editName.getText().toString();
        String email = editEmail.getText().toString();
        String age = editAge.getText().toString();

        // save to file
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("STFUserProfile", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("user_name", user);
        editor.putString("user_email", email);
        editor.putString("user_age", age);

        // commit changes to preferences
        editor.commit();

        // toast to notify user the preferences are being saved
        Context context = getApplicationContext();
        CharSequence text = "Saving your preferences";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // get intent
        if (!MainActivity.HASPROFILE.contains("no")) {
            Intent intent = getIntent();
            String user = intent.getStringExtra(MainActivity.USER);
            String email = intent.getStringExtra(MainActivity.EMAIL);
            String age = intent.getStringExtra(MainActivity.AGE);

            profile = new UserProfilePresenter(user, email, age);

            // set user and email EditText fields to saved profile
            EditText editUser = findViewById(R.id.name_entry);
            EditText editEmail = findViewById(R.id.email_entry);
            EditText editAge = findViewById(R.id.age_entry);

            // log receipt of intent
            String msg = "Received intent with " + profile.getUserName()
                    + ", " + profile.getUserEMail() + ", "
                    + profile.getUserAge();
            Log.d(TAG, msg);

            // display fields
            editUser.setText(profile.getUserName());
            editEmail.setText(profile.getUserEMail());
            editAge.setText(profile.getUserAge());
        }
    }

    /**
     * The onCreateOptionsMenu Method
     * Purpose: create the 3 dots (ellipsis) on right side of the app in the top bar
     * @param menu
     * @return boolean as true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.youtube.com/watch?v=kknBxoCOYXI
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}