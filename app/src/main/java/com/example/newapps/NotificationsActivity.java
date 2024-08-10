package com.example.newapps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notifications);

        // Here you can add logic to fetch notifications from a server or database.
        // If there are notifications, update the UI to display them.
        // Since the requirement is to show "No notifications", no further logic is needed.
    }
}
