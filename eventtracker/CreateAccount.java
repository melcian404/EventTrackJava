
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

public class CreateAccount extends AppCompatActivity {

    // Create variables for editText, button, and userDB
    private EditText userNameEdit, passwordEdit, confPasswordEdit;
    private Button signUpBtn, login_button;
    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize variables
        userNameEdit = findViewById(R.id.editTextEmailAddress);
        passwordEdit = findViewById(R.id.editTextPassword);
        confPasswordEdit = findViewById(R.id.editTextConfPassword);

        // initialize buttons
        signUpBtn = findViewById(R.id.create_button);
        login_button = findViewById(R.id.login_button);

        // initialize userDB class
        userDB = new UserDB(CreateAccount.this);

        // Set up OnClickListener for button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    // get data from input fields
                String userEmail = userNameEdit.getText().toString();
                String userPassword = passwordEdit.getText().toString();
                String confPassword = confPasswordEdit.getText().toString();

                // Check if fields are empty or not
                if (userEmail.isEmpty() || userPassword.isEmpty() || confPassword.isEmpty()){
                    Toast.makeText(CreateAccount.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                    // Check if passwords match
                } else if (!userPassword.equals(confPassword)){
                    Toast.makeText(CreateAccount.this, "Passwords do not match ", Toast.LENGTH_SHORT).show();
                } else {
                    // All conditions met, create account
                    userDB.addUser (userEmail, userPassword, confPassword);
                    // Verify account is created
                    Toast.makeText(CreateAccount.this, "Your account has been created, welcome to EventTrack", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // OnClickListener for Login button
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(v -> {
            Intent intent = new Intent(CreateAccount.this, HomeScreen.class);
            startActivity(intent);
        });
    }
}


