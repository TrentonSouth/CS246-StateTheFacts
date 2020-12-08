package com.example.statethefacts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
        EditText editName = (EditText) findViewById(R.id.name_entry);
        EditText editEmail = (EditText) findViewById(R.id.email_entry);

        // put name/email into variables
        String user = editName.getText().toString();
        String email = editEmail.getText().toString();

        // save to file
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("STFUserProfile", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("user_name", user);
        editor.putString("user_email", email);

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
            Intent intent1 = getIntent();
            String user = intent1.getStringExtra(MainActivity.USER);
            String email = intent1.getStringExtra(MainActivity.EMAIL);

            profile = new UserProfilePresenter(user, email);

            // set user and email EditText fields to saved profile
            EditText editUser = (EditText) findViewById(R.id.name_entry);
            EditText editEmail = (EditText) findViewById(R.id.email_entry);

            // log receipt of intent
            String msg = "Received intent with " + profile.getUserName()
                    + ", " + profile.getUserEMail();
            Log.d(TAG, msg);

            // display fields
            editUser.setText(profile.getUserName());
            editEmail.setText(profile.getUserEMail());
        }
    }
}