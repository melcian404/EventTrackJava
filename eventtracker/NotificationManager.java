package com.zybooks.eventtracker;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationManager {

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static final String INFO = "notification_prefs";
    private static NotificationManager instance;
    private String username;

    private NotificationManager(Context context, String username) {
        this.username = username;
        sharedPref = context.getSharedPreferences(INFO, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.apply();
    }

    // Singleton pattern
    public static void initialize(Context context, String username){
        if(instance == null){
            instance = new NotificationManager(context, username);
        }
    }


    public static NotificationManager getInstance(){
        // check if instance is null
        if (instance == null){
            // if null, throw argument
            throw new IllegalStateException("Notifications are not initialized");
        }
        return instance;
    }

    public void setUsername(String username){
        this.username = username;
    }

    // Save if notifications enabled
    public void saveNotificationPref(boolean isEnabled){
        editor.putBoolean(username, isEnabled);
        editor.apply();
    }


    public boolean getNotificationPref(){
        // default false if notification is not enabled
        return sharedPref.getBoolean(username,false);
    }

}
