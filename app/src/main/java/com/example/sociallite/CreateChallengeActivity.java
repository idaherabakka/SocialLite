package com.example.sociallite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Challenge;
import com.example.service.FirebaseDBService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateChallengeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDBService db;
    FirebaseAuth mAuth;
    EditText title;
    FirebaseUser creator;
    String type;
    Button createButton;
    Date currentDate;
    TextView dueDate;
    TextView description;
    String[] challengeTypes = {"Health", "Economics", "Nutrition"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createchallenge);
        db = new FirebaseDBService();
        mAuth = FirebaseAuth.getInstance();
        title = findViewById(R.id.title);
        creator = mAuth.getCurrentUser();
        dueDate = findViewById(R.id.DueDate);
        //type = findViewById(R.id.challengeType);
        description.findViewById(R.id.Description);
        createButton = findViewById(R.id.Create);

        Spinner spinner = (Spinner) findViewById(R.id.challengeType);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, challengeTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        type = spinner.getSelectedItem().toString();

        createButton.setOnClickListener(view -> {
            addChallenge();
        });

    }

    private void addChallenge() {
        currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String dateCurrent = simpleDateFormat.format(currentDate);

        String dateDue = dueDate.getText().toString() ;  // where dueDate is TextView

        Challenge challenge = new Challenge(title.getText().toString(), creator.toString(), dateDue, dateCurrent, type, description.toString());
        db.addChallenge(challenge);
        startActivity(new Intent(CreateChallengeActivity.this, OverviewActivity.class));
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),challengeTypes[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
