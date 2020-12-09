package com.example.statethefacts;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * The EmailPresenter class contains the methods used to send emails, linking
 * GMail and SendMailTask classes to the HistoryActivity
 */
public class EmailPresenter {
    private static final String FROM_EMAIL = "statethefacts2020@gmail.com";
    private static final String EMAIL_PASSWORD = "H9F4eBNA2wStNs2F";
    private static final String EMAIL_SUBJECT = "State The Facts History";
    private static final String TAG = "EmailPresenter";
    private HistoryActivity activity;
    private UserProfilePresenter profile;
    private double averageScore;
    private int playAttempts;
    private String emailBody;
    private String emailSubject;

    /**
     * EmailPresenter() constructor makes HistoryActivity available to an EmailPresenter object.
     * Then, lodes user information from sharedpreferences into a UserProfilePresenter object.
     * @param activity
     */
    public EmailPresenter(HistoryActivity activity) {
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfilePresenter(preferences.getString("user_name", null),
                preferences.getString("user_email", null));

    }

    /**
     * getProfile() is a getter fro the UserProfilePresenter object profile
     * @return
     */
    public UserProfilePresenter getProfile() {
        return profile;
    }

    /**
     * sendMessage() method checks content of the UserProfilePresenter object.
     * If it is not null, has user information, it will send the game summary in
     * an email to the user's email address.
     */
    public void sendMessage() {
        if (profile.getUserName() != null && profile.getUserEMail() != null) {
            Log.i(TAG, "Send History Email Launched");
            Log.i(TAG, "Send email to " + profile.getUserEMail());
            activity.getPresenter().loadGameResult();
            averageScore = activity.getPresenter().getAverageScoreEmail() * 100;
            playAttempts = activity.getPresenter().getPlayAttemptsEmail();
            emailSubject = profile.getUserName() + "'s " + EMAIL_SUBJECT;
            emailBody = "Your average score is " + averageScore + "%, having played " +
                    playAttempts + " total games.";
            new SendMailTask(activity).execute(FROM_EMAIL, EMAIL_PASSWORD, profile.getUserEMail(),
                    emailSubject, emailBody);
        } else {
            Log.i(TAG, "No User Profile");
        }
    }



}
