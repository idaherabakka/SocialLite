package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Challenge;
import com.example.service.FirebaseDBService;

public class CreateChallengeActivity extends AppCompatActivity {
    FirebaseDBService firebaseDB = new FirebaseDBService();

    EditText title;
    //EditText creator;
    EditText type;
    TextView dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createchallenge);
        title = findViewById(R.id.title);
        //FIX
        String creator = "Ida";
        dueDate = findViewById(R.id.dueDate);
        //type = findViewById(R.id.ChallengeType);

        Challenge challenge = new Challenge(this.title.getText().toString(), creator);

        Button profileButton = findViewById(R.id.Create);
        /*profileButton.setOnClickListener(
               firebaseDB.addChallenge(challenge)
        );*/


        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Select your favorite Planet!");

        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));*/
    }
}
