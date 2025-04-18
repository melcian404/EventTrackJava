package com.zybooks.eventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    // Create variables for fast action button
    private Button fastActionBtn;
    private GridView eventGridView;
    private ArrayList<Event> events;
    private EventDB eventDB;

    /*@Override
    public boolean onCreateOptMenu(Menu menu){
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }
    @Override
    public boolean onOptItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.action_notification){
            return true;
        }
        else if (itemId == R.id.action_logout){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NotificationManager.initialize(getApplicationContext(), "username");
        // initialize grid, database and adapter
        eventDB = new EventDB(this);
        events = new ArrayList<>();

        // initialize button
        fastActionBtn = findViewById(R.id.floatingActionButton);

        // Set up grid
        eventGridView = findViewById(R.id.eventGridView);


        // OnClickListener for fast action button
        fastActionBtn = (Button) findViewById(R.id.floatingActionButton);
        fastActionBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, CreateEvent.class);
            startActivity(intent);
        });
    }
}