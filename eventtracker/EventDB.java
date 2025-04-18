package com.zybooks.eventtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EventDB extends SQLiteOpenHelper {
    // Create variable for database name
    private static final String DB_NAME = "eventDB";
    // Database version
    private static final int VERSION = 2;

    // data table name
    private static final String TABLE_USERS = "event";
    // Variable for event ID
    private static final String COL_ID = "id";
    // Variable for event date
    private static final String COL_DATE = "date";
    // Variable for event name
    private static final String COL_NAME = "name";
    // Variable for event details
    private static final String COL_DETAILS = "details";

    // For passing variables in
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_DATE + " TEXT, " +
                    COL_NAME + " TEXT, " +
                    COL_DETAILS + " TEXT)";

    // Constructor for database handler
    public EventDB(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    // Method to create database
    @Override
    public void onCreate(SQLiteDatabase db){
        // Execute SQL table creation
        db.execSQL(TABLE_CREATE);
    }

    // Method for upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Or drop if already exists
        db.execSQL("Drop table if exists " + TABLE_USERS);
        // Create
        onCreate(db);
    }

    public void addEvents(ArrayList<Event> events) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Event event : events) {
            ContentValues values = new ContentValues();
            values.put(COL_DATE, event.getDate());
            values.put(COL_NAME, event.getName());
            values.put(COL_DETAILS, event.getDetails());
            db.insert(TABLE_USERS, null, values);
        }
        db.close();
    }

    public void updateEvent(int id, String newDate, String newName, String newDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, newDate);
        values.put(COL_NAME, newName);
        values.put(COL_DETAILS, newDetails);

        // Update the event with the specified ID
        int rowsAffected = db.update(TABLE_USERS, values, COL_ID + " = ?", new String[]{String.valueOf(id)});

        if (rowsAffected > 0) {
            System.out.println("Event " + id + " updated successfully.");
        } else {
            System.out.println("Event " + id + " not found.");
        }
        db.close();
    }

    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete the event with the specified ID
        int rowsDeleted = db.delete(TABLE_USERS, COL_ID + " = ?", new String[]{String.valueOf(id)});

        if (rowsDeleted > 0) {
            System.out.println("Event " + id + " deleted successfully.");
        } else {
            System.out.println("Event " + id + " not found.");
        }
        db.close();
    }
}

