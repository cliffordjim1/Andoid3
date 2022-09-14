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

public class MySignUp extends AppCompatActivity {

    EditText editusername, editpassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sign_up);

        editusername=findViewById(R.id.username);
        editpassword=findViewById(R.id.password);

        mAuth=FirebaseAuth.getInstance();




    }
        public void gotoreg(View view) {


            Intent intent = new Intent(MySignUp. this,MainActivity. class );
            startActivity(intent);
    }


    public void Login(View view) {
        String username, password;

        username=editusername.getText().toString().trim();
        password=editpassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //redirect to Profile page

                            Intent intent = new Intent(getApplicationContext(), Profile.class);
                            startActivity(intent);

                        }else
                        {
                            Toast.makeText(MySignUp.this, "Invalid credentials", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }
}
