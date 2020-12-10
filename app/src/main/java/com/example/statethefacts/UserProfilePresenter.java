
package com.example.statethefacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UserProfilePresenter {
    // declare variables and constants
    private static final String TAG = "UserProfileActivity";
    private UserProfile profile;
    private UserProfileActivity activity;

    // getters and setters
    public String getUserName() {
        return profile.getUserName();
    }
    public String getUserEMail() {
        return profile.getUserEMail();
    }
    public String getUserAge() { return profile.getUserAge(); }

    /**
     * UserProfilePresenter constructor: creates an object using saved
     * data from SharedPreferences and updates the text entry fields
     * if the user has already saved the information.
     * @param activity
     */
    public UserProfilePresenter(UserProfileActivity activity){
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfile(preferences.getString("user_name", null),
                preferences.getString("user_email", null),
                preferences.getString("user_age", null));
        Log.i(TAG, "Loaded user information from SharedPreferences");

        if (profile.getUserName() != null) {
            TextView editName = activity.findViewById(R.id.name_entry);
            editName.setText(profile.getUserName());
        }
        if (profile.getUserEMail() != null) {
            TextView editEmail = activity.findViewById(R.id.email_entry);
            editEmail.setText(profile.getUserEMail());
        }
        if (profile.getUserAge() != null) {
            TextView editAge = activity.findViewById(R.id.age_entry);
            editAge.setText(profile.getUserAge());
        }
    }

    // constructors to instantiate object using direct information
    public UserProfilePresenter(String user, String email) {
        profile = new UserProfile(user, email);
    }
    public UserProfilePresenter(String user, String email, String age) {
        profile = new UserProfile(user, email, age);
    }

    /**
     * saveProfile: using information from the three text entry fields in the
     * UserProfileActivity, saves user profile information into SharePreferences
     * @param activity
     */
    public void saveProfile(UserProfileActivity activity) {
        // get name/email from form
        EditText editName = activity.findViewById(R.id.name_entry);
        EditText editEmail = activity.findViewById(R.id.email_entry);
        EditText editAge = activity.findViewById(R.id.age_entry);

        // put name/email into variables
        String user = editName.getText().toString();
        String email = editEmail.getText().toString();
        String age = editAge.getText().toString();

        // save to file
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("user_name", user);
        editor.putString("user_email", email);
        editor.putString("user_age", age);

        // commit changes to preferences
        editor.commit();

        // toast to tell user the save has completed
        Context context = activity.getApplicationContext();
        CharSequence text = "Saving your preferences";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
