package com.example.statethefacts;

import android.content.SharedPreferences;
import android.util.Log;

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

    public EmailPresenter(HistoryActivity activity) {
        this.activity = activity;
        SharedPreferences preferences = activity.getSharedPreferences("STFUserProfile", 0);
        profile = new UserProfilePresenter(preferences.getString("user_name", null),
                preferences.getString("user_email", null));

    }

    public UserProfilePresenter getProfile() {
        return profile;
    }

    public void sendMessage() {
        if (profile.getUserName() != null) {
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
