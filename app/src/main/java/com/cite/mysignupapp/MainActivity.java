package com.cite.mysignupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editUsername, editEmail, editPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUsername = findViewById(R.id.user);
        editEmail = findViewById(R.id.Email);
        editPass = findViewById(R.id.Pass);

        mAuth=FirebaseAuth.getInstance();
    }

    public void Register (View view){
        String username, email, password;

        username=editUsername.getText().toString().trim();
        email=editEmail.getText().toString().trim();
        password=editPass.getText().toString().trim();



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(username, email);


                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(MainActivity.this, "Register is Successful", Toast.LENGTH_SHORT).show();


                                            }else
                                                Toast.makeText(MainActivity.this, "Register is Failed. Please try again", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }

                    }
                });








    }
}