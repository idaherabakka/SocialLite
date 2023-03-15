package com.example.sociallite;
import com.example.sociallite.DatabaseActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

FirebaseAuth mAuth;
    EditText email;
    EditText password;
    TextView registerUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        registerUser = findViewById(R.id.register);
        DatabaseActivity db2 = new DatabaseActivity();
        //db2.deleteUser();
        //db2.read();

        Button loginBtn = findViewById(R.id.signIn);
        loginBtn.setOnClickListener(view -> {
            loginUser();
        });
        registerUser.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,RegisterUser.class));
        });

    }

    private void loginUser() {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if (TextUtils.isEmpty(email)){
            this.email.setError("Email cannot be empty");
            this.email.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            this.password.setError("Password cannot be empty");
            this.password.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        //Start new activity
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}