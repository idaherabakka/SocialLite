package com.example.sociallite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.User;
import com.example.service.FirebaseDBService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText password;
    EditText email;
    Button register;
    FirebaseAuth mAuth;
    EditText firstName;
    EditText lastName;
    FirebaseDBService db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();
        db = new FirebaseDBService();
        password = findViewById(R.id.password);
        register = findViewById(R.id.registerUser);
        email = findViewById(R.id.email);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        register.setOnClickListener(view ->{
            creatUser();
        });
    }

    private void creatUser() {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String firstName = this.firstName.getText().toString();
        String lasName = this.lastName.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            this.firstName.setError("First Name cannot be empty");
            this.firstName.requestFocus();
        }
        if (TextUtils.isEmpty(lasName)){
                this.lastName.setError("Last Name cannot be empty");
                this.lastName.requestFocus();
            }
        if (TextUtils.isEmpty(email)){
            this.email.setError("Email cannot be empty");
            this.email.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            this.password.setError("Password cannot be empty");
            this.password.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterUser.this, "User Registered", Toast.LENGTH_SHORT).show();
                        User user = new User(firstName,lasName,email);
                        db.addUser(user);
                        startActivity(new Intent(RegisterUser.this, MainActivity.class));
                    }else {
                        Toast.makeText(RegisterUser.this, "Register failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}