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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        /*FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("first", "Lisa");
        user.put("last", "Eliassen");
        user.put("born", 1999);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                });

         */
    }
}