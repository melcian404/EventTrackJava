package com.zybooks.eventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CreateEvent extends AppCompatActivity {

    // Create variables for editText, buttons, and eventDB
    //private UserDB userDB;
    private EditText eventDateEdit, eventNameEdit, eventDetailsEdit;
    private Button addEventButton, homeButton;
    private EventDB eventDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.createEvent), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize variables
        eventDateEdit = findViewById(R.id.addEventDate);
        eventNameEdit = findViewById(R.id.addEventName);
        eventDetailsEdit = findViewById(R.id.addEventDetails);

        // Initialize buttons
        addEventButton = findViewById(R.id.addEventButton);

        // Initialize eventDB class
        eventDB = new EventDB(CreateEvent.this);

        // OnClickListener for add event button
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from input fields
                String eventDate = eventDateEdit.getText().toString();
                String eventName = eventNameEdit.getText().toString();
                String eventDetails = eventDetailsEdit.getText().toString();

                // Check if fields are empty
                if (eventDate.isEmpty() || eventName.isEmpty() || eventDetails.isEmpty()){
                    Toast.makeText(CreateEvent.this, "Please make sure all event info is added.", Toast.LENGTH_SHORT).show();
                } else {
                    // Array list of events
                    ArrayList<Event> events = new ArrayList<>();
                    events.add(new Event(eventDate, eventName, eventDetails));
                    // add event to database
                    eventDB.addEvents(events);
                    // Verify add
                    Toast.makeText(CreateEvent.this, "Your event has been added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // OnClickListener to go back to home screen
        homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateEvent.this, HomeScreen.class);
            startActivity(intent);
        });

    }
}
