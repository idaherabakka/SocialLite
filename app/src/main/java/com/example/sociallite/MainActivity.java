package com.example.sociallite;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.model.Challenge;
import com.example.model.Points;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText email;
    EditText password;
    TextView registerUser;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseDBService db = new FirebaseDBService();
        //db.updateDocument("users", "testadd", "born", "1999");
        //db.deleteDocument("User", "1");
        //Points point = new Points();
        //point.setID("1");
        //point.setChallengePoints(100);
        //point.setChallengeId("2");
        //point.setUserId("2");
        //db.addPoints(point);

        //Challenge challenge = new Challenge();
        //challenge.setID("1");
        //challenge.setCreator("Olesya");

        //Date currentDate = new Date();

        //challenge.setDate_created(currentDate);
        //challenge.setDeadline(currentDate);
        //challenge.setTitle("10k daily steps");

        //ArrayList<String> participants = new ArrayList<>();
        //participants.add("1");
        //participants.add("2");
        //challenge.setParticipants(participants);

        //db.addChallenge(challenge);

        //db.updateDocumentArrayField("Challenge","1","participants", "3", "delete");

        mAuth = FirebaseAuth.getInstance();
        registerUser = findViewById(R.id.register);
        forgotPassword = findViewById(R.id.forgotPassword);
        Button loginBtn = findViewById(R.id.signIn);

        loginBtn.setOnClickListener(view -> {
            loginUser();
        });
        registerUser.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,RegisterUser.class));
        });
        forgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,ForgotPassword.class));
        });
    }

    private void loginUser() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        String emailLogin = email.getText().toString();
        String passwordLogin = password.getText().toString();

        if (TextUtils.isEmpty(emailLogin)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if (TextUtils.isEmpty(passwordLogin)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(emailLogin,passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        //Start new activity
                        startActivity(new Intent(MainActivity.this,OverviewActivity.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }

}