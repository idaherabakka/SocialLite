package com.example.sociallite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Challenge;
import com.example.service.FirebaseDBService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;


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
        mAuth = FirebaseAuth.getInstance();
        registerUser = findViewById(R.id.register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPassword);

        Button loginBtn = findViewById(R.id.signIn);
        loginBtn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(email.getText())){
                this.email.setError("Email cannot be empty");
                this.email.requestFocus();
            }else if (TextUtils.isEmpty(password.getText())){
                this.password.setError("Password cannot be empty");
                this.password.requestFocus();
            }
            else {
                loginUser();
            }
        });
        registerUser.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,RegisterUser.class));
        });
        forgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,ForgotPassword.class));
        });
    }

    private void loginUser() {
            mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                       TextView message = findViewById(R.id.welcome_message);
                       message.setText("Welcome, user!");
                        startActivity(new Intent(MainActivity.this,OverviewActivity.class)); //Må endres til context screen
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }
}