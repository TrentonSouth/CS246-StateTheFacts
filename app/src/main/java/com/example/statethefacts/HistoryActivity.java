package com.example.statethefacts;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HistoryActivity extends AppCompatActivity {

    private HistoryPresenter presenter;
    private EmailPresenter email;

    public HistoryPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        presenter = new HistoryPresenter(this);
        presenter.loadGameResult();
        presenter.getStats();
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
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    /**
     * sendEmailButton - method checks for a valid user name and email before launching the
     * sendMessage() method, which sends the user's history statistics.
     * @param view
     */
    public void sendEmailButton(View view) {
        email = new EmailPresenter(HistoryActivity.this);
        if (email.getProfile().getUserName() != null && email.getProfile().getUserEMail() != null) {
            email.sendMessage();
        } else {
            // toast to notify user there isn't a saved profile
            Context context = getApplicationContext();
            CharSequence text = "No User Profile. Please enter user profile.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}