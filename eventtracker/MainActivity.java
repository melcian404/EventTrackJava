package com.zybooks.eventtracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Login button from activity_main to move to home_screen
    // new id for button
    Button login_button1;
    // Signup button
    Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    // Setting up Login button on main screen
        // using findViewByID() to get login1 button
        login_button1 = (Button) findViewById(R.id.button);
        // Add button and click listener
        login_button1.setOnClickListener(v -> {
            // intent button to use login button to get to home screen
            Intent intent = new Intent(MainActivity.this, HomeScreen.class);
            // Start activity
            startActivity(intent);
        });

        // Following above format to set up Signup button
        signup_button = (Button) findViewById(R.id.button2);
        signup_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateAccount.class);
            startActivity(intent);
        });
    }

}

