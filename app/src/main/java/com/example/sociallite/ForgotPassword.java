package com.example.sociallite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText email;
    Button sendNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        sendNewPassword = findViewById(R.id.forgotPasswordBtn);
        mAuth = FirebaseAuth.getInstance();

        sendNewPassword.setOnClickListener(view ->{
            email = findViewById(R.id.email);
            if(TextUtils.isEmpty(email.getText())){
                email.setError("Cant be empty");
            }
            else {
                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Email sent, might take a few minutes. Check spam", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                        }
                        else {
                            Toast.makeText(ForgotPassword.this, "Email not working, try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}