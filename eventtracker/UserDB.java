package com.zybooks.eventtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {
    // Create variable for database name
    private static final String DB_Name = "usersDB";
    // database version
    private static final int VERSION = 1;

    // data table name
    public static final String TABLE_USERS = "user";
    // Variable for user ID
    public static final String COL_ID = "id";
    // Variable for username column
    public static final String COL_EMAIL = "email";
    // Variable for password column
    public static final String COL_PASS = "password";
    // Variable for password confirmed column
    public static final String COL_CPASS = "confirmPassword";

    // For passing variables in
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_EMAIL + "TEXT, " +
                    COL_PASS + "TEXT, " +
                    COL_CPASS + "TEXT)";

    // Constructor for database handler
    public UserDB(Context context) {
        super(context, DB_Name, null, VERSION);
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

    // Method to add username to database
    public void addUser (String username, String password, String confirmPassword){
        // Create variable for writable method
        SQLiteDatabase db = this.getWritableDatabase();
        // Content values
        ContentValues values = new ContentValues();
        // Pass values in
        values.put(UserDB.COL_EMAIL, username);
        values.put(UserDB.COL_PASS, password);
        values.put(UserDB.COL_CPASS, confirmPassword);
        // Close database after adding
        db.close();
    }


}
